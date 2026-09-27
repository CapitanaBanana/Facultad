#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netinet/in.h>

#define DEFAULT_PORT 8080

void error(const char *msg) {
    perror(msg);
    exit(1);
}

int main(int argc, char *argv[]) {
    int port = DEFAULT_PORT;
    if (argc > 1) {
        port = atoi(argv[1]);
    }

    int listen_fd = socket(AF_INET, SOCK_STREAM, 0);
    if (listen_fd < 0) error("Error al crear el socket");

    // Permitir reutilizar el puerto
    int opt = 1;
    setsockopt(listen_fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));

    struct sockaddr_in serv_addr;
    memset(&serv_addr, 0, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    serv_addr.sin_addr.s_addr = INADDR_ANY;
    serv_addr.sin_port = htons(port);

    if (bind(listen_fd, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) < 0)
        error("Error en bind");

    if (listen(listen_fd, 5) < 0)
        error("Error en listen");

    printf("==========================================\n");
    printf(" Servidor escuchando en el puerto %d\n", port);
    printf("==========================================\n\n");

    int cantidades[] = {10, 100, 1000, 10000, 100000, 1000000};
    int num_experimentos = sizeof(cantidades) / sizeof(cantidades[0]);

    while (1) {
        struct sockaddr_in client_addr;
        socklen_t client_len = sizeof(client_addr);
        int conn_fd = accept(listen_fd, (struct sockaddr *)&client_addr, &client_len);
        if (conn_fd < 0) {
            perror("Error al aceptar conexión");
            continue;
        }

        char client_ip[INET_ADDRSTRLEN];
        inet_ntop(AF_INET, &client_addr.sin_addr, client_ip, sizeof(client_ip));
        printf("[+] Cliente conectado desde %s:%d\n", client_ip, ntohs(client_addr.sin_port));

        for (int i = 0; i < num_experimentos; i++) {
            int total_bytes = cantidades[i];
            char *buffer = malloc(total_bytes);
            if (!buffer) error("Error asignando memoria");

            int recibidos = 0;
            while (recibidos < total_bytes) {
                int n = read(conn_fd, buffer + recibidos, total_bytes - recibidos);
                if (n <= 0) {
                    printf("  [-] Conexión cerrada o error durante lectura de %d bytes\n", total_bytes);
                    free(buffer);
                    goto next_client;
                }
                recibidos += n;
            }

            printf("  [✓] Recibidos %7d bytes de %s\n", recibidos, client_ip);
            free(buffer);

            // Responder con confirmación de 255 bytes (como especifica la consigna)
            char ack_buffer[255];
            memset(ack_buffer, 'A', sizeof(ack_buffer));
            int respondidos = 0;
            while (respondidos < 255) {
                int w = write(conn_fd, ack_buffer + respondidos, 255 - respondidos);
                if (w <= 0) break;
                respondidos += w;
            }
        }

next_client:
        close(conn_fd);
        printf("[+] Conexión finalizada con %s\n\n", client_ip);
    }

    close(listen_fd);
    return 0;
}
