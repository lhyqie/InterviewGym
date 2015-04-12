"""
Reducer
As you remember, the mappers previously produced key-value pairs of data - the key was the string we were searching for and the value was a list of matched documents.

We are now entering the Reducer phase of the MapReduce. Each reducer will receive the lists of matched documents produced by all mappers for a given string.
Our task is to simply merge the given lists of documents.
Given an array containing lists of sorted document IDs
Your task is to
write a function that prints to the standard output (stdout) the sorted document IDs obtained after merging the given lists
all IDs in the merged list must be unique
Note that your function will receive the following arguments:
docIds
which is an array of strings
each string contains a list of sorted document IDs separated by comma
Data constraints
all document IDs are integer numbers
each string in the docIds array will contain at most 100,000 IDs
the length of the docIds array will not exceed 1000 elements
Efficiency constraints
your function is expected to print the requested result and return in less than 2 seconds
expected complexity: O(N * logK), N = total number of IDs in all lists, K = total number of lists
Example
Input    Output
docIds: "1,2,3", "2,3", "2,5"
1 2 3 5
"""

#class HeapItem:
#    def __init__(self, word, docid):
#        self.word = word
#        self.docid = docid
#    def __eq__(self, other):
#        return self.word == other.word
#    def __lt__(self, other):
#        return self.word < other.word
        
docs = ["1,2,3", "2,3", "2,5"]

def mr_reduce(docs):
    res = []
    import Queue as Q
    q = Q.PriorityQueue() 
    docs = [map(int, doc.split(",")) for doc in docs]
    ptrs = [0 for i in xrange(len(docs))]
    for i, words in enumerate(docs):
        q.put((words[0],i))
        ptrs[i] += 1
    while not q.empty():
        e = q.get()
        print e
        res.append(e[0])
        docId = e[1]
        if( ptrs[docId] < len(docs[docId]) ):
            q.put((docs[docId][ptrs[docId]], docId))
            ptrs[docId] += 1
   
    for word in sorted(list(set(res))):
        print word
        
mr_reduce(docs)