import redis

pool = redis.ConnectionPool(host='localhost', port=6379)
r = redis.Redis(connection_pool=pool)

def store_user(email, password):
    r.set(email,password)


def check_user_password(email, password):
    # Write your code here
    # To print results to the standard output you can use print
    # Example: print "Hello world!"
    pwd = r.get(email)
    if pwd is None:
        print 'Not found'
    elif pwd == password:
        print 'True'
    else:
        print 'False'

store_user('a@email.com','pwd')
check_user_password('a@email.com', 'pwd1')
check_user_password('a@email.com', 'pwd')
check_user_password('b@email.com', 'pwd')