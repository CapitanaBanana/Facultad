# Nombre del compilador
CC = gcc

# Flags de compilación
CFLAGS = -Wall -g

# Nombre del ejecutable
EXEC = get_task_info

# Archivos fuente y objeto
SRC = get_task_info.c
OBJ = get_task_info.o

# Regla por defecto: compilar el programa
all: $(EXEC)

$(EXEC): $(OBJ)
	$(CC) $(CFLAGS) -o $(EXEC) $(OBJ)

# Regla para compilar el archivo fuente a objeto
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# Limpiar archivos generados
clean:
	rm -f $(EXEC) $(OBJ)

# Ejecutar el programa
run: $(EXEC)
	./$(EXEC)

