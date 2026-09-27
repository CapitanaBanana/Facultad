# Ejercicio 2: Experimentos de Comunicación y Medición de Tiempos TCP

Este documento contiene la solución completa, el código fuente, los **datos empíricos reales obtenidos en la ejecución**, y el análisis teórico detallado para el **Ejercicio 2** de la Práctica 2.

---

## 1. Descripción de las Computadoras y Red Utilizadas

| Parámetro | Computadora 1 (Servidor VM 1) | Computadora 2 (Cliente VM 2) |
| :--- | :--- | :--- |
| **Rol** | Servidor TCP | Cliente TCP |
| **Entorno** | VirtualBox VM (Ubuntu 26.04) | VirtualBox VM (Ubuntu 26.04) |
| **Recursos Asignados** | 2 vCPUs, 2 GB RAM | 2 vCPUs, 2 GB RAM |
| **Dirección IP** | `192.168.1.28` | `192.168.1.29` |
| **Topología de Red** | **Misma Red Local (LAN Wi-Fi / Ethernet)** vía adaptador en modo puente (`public_network`) |

---

## 2. Resultados Empíricos Reales Obtenidos

Valores reales medidos en el cliente para cada cantidad de datos (en microsegundos $\mu s$ y milisegundos $ms$):

| Tamaño Enviado (Bytes) | Tamaño $10^x$ | Tiempo `write()` ($\mu s$) | Tiempo `write()` ($ms$) | Tiempo `read()` ($\mu s$) | Tiempo `read()` ($ms$) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **10** | $10^1$ B | $1.009,33\,\mu s$ | $1,01\,ms$ | $42.856,92\,\mu s$ | $42,86\,ms$ |
| **100** | $10^2$ B | $2.379,59\,\mu s$ | $2,38\,ms$ | $9.386,78\,\mu s$ | $9,39\,ms$ |
| **1.000** | $10^3$ B | $2.903,67\,\mu s$ | $2,90\,ms$ | $86.594,11\,\mu s$ | $86,59\,ms$ |
| **10.000** | $10^4$ B | $13.000,58\,\mu s$ | $13,00\,ms$ | $41.544,49\,\mu s$ | $41,54\,ms$ |
| **100.000** | $10^5$ B | $64.658,75\,\mu s$ | $64,66\,ms$ | $142.232,87\,\mu s$ | $142,23\,ms$ |
| **1.000.000** | $10^6$ B | $323.765,30\,\mu s$ | $323,77\,ms$ | $829.970,93\,\mu s$ | $829,97\,ms$ |

---

## 3. Análisis Teórico de los Resultados

### ❓ a) ¿Las diferencias de tiempos en `write()` son proporcionales a las cantidades de datos? (Ej: ¿$1000$ bytes toma 10 veces más que $100$ bytes?)

**Respuesta: NO son proporcionales para datos pequeños ($10^1$ a $10^3$ bytes), pero escalan fuertemente para volúmenes grandes ($10^5$ a $10^6$ bytes).**

* **Datos Pequeños ($10$ B a $1000$ B)**: 
  * Enviar 100 bytes tomó $2,38\,ms$ y enviar 1000 bytes tomó $2,90\,ms$. 
  * **Observación**: ¡Multiplicar la cantidad de datos por 10 sólo incrementó el tiempo de `write()` un $21\%$!
  * **Causa**: `write()` en sockets TCP copia los datos desde el buffer de usuario al *Socket Send Buffer* del Kernel en memoria RAM. Esta copia es casi instantánea para bloques pequeños.
* **Datos Grandes ($10^5$ a $10^6$ bytes)**: 
  * Al pasar de $100\,\text{KB}$ ($64,66\,ms$) a $1\,\text{MB}$ ($323,77\,ms$), el tiempo aumenta $5$ veces.
  * **Causa**: El volumen de datos excede la ventana TCP y el MTU de la interfaz física ($1500$ bytes). La función `write()` debe pausarse (bloquearse) a la espera de confirmaciones ACK del receptor para poder liberar espacio en el buffer del kernel y continuar escribiendo.

---

### ❓ b) ¿El tiempo de la función `read()` se mantiene constante?

**Respuesta: Varía en una franja moderada debido al jitter de la red Wi-Fi, pero está acotado ya que la respuesta esperada es siempre de tamaño fijo (255 bytes).**

* `read()` en el cliente espera recibir el mensaje de confirmación fijo de 255 bytes enviado por el servidor.
* Para paquetes de $1\,\text{MB}$, el tiempo de `read()` ($829,97\,ms$) incluye la espera de que el servidor termine de procesar e ingresar el último bloque enviado por el cliente antes de emitir la confirmación final.

---

## 4. Código Fuente Empleado

* [server.c](file:///C:/Users/josef/Facultad/Cuarto/PDyTR/Tps/TP2/res/server.c)
* [client.c](file:///C:/Users/josef/Facultad/Cuarto/PDyTR/Tps/TP2/res/client.c)
