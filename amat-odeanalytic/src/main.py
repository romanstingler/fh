'''
Created on Nov 18, 2013

@author: marc1
'''
from numpy import matrix, set_printoptions
from numpy.linalg import matrix_rank
from CalcGenVectorBasis import CalcGenVectorBasis
from CalcFundamentalSystem import CalcFundSys
from CalcInitialValProblem import CalcInitialValProblem
from CalcGeneralSolution import CalcGeneralSolution
from sympy import init_printing
import sympy as sym

import argparse
from pprint import pprint

if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Script for finding the fundamental matrix of a system.')
    parser.add_argument('-m', '--matrix', help='Matrix', required=True)
    parser.add_argument(
        '-x', '--initialvalue', help='Initial value', required=False)
    parser.add_argument(
        '-t0', '--initialt', help='Initital value t', required=False)

    args = parser.parse_args()
    a = matrix(args.matrix)
    t = sym.Symbol('t', real=True)
    b = sym.exp(2 * t) * sym.Matrix([[0], [1], [1]])
    x0 = matrix(args.initialvalue)
    t0 = args.initialt

    set_printoptions(precision=4)
    genVectors = CalcGenVectorBasis(a)
    fundSystem = CalcFundSys(
        a, genVectors['genBasis'], genVectors['lam'], genVectors['deg'])
    genSolution = CalcGeneralSolution(fundSystem['X'], b)
    if x0.shape[0]:
        valProb = CalcInitialValProblem(fundSystem['X'], x0, t0, b)

    print("Matrix A: ")
    print(a)
    print("\n")

    print("Eigenvalues: ")
    print(genVectors['lam'])
    print("\n")

    print("Basis of eigenvectors: ")
    print(genVectors['genBasis'])
    print("\n")

    print("Degree of eigenvectors: ")
    print(genVectors['deg'])
    print("\n")

    print("Rank of basis: ")
    print(matrix_rank(genVectors['genBasis']))
    print("\n")

    print("Fundamental system:")
    print(sym.simplify(fundSystem['X']))
    sym.pprint(sym.simplify(fundSystem['X']))
    print("\n")

    print("check for correctness of fundamental system Xdot = A*X:")
    sym.pprint(sym.simplify(fundSystem['Xdot'] - fundSystem['X'] * a))
    print("\n")

    print("General solution:")
    print(sym.simplify(genSolution))
    sym.pprint(sym.simplify(genSolution))

    if x0.shape[0]:
        print("Solution of the initial value problem:")
        print(sym.simplify(valProb))
        sym.pprint(sym.simplify(valProb))


pass
