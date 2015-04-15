# a good tool to visualize
# http://www.derivative-calculator.net/

"""
Price experiment
Uber is a company based in San Francisco, California that makes a mobile application that connects passengers with drivers of luxury vehicles for hire.
Uber is doing experiments to better understand the relationship between price and demand for rides. One hypothesis is that lower prices will show an increase in demand for cars.
Their goal is to find the cost/demand point that is best for everyone.
Experimental data will show that the relation between the price discount and the uber revenue can be approximated by a mathematical function. Let¡¯s consider the following function:
y = 1/2 (¡Ì(-3x^2 + Ax + B) - x - 1)
where
x represents the discount,
y represents the uber revenue and
A, B are two constant positive integers
The relation described above has a shape similar to the one shown below:

Given the two positive integer numbers A and B
Your task is to
write a function that prints to the standard output (stdout) the discount value x that maximizes uber revenue y
please print the found value x truncated to two decimal places without rounding.
Note that your function will receive the following arguments:
a
the positive integer number A
b
the positive integer number B
Data constraints
the values of a, b constants will not exceed 2000
Efficiency constraints
your function is expected to print the requested result and return in less than 2 seconds
Example
Input    Output
a: 250
b: 400
20.04
"""
from math import sqrt

def f(x, a, b):
    return 0.5 * ( sqrt(-3 * x *x + a * x + b) - x - 1)

def f_prime(x, a, b):
    t1 = a - 6 * x
    t2 = 4 * sqrt(-3*x*x + a*x +b) 
    return t1 / t2 - 0.5

def f_prime_prime(x, a, b):
    pass

# def newt(x,n, a, b):
#     for i in range(n):
#         #if f_prime(x, a, b) == 0:
#         #    return x
#         print x, f_prime(x,a,b), f(x, a, b)
#         x = x + 0.1 *f_prime(x,a,b) #f(x, a, b)/f_prime(x,a,b)
#     return x

def gd(x, max_iter, alpha , a, b):
    for i in range(max_iter):
        x = x + alpha * f_prime(x,a,b)
    return x

def uber_price(a, b):
    # Write your code here
    # To print results to the standard output you can use print
    # Example: print "Hello world!"
    x = 20.0
    alpha = 1
    x = gd(x, 3000, alpha,  float(a), float(b))
    print int(x*100) *1.0/100
    
uber_price(250, 400)