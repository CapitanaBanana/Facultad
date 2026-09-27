#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>

int main()
{
  int sock = 0;
  struct sockaddr_in serv_addr;

  // 1. Crear el socket
  sock = socket(AF_INET, SOCK_STREAM, 0);

  serv_addr.sin_family = AF_INET;
  serv_addr.sin_port = htons(8080);

  // Apuntamos a la IP de vma
  inet_pton(AF_INET, "192.168.56.10", &serv_addr.sin_addr);

  // 2. Intentar la conexión
  if (connect(sock, (struct sockaddr *)&serv_addr, sizeof(serv_addr)) > 0)
  {
    int cantidades[] = {10, 100, 1000, 10000, 100000, 1000000};
    for (int i = 0; i < 6; i++)
    {
      // reserva memoria
      int total_bytes = cantidades[i];
      char *buffer = malloc(total_bytes);
      // llenar el buffer
      for (int j = 0; j < total_bytes; j++)
      {
        buffer[j] = 'X';
      }
      int enviados = 0;
      while (enviados < total_bytes)
      {
        int n = write(sockfd, buffer + enviados, total_bytes - enviados); // escribí en el socket la parte de datos que falta. write devuelve la cant de bytes que efectivamente pudo enviar. buffer+datos= muevo el puntero hasta donde ya envie. total_bytes-enviados= manda todo menos lo que ya mande.
        if (n < 0)
        {
          error("Error al escribir en el socket");
        }
        enviados += n;
      }
      // VERIFICACIÓN
      if (enviados == total_bytes)
      {
        printf("Éxito: Se enviaron %d bytes correctamente.\n", enviados);
      }
      else
      {
        printf("Advertencia: Solo se enviaron %d de %d bytes.\n", enviados, total_bytes);
      }

      // Liberar la memoria para la próxima iteración
      free(buffer);
    }
  }
  return 0;
}