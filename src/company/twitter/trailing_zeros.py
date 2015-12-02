from math import factorial

a = factorial(10000)

cnt = 0
while a % 10 == 0:
    a/= 10
    cnt += 1
    
print cnt 