class Solution(object):
    
    map = {0: "", 1: "One", 2:"Two", 3:"Three", 4:"Four",
               5: "Five", 6:"Six", 7:"Seven", 8:"Eight",
               9: "Nine", 10:"Ten",
               11: "Eleven", 12: "Twelve", 13: "Thirteen", 14:"Fourteen",
               15: "Fifteen", 16: "Sixteen", 17 : "Seventeen", 18 : "Eighteen",
               19: "Nineteen", 
               20:"Twenty", 30:"Thirty",
               40:"Forty", 50:"Fifty", 60:"Sixty", 70:"Seventy",
               80:"Eighty", 90:"Ninety"}
        
    levelmap = { 0: "", 1:"Thousand", 2:"Million", 3:"Billion"}
        
    def numberToWords(self, num):
        """
        :type num: int
        :rtype: str
        """
        if num == 0 : return "Zero"
        groups = []
        num = str(num)
        n = len(num)
        i = n
        while i > 0:
            if i >= 3:
                groups.append(num[i-3:i])
            else:
                groups.append(num[:i])
            i -= 3
        print groups
    
        res = []
        for i in range(0,len(groups))[::-1]:
            group = groups[i]
            groupvalue = self.toEnglish(group)
            if len(groupvalue) > 0:
                res.append(groupvalue)
                if len(self.levelmap[i]) > 0:
                    res.append(self.levelmap[i])
        return ' '.join(res).strip()
    
    def toEnglish(self, num):
        num = int(num)
        ret = []
        if num / 100 > 0:
            ret.append(self.map[num/100] +" Hundred")
            num %= 100
        if num < 20:
            if len(self.map[num]) > 0:
                ret.append(self.map[num])
        else:
            if len(self.map[num - num % 10]) > 0:
                ret.append(self.map[num - num % 10])
            num %= 10
            if len(self.map[num]) > 0:
                ret.append(self.map[num])
        return ' '.join(ret)


s = Solution()
print s.numberToWords(1000000)
