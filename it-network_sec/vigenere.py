from __future__ import print_function
''' import print_function to be backward compatible to python 2 '''


plainText = "im technischen bereich kann man an der fachhochschule kaernten zwischen mehreren bachelorstudiengaengen sowie masterstudiengaengen auswaehlen"
key1 = "blue"
key21 = "blue"
key22 = "abc"


def vigenere(plainText, *key):
    cnt = 0                             # position counter of the actual keyN
    for i, keys in enumerate(key):      # generate for loop with number i for each element in key
        for ch in plainText:            # each character in plainText
            if not ch == " ":           # remove spaces to prevent easy decryption
                if ch.isalpha():        # implementation just for alphabetic characters
                    alph2num = ord(
                        ch) + (ord(keys[cnt % len(key[i])]) - 97)   # convert character to unicode, and start a = 1 therefore offset would equal -96 but due to the modulo operator of the shift operation which equals to 0 we have to increase the offset to -97
                    if alph2num > ord("z"):     # check if unicode of shifted character is higher than the unicode of z
                        alph2num -= 26          # if unicode is higher than z, start at the beginning
                print(chr(alph2num))            # print and transform from unicode to character
                cnt = cnt + 1                   # increase the position counter of the actual key


#vigenere(plainText, key1)
vigenere(plainText, key21, key22)
