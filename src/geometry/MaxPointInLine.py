class Point:
    def __init__(self, a=0, b=0):
        self.x = a
        self.y = b

class Solution:
    # @param points, a list of Points
    # @return an integer
    def maxPoints(self, points):
        ans = 0
        size = len(points)
        for x in range(size):
            k_dict = {}
            same = 0
            for y in range(x + 1, size):
                if self.isEqual(points[x], points[y]):
                    same += 1
                else:
                    k = self.getK(points[x], points[y])
                    if k_dict.get(k) is None:
                        k_dict[k] = 1
                    else:
                        k_dict[k] += 1
            val = 0
            if len(k_dict):
                val = max(k_dict.values())
            print val + same + 1
            ans = max(ans, val + same + 1)
            
        return ans
    def getK(self, pa, pb):
        if pa.x == pb.x:
            return None
        return 1.0 * (pb.y - pa.y) / (pb.x - pa.x)
    def isEqual(self, pa, pb):
        return pa.x == pb.x and pa.y == pb.y

input = [[84,250],[0,0],[1,0],[0,-70],[0,-70],[1,-1],[21,10],[42,90],[-42,-230]]
points = [Point(e[0], e[1]) for e in input]
app = Solution()
print app.maxPoints(points)

