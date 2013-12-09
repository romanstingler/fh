'''
Created on Nov 18, 2013

@author: marc
'''

from numpy import eye, ones, zeros, matrix, shape, append, nonzero, round, array, inf, isnan
from numpy.linalg import pinv, norm
from scipy.linalg import eigvals, svd, solve, lu
from scipy import compress, transpose
from collections import Counter, OrderedDict
import math

def setZero(array, eps=1e-4):
    array.real[abs(array.real) <= eps] = 0.0
    array.imag[abs(array.imag) <= eps] = 0.0
    array.real[abs(round(array.real) - array.real) <= eps] = round(array.real[abs(round(array.real) - array.real) <= eps])
    array.imag[abs(round(array.imag) - array.imag) <= eps] = round(array.imag[abs(round(array.imag) - array.imag) <= eps])

    return array

def setZeroM(mat, eps=1e-4):
    mat.real[abs(mat.real) <= eps] = 0.0
    mat.imag[abs(mat.imag) <= eps] = 0.0
    mat.real[(abs(round(mat.real) - mat.real) <= eps)] = array(round(mat.real[(abs(round(mat.real) - mat.real) <= eps)]))[0]
    mat.imag[abs(round(mat.imag) - mat.imag) <= eps] = array(round(mat.imag[(abs(round(mat.imag) - mat.imag) <= eps)]))[0]
    
    return mat

def FindFactor(mat, eps=1e-4):
    fac = 1
    for col in range(mat.shape[0]):
        if abs(mat[:,col]).any() >= eps:
            cMinNorm = inf
            for elem in range(mat.shape[1]):
                if abs(mat[elem,col]) >= eps:
                    f = mat[elem,col]
                    tv = mat[:,col] / f
                    if (abs(tv.real - round(tv.real)) <= eps).all() and (abs(tv.imag - round(tv.imag) <= eps)).all() and norm(tv) <= cMinNorm:
                        cMinNorm = norm(tv)
                        fac = f
            if not isnan(fac):
                mat[:,col] = setZeroM(mat[:,col] / fac)
    return mat 

def null(A, eps=1e-4):
    u, s, vh = svd(A)
    null_mask = (s <= eps)
    null_space = compress(null_mask, vh, axis=0)
    
    return transpose(null_space)

def CompleteBase(V, B, eps=1e-4):
    tbase = append(V, B, axis=1)
    p, l, u = lu(tbase)
    echelon = zeros(u.shape[1], int)
    
    for row in u:
        tmp = nonzero(abs(row) > eps)[0]
        if tmp.size:
            echelon[tmp[0]] = 1
  
    return compress(echelon, tbase, axis=1)

def CalcGenVectorBasis(mat, eps=1e-4):
    n = len(mat)
    E = matrix(eye(n), 'complex')
    eigv = setZero(eigvals(mat))
    lam = Counter(eigv)
    sig = len(lam)
    genBasis = matrix(zeros([n, n], 'complex')) 
    
    # One eigenvalue with algebraic multiplicy = n
    if sig == 1:
        for lamc in lam:
            genBasis = E
            deg = ones(n, int)
            for i in range(n):
                while max(abs((mat - lamc * E) ** deg[i] * genBasis[:, i])) > eps:
                    deg[i] += 1
                    if deg[i] > n:
                        deg[i] = 0
                        break
                
    # n eigenvalues with algebraic multiplicy = 1
    elif sig == n:
        deg = ones(n, int)
        i = 0
    
        for lamc in lam:
            muc = lam[lamc]
            genBasis[:, i] = null(mat - lamc * E)
            i += 1
        
    else :
        i = 0
        deg = ones(n, int)
        
        for lamc in lam:
            muc = lam[lamc]
            basis = null(mat - lamc * E)
            rho = basis.shape[1]

            if rho == muc:  
                genBasis[:, i:i + rho] = basis
                i += rho
            else:
                if rho == 1:
                    genBasis[:, i] = basis
                    i += 1
                    genl = 2
                    while(genl <= muc):
                        basis = pinv(mat - lamc * E) * basis
                        genBasis[:, i] = basis
                        deg[i] = genl
                        genl += genl
                        i += 1
                else:
                    curdim = rho
                    order = 1
                    ldim = ones(muc, int)
                    while(curdim < muc):
                        order += 1
                        tbasis = null((mat - lamc * E) ** order)
                        basis = CompleteBase(basis, tbasis) 
                        newdim = basis.shape[1]
                        ldim[curdim : newdim] = order
                        curdim = newdim
                    genBasis[:, i:i + curdim] = basis
                    deg[i:i + curdim] = ldim
                    i += curdim
              
    
    genBasis = setZeroM(genBasis)
    FindFactor(genBasis)               
    return {'genBasis':genBasis, 'lam':lam, 'deg':deg}
