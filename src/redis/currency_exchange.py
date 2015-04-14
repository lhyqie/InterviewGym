import redis
import json

pool = redis.ConnectionPool(host='localhost', port=6379)
r = redis.Redis(connection_pool=pool)

def update_currency_price(bank_name, currency, price):
    tuples = r.get(bank_name)
    if tuples is None:
        tuples = {}
    else:
        tuples = json.loads(tuples)
    tuples[currency] = price
    #print tuples
    r.set(bank_name, json.dumps(tuples))

def find_currency_price(bank_name, currency):
    # Write your code here
    # To print results to the standard output you can use print
    # Example: print "Hello world!"
    tuples = r.get(bank_name)
    tuples = json.loads(tuples)
    print tuples[currency]
    
update_currency_price('bank1','cur1',1.0)
update_currency_price('bank1','cur2',3.0)

find_currency_price('bank1','cur1')
find_currency_price('bank1','cur2')

update_currency_price('bank1','cur1',2.0)
find_currency_price('bank1','cur1')