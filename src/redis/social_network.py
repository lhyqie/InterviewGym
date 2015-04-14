import redis

pool = redis.ConnectionPool(host='localhost', port=6379)
r = redis.Redis(connection_pool=pool)

def follow(user_id1, user_id2):
    # Write your code here
    r.sadd(user_id2, user_id1)

def num_followers(user_id):
    # Write your code here
    print len(r.smembers(user_id))

def friends(user_id):
    fs = []
    followers = r.smembers(user_id)
    for follower in followers:
        if user_id in r.smembers(follower):
            fs.append(follower)
    return fs

def list_friends(user_id):
    # Write your code here
    fs = friends(user_id)
    for f in sorted(fs):
        print f

def most_friendly_users(n):
    # Write your code here
    users = r.keys('*')
    all_users = set()
    for user in users:
        all_users = all_users.union(r.smembers(user))
    usercntpair = []
    for user in all_users:
        usercntpair.append((user,len(friends(user))))
    #print usercntpair
    usercntpair = sorted(usercntpair, key=lambda element: (-element[1], -ord(element[0])))
    #print usercntpair
    for user, cnt in usercntpair[:n]:
        print user



#r.flushall()
 
print r.keys('*')
 
# follow("a","o")
# follow("o","a")
# follow("a","v")
# follow("a","v")
# follow("v","a")
# follow("d","v")
#  
# num_followers("v")
# list_friends("a")
# list_friends("v")
# most_friendly_users(4)

# follow('u1','u2')
# follow('u2','u1')
# follow('u1','u3')
# follow('u2','u3')
# follow('u3','u2')
# follow('u4','u2')
# follow('u2','u4')
# follow('u3','u4')
# follow('u4','u3')
#
#
# num_followers('u1')
# num_followers('u2')
# num_followers('u3')
# num_followers('u4')
# 
# 
# most_friendly_users(2)

 
 

