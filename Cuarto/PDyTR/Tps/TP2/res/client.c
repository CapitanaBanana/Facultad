#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

void error(const char *msg) {
    perror(msg);
    exit(1);
}

// Función auxiliar para obtener el tiempo en microsegundos (us)
double get_time_us() {
    struct timespec ts;
    clock_gettime(CLOCK_MONOTONIC, &ts);
    return (double)ts.tv_sec * 1000000.0 + (double)ts.tv_nsec / 1000.0;
}

int main(int argc, char *argv[]) {
    if (argc < 3) {
        fprintf(stderr, "Uso: %s <IP_SERVIDOR> <PUERTO>\n", argv[0]);
        exit(1);
    }

    char *server_ip = argv[1];
    int port = atoi(argv[2]);

    int sockfd = socket(AF_INET, SOCK_STREAM, 0);
    if (sockfd < 0) error("Error al crear el socket");

    struct sockaddr_in serv_addr;
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(port);

    if (inet_pton(AF_INET, server_ip, &serv_addr.sin_addr) <= 0) {
        fprintf(stderr, "Dirección IP inválida: %s\n", server_ip);
        exit(1);
    }

    printf("Conectando al servidor %s:%d...\n", server_ip, port);
    if (connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        error("Error conectando con el servidor");
    }
    printf("¡Conexión establecida exitosamente!\n\n");

    int cantidades[] = {10, 100, 1000, 10000, 100000, 1000000};
    int num_experimentos = sizeof(cantidades) / sizeof(cantidades[0]);

    printf("%-15s | %-20s | %-20s\n", "Tamaño (Bytes)", "Tiempo write() (us)", "Tiempo read() (us)");
    printf("-------------------------------------------------------------------\n");

    for (int i = 0; i < num_experimentos; i++) {
        int total_bytes = cantidades[i];
        char *buffer = malloc(total_bytes);
        if (!buffer) error("Error de asignación de memoria");
        memset(buffer, 'X', total_bytes);

        // --- MEDICIÓN DE WRITE(...) ---
        double t0_write = get_time_us();
        
        int enviados = 0;
        while (enviados < total_bytes) {
            int n = write(sockfd, buffer + enviados, total_bytes - enviados);
            if (n < 0) error("Error en write()");
            enviados += n;
        }

        double t1_write = get_time_us();
        double tiempo_write_us = t1_write - t0_write;

        // --- MEDICIÓN DE READ(...) ---
        char ack_buffer[256];
        double t0_read = get_time_us();
        
        int recibidos = 0;
        while (recibidos < 255) {
            int n = read(sockfd, ack_buffer + recibidos, 255 - recibidos);
            if (n < 0) error("Error en read()");
            if (n == 0) break;
            recibidos += n;
        }

        double t1_read = get_time_us();
        double tiempo_read_us = t1_read - t0_read;

        printf("%-15d | %-20.2f | %-20.2f\n", total_bytes, tiempo_write_us, tiempo_read_us);

        free(buffer);
        usleep(50000); // 50ms de pausa entre cada envío para estabilidad de red
    }

    printf("-------------------------------------------------------------------\n");
    printf("Experimento completado.\n");

    close(sockfd);
    return 0;
}
