import numpy as np
import matplotlib.pyplot as plt

x = np.linspace(-3, 3, 400)
y = np.sqrt(x**3 + 7)
plt.plot(x, y, label="y^2 = x^3 + 7", color='blue')
plt.plot(x, -y, color='blue')
plt.legend()
plt.title("Visualización de secp256k1 en el espacio real")
plt.xlabel("x")
plt.ylabel("y")
plt.grid(True)
plt.show()