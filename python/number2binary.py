def convert_to_binary(num, length):
    # 0 meaning filling heading 0s, 
    # {} is a place holder for length,  
    # b means binary format
    # '0{}b'.format(length) format number in binary string whose length is "length" 
    binary = format(num, '0{}b'.format(length)) #list(format(num, '0{}b'.format(length)))   
    return binary

def ones_compliment(binary, length):
    """
    if binary is positive, return itself
    else 0-1 reverse the binary string except the first bit, replace '0' with '1', '1' with '0'
    """
    if binary[0] == '0':
        return binary
    elif binary[0] == '1':
        return binary[0] + ''.join(['0' if e == '1' else '1' for e in binary[1:]])

def twos_compliment(binary, length):
    """
    if binary is positive, return itself
    else first compute ones_compliment and add 1 to it
    """
    if binary[0] == '0': return binary
    binary = ones_compliment(binary, length)
    #convert binary string to an integer array of 0's and 1's
    bitArray = map(int, binary)
    i = length - 1
    # now add 1 to binary
    c = 1 # carrier
    while i >= 1:
        bitArray[i] += c
        if bitArray[i] == 2:
            c = 1
            bitArray[i] = 0
        else:
            c = 0
            break
        i-= 1
    return ''.join(map(str, bitArray))

def format_binary(n, length):
    """
        give the binary representation of a decimal
        if n == 0 :    the binary string will be all 0's
        if n > 0 :     the binary string will be the direct translation of decimal to binary
        if n < 0 :     the binary string will be the the two_compliments of decimal
         
    """
    if n == 0: return convert_to_binary(0, length)
    neg = n < 0;
    if neg: n = -n;
    s = convert_to_binary(n, length)
    if neg: 
        s = '1' + s[1:]
        s = twos_compliment(s, length)
    return s
def parse_binary(s, length):
    """
    given a binary string of an integer, either positive, negative or all 0's
    return the decimal value of it
    """
    if s[0] == '0':
        return int(s, base=2)
    else:
        s = twos_compliment(s, length)
        #print 's=',s
        res = -int(s[1:], base=2)
        if res == 0:  # negative 0 is reserved for -2^(length-1)
            return -2**(length-1)
        else:
            return res

length = 3
print 'number of bits = ', length
for num in range(-4, 4):  #[-4....3]
    s = format_binary(num, length)
    d = parse_binary(s, length)
    print 'number is {}, \t binary string is {}, \t parsing {} = {}'.format(num, s, s, d)
 
print 
 
 
length = 4
print 'number of bits = ', length
for num in range(-4, 4):  #[-4....3]
    s = format_binary(num, length)
    d = parse_binary(s, length)
    print 'number is {}, \t binary string is {}, \t parsing {} = {}'.format(num, s, s, d)
 
print 
 
length = 4
print 'number of bits = ', length
for num in range(-8, 8):  #[-8....7]
    s = format_binary(num, length)
    d = parse_binary(s, length)
    print 'number is {}, \t binary string is {}, \t parsing {} = {}'.format(num, s, s, d)

print
length = 32
print 'number of bits = ', length
for num in range(-8, 8):  #[-8....7]
    s = format_binary(num, length)
    d = parse_binary(s, length)
    print 'number is {}, \t binary string is {}, \t parsing {} = {}'.format(num, s, s, d)
