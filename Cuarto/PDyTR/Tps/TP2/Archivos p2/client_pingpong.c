#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>
#include <arpa/inet.h>
#include <sys/socket.h>

void error(const char *msg) {
    perror(msg);
    exit(1);
}

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
    if (sockfd < 0) error("Error al crear socket");

    struct sockaddr_in serv_addr;
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_port = htons(port);
    inet_pton(AF_INET, server_ip, &serv_addr.sin_addr);

    printf("Conectando al servidor Ping-Pong %s:%d...\n", server_ip, port);
    if (connect(sockfd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0) {
        error("Error al conectar");
    }
    printf("¡Conexión establecida!\n\n");

    int cantidades[] = {10, 100, 1000, 10000, 100000, 1000000};
    int num_experimentos = sizeof(cantidades) / sizeof(cantidades[0]);

    printf("%-15s | %-20s | %-25s\n", "Tamaño (Bytes)", "T_RTT (us)", "T_Unidireccional (T_RTT/2 us)");
    printf("-----------------------------------------------------------------------------\n");

    for (int i = 0; i < num_experimentos; i++) {
        int total_bytes = cantidades[i];
        char *send_buf = malloc(total_bytes);
        char *recv_buf = malloc(total_bytes);
        if (!send_buf || !recv_buf) error("Error asignando memoria");
        memset(send_buf, 'P', total_bytes);

        // --- INICIO MEDICIÓN PING-PONG (RTT) ---
        double t0 = get_time_us();

        // 1. Enviar N bytes
        int enviados = 0;
        while (enviados < total_bytes) {
            int n = write(sockfd, send_buf + enviados, total_bytes - enviados);
            if (n < 0) error("Error en write()");
            enviados += n;
        }

        // 2. Recibir exactamente los mismos N bytes de vuelta (Echo)
        int recibidos = 0;
        while (recibidos < total_bytes) {
            int n = read(sockfd, recv_buf + recibidos, total_bytes - recibidos);
            if (n <= 0) error("Error en read()");
            recibidos += n;
        }

        double t1 = get_time_us();
        double t_rtt = t1 - t0;
        double t_unidireccional = t_rtt / 2.0;

        printf("%-15d | %-20.2f | %-25.2f\n", total_bytes, t_rtt, t_unidireccional);

        free(send_buf);
        free(recv_buf);
        usleep(50000); // 50ms de pausa
    }

    printf("-----------------------------------------------------------------------------\n");
    printf("Experimento Ping-Pong finalizado.\n");

    close(sockfd);
    return 0;
}
