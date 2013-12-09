'''
Created on Nov 22, 2013

@author: marc
'''
import sympy as sym

def CalcGeneralSolution(X, b):
    t = sym.Symbol('t', real=True)
    c = sym.Matrix(X.shape[0], 1, lambda i,j:sym.Symbol('c_%d' % (i + 1)))
    x = X * c
    cdot = X.inv() * b
    ct = sym.zeros(len(b), 1)
    for elem in range(len(b)):
        ct[elem] = sym.integrate(cdot[elem], t,manual=True)
        x += X * ct

    return x
