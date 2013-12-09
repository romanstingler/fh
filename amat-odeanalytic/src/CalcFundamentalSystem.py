'''
Created on Nov 20, 2013

@author: marc
'''

import sympy as sym
import numpy as num

def CountertoList(countobj):
    elist = []
    ecount = []
    for elem in countobj:
        elist.append(elem)
        ecount.append(countobj[elem])
    return {'elist':elist, 'ecount':ecount}
        
def CalcFundSys(mat, basis, lam, deg, eps=1e-5):
    nlam = len(lam)
    n = basis.shape[0]

    t = sym.Symbol('t', real=True)
    u = num.zeros(nlam, sym.Matrix)
    x = num.zeros(nlam, sym.Matrix)
    E = num.matrix(num.eye(n), 'complex')
    tmp = CountertoList(lam)
    lamlist = tmp['elist']
    lamocc = tmp['ecount']
        
    i = 0
    pos = 0
    for lamc in lam:
        u[i] = sym.zeros(n, lam[lamc])
        x[i] = sym.zeros(n, lam[lamc])
        
        for j in range(lam[lamc]):
            M = E
            H = E
            vij = basis[:, pos]
            dij = deg[pos]
            for k in range(1, dij):
                H = H * (mat - lamc * E)
                M = M + t ** (k) / sym.factorial(k) * sym.Matrix(H)
            u[i][:, j] = M * vij
            if abs(lamc.imag) > eps:
                if  lamlist.index(num.conj(lamc)) < lamlist.index(lamc):
                    for row in range(mat.shape[1]):
                        x[i][row, j] = sym.exp(lamc.real * t) * (sym.cos(num.conj(lamc).imag * t) * sym.re(u[i][row, j]) - sym.sin(num.conj(lamc).imag * t) * sym.im(u[i][row, j]))
                        x[lamlist.index(num.conj(lamc))][row, j] = sym.exp(lamc.real * t) * (sym.sin(lamc.imag * t) * sym.re(u[lamlist.index(num.conj(lamc))][row, j]) + sym.cos(lamc.imag * t) * sym.im(u[lamlist.index(num.conj(lamc))][row, j]))
            else:
                x[i][:, j] = sym.exp(lamc * t) * u[i][:, j]
            pos += 1
        i += 1
        
    X = sym.eye(n)
    pos = 0
    for i in range(nlam):
        X[:, pos:pos + x[i].shape[1]] = x[i]
        pos += x[i].shape[1]
        
    Xdot=sym.zeros(n,n)
    for elem in range(n ** 2):
        Xdot[elem]=sym.diff(X[elem],t)
    
    return {'X':X, 'Xdot':Xdot}
