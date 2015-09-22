# python iterator does not have "hasNext()" 
class ListIterator:
    it = None
    buffer = None
    def __init__(self, list):
        self.it = iter(list)
        try:
            self.buffer = self.it.next()
        except:
            self.buffer = None
            
    def hasNext(self):
        return self.buffer is not None
    
    def next(self):
        tmp = self.buffer
        try:
            self.buffer = self.it.next()
        except :
            self.buffer = None
        return tmp
        
x = [1,2,3, [40, 50, [600, 700], 80], 9 ]


it = ListIterator(x)
s = [it]
while len(s) > 0:
    it = s[-1]
    if it.hasNext():
        obj = it.next()
        if type(obj) is int:
            print obj
        elif type(obj) is list:
            s.append(ListIterator(obj))
    else:
        s.pop();

