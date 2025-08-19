CFLAGS = -Wall -Werror
LDLIBS = -lpth
PROJECT_DIR := $(dir $(abspath $(lastword $(MAKEFILE_LIST))))

PY = python3
PY_NOGIL = podman run -it --rm -v .:/mnt docker.io/felopez/python-nogil:latest python3 -X gil=0

VENV = $(PROJECT_DIR)/.venv
VENV_PY = $(VENV)/bin/$(PY)
VENV_PIP = $(VENV)/bin/pip
# VENV_NOGIL = $(PROJECT_DIR)/.venv-nogil
# VENV_PY_NOGIL = $(VENV_NOGIL)/bin/$(PY_NOGIL)
# VENV_PIP_NOGIL = $(VENV_NOGIL)/bin/pip

all: klt ult $(VENV)

venv: $(VENV)

$(VENV):
	$(PY) -m venv $(PROJECT_DIR)/.venv
	$(VENV_PIP) install --upgrade pip
	$(VENV_PIP) install -r $(PROJECT_DIR)/requirements.txt


run_klt: klt
	./klt

run_ult: ult
	./ult

run_klt_py: $(VENV)
	$(VENV_PY) klt.py

run_ult_py: $(VENV)
	$(VENV_PY) ult.py

run_klt_py_nogil:
	$(PY_NOGIL) /mnt/klt.py

run: run_klt run_klt_py run_ult run_ult_py run_ult_py_nogil

help::
	@echo "Makefile targets:"
	@echo "  all: Build all executables and create virtual environment"
	@echo "  run_klt: Run C KLT executable"
	@echo "  run_ult: Run C ULT executable"
	@echo "  run_klt_py: Run KLT Python script"
	@echo "  run_ult_py: Run ULT Python script"
	@echo "  run_klt_py_nogil: Run KLT Python script with no GIL"
	@echo "  clean: Remove all generated files"
	@echo "  help: Show this help message"

clean::
	rm -f *.o klt ult $(VENV)

.PHONY: all help clean run run_klt_py run_ult_py run_klt run_ult run_klt_py_nogil
