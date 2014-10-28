
from __future__ import print_function
from operator import mod
from fractions import gcd
''' 
import print_function to be backward compatible to python 2 
import gcd to check private key
impoort mod operator to encrypt and decrypt message
'''

plainText = "studieren ist schwer"


def RSAEncrypt(N, privkey, plainText):
    e = privkey
    C = []  # encrypted list
    i = 2
    p = N   # must be a prime
    while i * i < p:    # get higher prime factor
        while p % i == 0:
            p = p / i
        i = i + 1
    q = N / p      # another prime but p!=q  #N = p * q   # RSA modulus
    if isprime(q):
        phiN = (q - 1) * (p - 1)  # Euler's phi function
        if gcd(e, phiN) == 1:  # has to be 1 < e < phiN and gcd(e,phiN)=1
            d = pow(int(e), int(phiN) - 1, int(phiN)) # return x to the power y, modulo z
            for ch in plainText:
                C.append(chr(mod(ord(ch) ** e, N)))       # fill list C with encrypted characters
            print("Encrypted: ", C)
            return(N, d, C) # return N, d, and encrypted message for the receiver
        else:
            print("False value for e \nHas to be 1 < e < phiN and gcd(e,phiN)=1")
            return 0
    else:
        print("False value for N \nPrime factorization failed")
        return 0


def RSADecrypt(decrypt):
    if decrypt == 0:    # check if returned 0 (error)
        return 0
    D = []
    N = decrypt[0]
    pubkey = decrypt[1]
    cryptedMsg = decrypt[2]
    for i, ch in enumerate(cryptedMsg):
        D.append(mod(ord(ch) ** pubkey, N))
        print("Decrypted: ", chr(D[i]))


def isprime(n):
    if n == 2:
        return 1
    if n % 2 == 0:
        return 0
    max = n**0.5+1
    i = 3
    while i <= max:
        if n % i == 0:
            return 0
        i+=2
    return 1

values = RSAEncrypt(187, 7, plainText)
RSADecrypt(values)
