#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>

void error(char *msg)
{
  perror(msg);
  exit(1);
}

int main()
{
  struct sockaddr_in serv_address;

  // 1. Crear el socket original (file descriptor)
  int socket_fd = socket(AF_INET, SOCK_STREAM, 0);
  if (socket_fd < 0)
    error("Error al crear el socket");

  // 2. Configurar la dirección y puerto
  serv_address.sin_family = AF_INET;
  serv_address.sin_port = htons(8080);
  serv_address.sin_addr.s_addr = INADDR_ANY;

  // 3. Asociar el socket al puerto
  if (bind(socket_fd, (struct sockaddr *)&serv_address, sizeof(serv_address)) < 0)
  {
    error("Error en el bind");
  }

  // 4. Habilitar la cola de conexiones
  listen(socket_fd, 3);
  printf("Servidor: Escuchando en el puerto 8080...\n");

  // 5. Bucle infinito para atender llamadas
  while (1)
  {
    // accept() bloquea hasta que alguien llame, y devuelve un NUEVO socket
    int socket_cliente = accept(socket_fd, NULL, NULL);
    if (socket_cliente < 0)
      error("Error al aceptar la conexión");

    printf("Servidor: ¡Cliente conectado! Recibiendo datos...\n");

    int cantidades[] = {10, 100, 1000, 10000, 100000, 1000000};

    for (int i = 0; i < 6; i++)
    {
      int total_bytes = cantidades[i];
      char *buffer = malloc(total_bytes);
      if (buffer == NULL)
        error("Error de memoria");

      int recibidos = 0;

      // Bucle de lectura garantizada (TCP puede fragmentar los paquetes)
      while (recibidos < total_bytes)
      {
        int n = read(socket_cliente, buffer + recibidos, total_bytes - recibidos);
        if (n < 0)
          error("Error al leer del socket");
        if (n == 0)
          break; // El cliente cortó
        recibidos += n;
      }

      // Verificación lógica sin imprimir a pantalla
      int es_valido = 1;
      for (int j = 0; j < recibidos; j++)
      {
        if (buffer[j] != 'X')
        {
          es_valido = 0;
          break;
        }
      }

      if (recibidos == total_bytes && es_valido)
      {
        printf("  -> Éxito: %d bytes validados correctamente.\n", recibidos);
      }
      else
      {
        printf("  -> Fallo: Esperaba %d, recibí %d.\n", total_bytes, recibidos);
      }

      free(buffer);
    }

    // Cortamos con este cliente
    close(socket_cliente);
    printf("Servidor: Conexión terminada. Esperando otro cliente...\n\n");
  }

  close(socket_fd);
  return 0;
}