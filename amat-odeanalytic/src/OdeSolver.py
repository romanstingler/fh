import numpy
from scipy.integrate import odeint
import matplotlib as mpl
from mpl_toolkits.mplot3d import Axes3D
from pylab import *
import sympy as sym

def euler(f, x0, t):
    n = len(t)
    x = numpy.array([x0] * n, dtype='d')
    for i in range(n - 1):
        x[i + 1] = x[i] + (t[i + 1] - t[i]) * f(x[i], t[i])
    return x


def heun(f, x0, t):
    n = len(t)
    x = numpy.array([x0] * n, dtype='d')
    for i in range(n - 1):
        h = t[i + 1] - t[i]
        k1 = h * f(x[i], t[i])
        k2 = h * f(x[i] + k1, t[i + 1])
        x[i + 1] = x[i] + (k1 + k2) / 2.0
    return x

def odeplot(x_analytic):
    abserr = 1e-8
    relerr = 1e-6
    stoptime = 20
    numpoints = 250
    tn = [stoptime * float(i) / (numpoints - 1) for i in range(numpoints)]
    t = sym.Symbol('t', real=True)
    x0 = numpy.array([0, 1, 0, 1, 0, 1])
    x_exact = numpy.zeros((len(tn), len(x0)))
    
    k = 0
    for i in tn:
        x_exact[k] = np.asarray(x_analytic.subs(t, i).evalf()).T
        k += 1
        
    def f(x0, tn):
        e = 1.602 * 10 ** -19
        B0 = 10 ** -11
        m0 = 9.109 * 10 ** -31
        w = e * B0 / m0
        q1 = x0[1]
        q2 = -w * x0[3]
        q3 = x0[3]
        q4 = w * x0[1]
        q5 = x0[5]
        q6 = 0
        return (numpy.array([q1, q2, q3, q4, q5, q6]))

    wsol = odeint(f, x0, tn, args=(), atol=abserr, rtol=relerr)
    x_euler = euler(f, x0, tn)
    x_heun = heun(f, x0, tn)

    fig = plt.figure()
    ax = fig.add_subplot(1, 2, 1, projection='3d', label='electron movement')
    ax.plot(x_exact[:, 0], x_exact[:, 2], x_exact[:, 4], 'r', label='analytic')
    ax.plot(x_euler[:, 0], x_euler[:, 2], x_euler[:, 4], 'b.', label='euler')
    ax.plot(x_heun[:, 0], x_heun[:, 2], x_heun[:, 4], 'y+', label='heun')
    ax.plot(wsol[:, 0], wsol[:, 2], wsol[:, 4], 'k-', label='LSODA')
    ax.set_xlabel("X Axis")
    ax.set_ylabel("Y Axis")
    ax.set_zlabel("Z Axis")
    legend(('Analytic','Euler', 'Heun', 'LSODA'), loc='upper left')
    title('Solutions of electron movement')

    subplot(1, 2, 2)
    plot(tn, numpy.sqrt(x_euler[:, 0] ** 2 - wsol[:, 0] ** 2 + x_euler[:, 2] ** 2 - wsol[:, 2] ** 2 + x_euler[:, 4] ** 2 - wsol[:, 4] ** 2), 'b-x',
         tn, numpy.sqrt(x_heun[:, 0] ** 2 - wsol[:, 0] ** 2 + x_heun[:, 2] ** 2 - wsol[:, 2] ** 2 + x_heun[:, 4] ** 2 - wsol[:, 4] ** 2), 'g')
    xlabel('$t$')
    ylabel('$x - x^*$')
    title('Errors in solutions')
    legend(('Euler', 'Heun', 'LSODA'), loc='upper left')
    show()
