SOURCES = $(wildcard *.c)
OBJECTS = $(SOURCES:.c=.o)
EXECUTABLES = $(SOURCES:.c=)
CFLAGS = -Wall -Werror
LDLIBS = -lpth

all: $(EXECUTABLES)

clean: 
	rm -f $(OBJECTS) $(EXECUTABLES)

.PHONY: all clean
