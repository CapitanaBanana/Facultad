include ../common.mk

SOURCES = klt.c ult.c
OBJECTS = $(SOURCES:.c=.o)
EXECUTABLES = $(SOURCES:.c=)
STATIC_LIBS = common.o
CFLAGS = -Wall -Werror
LDLIBS = -lpth


all: $(EXECUTABLES)

$(EXECUTABLES): $(STATIC_LIBS)

STATIC_LIBS: common.c common.h
	$(CC) $(CFLAGS) -c common.c

clean:: 
	rm -f $(OBJECTS) $(EXECUTABLES) $(STATIC_LIBS)


.PHONY: all clean
