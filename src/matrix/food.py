# http://www.1point3acres.com/bbs/thread-138233-2-1.html


from collections import deque

def shortestPoint(A):
    m = len(A)
    n = len(A[0])
    psum = [[0]*n for k in range(m)]

    for i in range(m):
        for j in range(n):
            if A[i][j] == 2:
                B = [[-1]*n for k in range(m)]
                B[i][j] = 0
                bfshelper(B, A, i, j, m, n)
                psum = map(lambda pair: map(lambda x, y: x+y, pair[0] , pair[1]),  zip(B,psum))

    mini, minj, maxstep = -1, -1, 2147483647
    for i in range(m):
        for j in range(n):
            if 0 < psum[i][j] < maxstep:
                mini, minj = i, j
                maxstep = psum[i][j]
    return (mini, minj)

def bfshelper(B, A, i, j, m, n):
    Q = deque([(i,j)])

    while Q:
        (i, j) = Q.popleft()
        for x, y in [(i+1,j), (i-1, j), (i, j+1), (i, j-1)]:
            if 0<=x<m and 0<=y<n and B[x][y] == -1 and A[x][y] != 0:
                B[x][y] = B[i][j] + 1
                Q.append( (x, y) )

def Main():
    C = [[0, 2, 1, 1], [0, 1, 2, 0], [1, 1, 1, 1], [1, 0, 0, 2], [1, 2, 0, 0]]
    B = [[2,0,1, 0], [1,1,1, 2], [0,1,0, 0], [1, 1, 1, 2]]
    A = [[1]]
    D = [[2, 1, 0, 0], [1, 2, 0, 1], [1, 0, 2, 1], [0, 1, 2, 1]]

    test = C
    for row in test:
        print row

    print "---"
    print shortestPoint(test)

if __name__ == '__main__':
    Main()
