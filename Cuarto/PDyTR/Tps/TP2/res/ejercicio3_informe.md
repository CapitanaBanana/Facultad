# Ejercicio 3: Experimento de Comunicación Ping-Pong (Echo Simétrico)

Este documento contiene los **datos empíricos reales obtenidos en la corrida del usuario**, la comparación con el Ejercicio 2 y las conclusiones del experimento Ping-Pong simétrico.

---

## 1. Resultados Empíricos Reales del Experimento Ping-Pong

Valores reales medidos en la corrida del cliente Ping-Pong:

| Tamaño Enviado (Bytes) | Tamaño $10^x$ | $T_{RTT}$ Medido ($\mu s$) | $T_{RTT}$ Medido ($ms$) | $T_{\text{unidireccional}} = \frac{T_{RTT}}{2}$ ($\mu s$) | $T_{\text{unidireccional}}$ ($ms$) |
| :---: | :---: | :---: | :---: | :---: | :---: |
| **10** | $10^1$ B | $53.844,44\,\mu s$ | $53,84\,ms$ | **$26.922,22\,\mu s$** | **$26,92\,ms$** |
| **100** | $10^2$ B | $46.793,67\,\mu s$ | $46,79\,ms$ | **$23.396,83\,\mu s$** | **$23,40\,ms$** |
| **1.000** | $10^3$ B | $33.375,72\,\mu s$ | $33,38\,ms$ | **$16.687,86\,\mu s$** | **$16,69\,ms$** |
| **10.000** | $10^4$ B | $328.227,25\,\mu s$ | $328,23\,ms$ | **$164.113,63\,\mu s$** | **$164,11\,ms$** |
| **100.000** | $10^5$ B | $2.472.588,96\,\mu s$ | $2.472,59\,ms$ | **$1.236.294,48\,\mu s$** | **$1.236,29\,ms$ ($1,24\,s$)** |
| **1.000.000** | $10^6$ B | $21.711.707,32\,\mu s$ | $21.711,71\,ms$ | **$10.855.853,66\,\mu s$** | **$10.855,85\,ms$ ($10,86\,s$)** |

---

## 2. Comparativa Directa entre Ejercicio 2 y Ejercicio 3

| Tamaño (Bytes) | Ejercicio 2: `write()` ($ms$) | Ejercicio 2: `read()` ACK 255B ($ms$) | Ejercicio 3: $T_{RTT}$ Ping-Pong ($ms$) | Ejercicio 3: $T_{\text{unidireccional}}$ ($ms$) |
| :---: | :---: | :---: | :---: | :---: |
| **10** ($10^1$) | $1,01\,ms$ | $42,86\,ms$ | $53,84\,ms$ | **$26,92\,ms$** |
| **100** ($10^2$) | $2,38\,ms$ | $9,39\,ms$ | $46,79\,ms$ | **$23,40\,ms$** |
| **1.000** ($10^3$) | $2,90\,ms$ | $86,59\,ms$ | $33,38\,ms$ | **$16,69\,ms$** |
| **10.000** ($10^4$) | $13,00\,ms$ | $41,54\,ms$ | $328,23\,ms$ | **$164,11\,ms$** |
| **100.000** ($10^5$) | $64,66\,ms$ | $142,23\,ms$ | $2.472,59\,ms$ | **$1.236,29\,ms$** |
| **1.000.000** ($10^6$) | $323,77\,ms$ | $829,97\,ms$ | $21.711,71\,ms$ | **$10.855,85\,ms$** |

---

## 3. Conclusiones y Análisis Comparativo

1. **Crecimiento de $T_{RTT}$ con el Volumen de Datos**:
   * En el Ejercicio 2, como la respuesta del servidor era fija (255 bytes), el tiempo de `read()` se mantuvo acotado en milisegundos.
   * En el Ejercicio 3 (Ping-Pong), el servidor debe enviar el payload de $1\,\text{MB}$ completo de regreso. Esto provoca que el canal de red de ida y vuelta se sature por completo en ambas direcciones, elevando el $T_{RTT}$ a **$21,71$ segundos** para $10^6$ bytes.

2. **Estimación Unidireccional ($T_{RTT}/2$)**:
   * Dividir $T_{RTT} / 2$ proporciona la estimación promedio del tiempo de vuelo unidireccional de la ráfaga de datos en un solo sentido.
   * Para $10^6$ bytes ($1\,\text{MB}$), la latencia de envío en un sentido se estima en **$10,85$ segundos**.

---

## 4. Código Fuente Empleado

* [server_pingpong.c](file:///C:/Users/josef/Facultad/Cuarto/PDyTR/Tps/TP2/res/server_pingpong.c)
* [client_pingpong.c](file:///C:/Users/josef/Facultad/Cuarto/PDyTR/Tps/TP2/res/client_pingpong.c)
