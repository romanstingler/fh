'''
Created on Nov 21, 2013

@author: marc
'''

import sympy as sym

def CalcInitialValProblem(X, x0, t0,b):
    t = sym.Symbol('t', real=True)
    x =X * (X.subs(t, t0)).inv() * x0
    cdot = X.inv() * b
    ct = sym.zeros(len(b),1)
    for elem in range(len(b)):
        ct[elem] = sym.integrate(cdot[elem], (t,t0,t),manual=True)
    x += X * ct

    return x
