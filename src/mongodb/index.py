import pymongo

client = pymongo.MongoClient('localhost', 27017)
db = client['TB']
#cur = db.users.index_information()
#for idx in cur:
#    if cur[idx]['key'][0][0] == 'username':
#        print idx
#        db.users.drop_indexes()
#    db.users.create_index("email")


db.users.drop_indexes()
db.users.create_index("email")