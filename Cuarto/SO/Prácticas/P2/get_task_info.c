#include <stdio.h>
#include <unistd.h>
#include <sys/syscall.h>
#include <string.h>
#define SYS_get_task_info 468
void print_task_info(const char *info) {
printf("\nInformación de los procesos en ejecución:\n");
printf("----------------------------------------\n");
printf("%s", info);
printf("\n----------------------------------------\n");
}
int main() {
char buffer[1024];
// Buffer donde se almacenará la información de las tareas
long bytes_read;
// Llamada al sistema para obtener la información de los procesos
bytes_read = syscall(SYS_get_task_info, buffer, sizeof(buffer));
// Comprobamos si la llamada al sistema fue exitosa
if (bytes_read < 0) {
perror("Error al invocar la llamada al sistema");
return 1;
}
// Mostrar la información obtenida de los procesos
print_task_info(buffer);
return 0;
}
