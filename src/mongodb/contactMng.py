"""
Contact management
A sales team needs to keep track of the company's clients. One way to achieve this is to have an intelligent contact management application that allows anyone from the team to add or update information about clients.
Let's implement the backend that will process the requests made by the sales team in this contact management web app.
Your task is to implement the following functions:
create_contact
This function stores the following information in the database:
name of the person that adds the client
name of the client
email of the client
birthday of the client
Notes:
all arguments are strings
contact_birthday has the following format: 'YYYY-MM-DD' (for example: '1988-03-20')
edit_contact
This function modifies a field (given by field) with a new value (given by value) for the contact identified by email (given by contact_email)
Notes:
field can have the following values: "email", "name", "birthday"
a history of who changed (given by author_name) what field must be kept for each contact
field_history
This function must find all the authors that added / modified the field given by field for the contact with the email given by contact_email
The function must print to standard output (stdout) on each line the author and the value that the author added / changed for that specific field, separated by a single space
The lines must be sorted in ascending order by the date the field was modified
get_birthdays
This function must find all contacts that have a birthday in the month given as argument and print their names to standard output (stdout), one on each line, sorted alphabetically
Notes:
month is an integer number between 1 and 12 that represents the month given as argument
To store data, you can connect to a mongodb instance at db.talentbuddy.co:27017
"""
import pymongo
from datetime import datetime

client = pymongo.MongoClient('localhost', 27017)
db = client['TB']


def create_contact(author, name, email, birthday):
    # Write your code here
    db.contact.insert({'name':name, 'email':email,
                       'birthday':birthday})
    db.create_contact.insert({'email':email, 'author': author, 'name':name,
                       'birthday':birthday, 'add_ts': datetime.now()})
    

def edit_contact(author, email, field, value):
    # Write your code here
    db.contact.update({'email':email}, {'$set': {field: value}})
    db.edit_contact.insert({'email': email, 'field': field, 'value': value, 'author':author, 'add_ts': datetime.now()})
    

def field_history(email, field):
    # Write your code here
    cur = db.create_contact.find({'email':email},{'_id':0, 'author':1, field:1})
    for e in cur:
        print e['author']+" "+e[field]
    
    cur = db.edit_contact.find({'email':email, 'field':field}, {'_id':0, 'author':1, 'value':1}).sort([('add_ts', pymongo.ASCENDING)])
    for e in cur:
        print e['author']+" "+e['value']
 
def get_birthdays(month):
    # Write your code here
    # To print results to the standard output you can use print
    # Example: print "Hello world!"
    for e in db.contact.find().sort([('name',pymongo.ASCENDING)]):
        if datetime.strptime(e['birthday'], '%Y-%m-%d').month == month:
            print e['name']

# create_contact('a1', 'c1', 'c1@email.com', '1988-03-20')
# create_contact('a1', 'c2', 'c2@email.com', '1988-03-21')
# create_contact('a1', 'c3', 'c3@email.com', '1988-04-20')
# create_contact('a2', 'c4', 'c4@email.com', '2010-03-20')
# create_contact('a2', 'c5', 'c5@email.com', '2000-03-21')
# create_contact('a3', 'c6', 'c6@email.com', '2011-04-20')
# 
# edit_contact('a1','c6@email.com','name','c66')
# edit_contact('a2','c6@email.com','name','c666')
# edit_contact('a3','c6@email.com','birthday','2015-04-14')


# field_history('c6@email.com', 'name')
# print
# field_history('c6@email.com', 'birthday')
# print 
# 
# get_birthdays(3)
# print
# get_birthdays(4)