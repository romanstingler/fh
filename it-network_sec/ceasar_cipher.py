from __future__ import print_function
''' import print_function to be backward compatible to python 2 '''

plainText = "im technischen bereich kann man an der fachhochschule kaernten zwischen mehreren bachelorstudiengaengen sowie masterstudiengaengen auswaehlen"
shift = "j"


def caesar(plainText, shift):
    for ch in plainText:
        if not ch==" ":     # remove spaces to prevent easy decryption
            if ch.isalpha():    # implementation just for alphabetic characters
                alph2num = ord(ch) + (ord(shift) - 96) # convert character to unicode, and start a = 1 therefore offset equals -96
                if alph2num > ord("z"):     # check if unicode of shifted character is higher than the unicode of z
                    alph2num -= 26          # if unicode is higher than z, start at the beginning
            print(chr(alph2num))            # print and transform from unicode to character


caesar(plainText, shift)