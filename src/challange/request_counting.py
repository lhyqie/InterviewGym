"""
Request counting
A large number of customers requested a cleaning service on Redbeacon. Each of them provided a moment in time when the service needs to be completed.
Every professional that can provide cleaning services is available only within a certain time frame.
Given the times for the requested services and the available time frame for each professional
Your task is to
write a function that computes the number of requests that each professional can access within their time frame (one per line)
Note that your function will receive the following arguments:
requests
array of integer numbers representing the timestamp when the request needs to be completed
pro_start
array of integer numbers representing the timestamp for each professional when his availability time frame starts
pro_end
array of integer numbers representing the corresponding timestamp for each professional when his availability time frame ends
e.g. the availability time frame for professional i is given by pro_start[i] and pro_end[i]
Data constraints
the number of requests and professionals will not exceed 50,000
Example
Input    Output
requests: [6, 5, 2, 3]
pro_start: [1, 4]
pro_end: [5, 6]
3
2
Explanation
The first professional is available between timestamps 1 and 5. He could access requests from timestamp 2, 3 and 5. In total 3 requests.
"""


"""
*Note*  my solution is a lot more than 2 seconds
Hope to find a much faster solution soon
"""


def loadFromFile(filepath):
    return open(filepath, 'r').read().split(',')

requests = loadFromFile('../../data/request_counting_requests.txt')
pro_start = loadFromFile('../../data/request_counting_pro_start.txt')
pro_end = loadFromFile('../../data/request_counting_pro_end.txt')
#print len(requests)
#print len(pro_start)
#print len(pro_end)


#requests = [50, 1, 50, 1, 50, 23]
#pro_start = [-1, 50, 54, 24, 6, 45]
#pro_end = [49, 50, 54, 26, 6, 55]

def count_requests(requests, pro_start, pro_end):
    # Write your code here
    # To print results to the standard output you can use print
    # Example: print "Hello world!"
    requests.sort()
    #rint requests
    n = len(requests)
    
    first_occ = {}  # first occurence of d
    last_occ = {}   # last occurence of d
    for i in xrange(len(requests)):
        d = requests[i]
        if d not in first_occ:
            first_occ[d] = i
        if d not in last_occ:
            last_occ[d] = i
        else:
            last_occ[d] = i
    
    for i in xrange(len(requests)):
        d = requests[i]
        # d, first_occ[d], last_occ[d]
    
    for idx in xrange(len(pro_start)):
        #print 'idx = ', idx
        start = pro_start[idx]
        end = pro_end[idx]
        if start > requests[-1]: 
            print 0
            continue 
        
        if end < requests[0]:
            print 0
            continue 
        
        if start < requests[0]:
            start = requests[0]
        elif start not in first_occ:
            for e in requests:
                if e > start:
                    start = e
                    break
        
        if end > requests[-1]:
            end = requests[-1]
        elif end not in last_occ:
            for e in reversed(requests):
                if e < end:
                    end = e
                    break
                    
        #print 'start = ', start, 'end = ', end
        if start <= end :
            #print last_occ[end], first_occ[start]
            print last_occ[end] - first_occ[start] + 1
        else:
            print 0
    
count_requests(requests, pro_start, pro_end)