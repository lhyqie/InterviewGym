"""
Examples
{"abca", "zbxz", "opqr"}

Returns: 1

"abca" and "zbxz" are isomorphic, but none of the two is isomorphic with "opqr".
        

{"aa", "ab", "bb", "cc", "cd"}

Returns: 4

The four isomorphic pairs are {"aa", "bb"}, {"aa", "cc"}, {"bb", "cc"} and {"ab", "cd"}.
        

{ "cacccdaabc", "cdcccaddbc", "dcdddbccad", "bdbbbaddcb",
  "bdbcadbbdc", "abaadcbbda", "babcdabbac", "cacdbaccad",
  "dcddabccad", "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca" }

Returns: 13
"""
def isomorphic(word1, word2):
    if len(word1) != len(word2): return False
    map1 = {}
    map2 = {}
    for i in range(len(word1)):
         ch1 = word1[i]
         ch2 = word2[i]
         if ch1 not in map1:
             map1[ch1] = ch2
         else:
             if map1[ch1] != ch2: return False
         
         if ch2 not in map2:
             map2[ch2] = ch1
         else:
             if map2[ch2] != ch1: return False
    return True


testWords = ["abca", "zbxz", "opqr"]
#testWords = ["aa", "ab", "bb", "cc", "cd"]
#testWords = ["cacccdaabc", "cdcccaddbc", "dcdddbccad", "bdbbbaddcb", "bdbcadbbdc", "abaadcbbda", "babcdabbac", "cacdbaccad", "dcddabccad", "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca"]

for i in range(len(testWords)):
    for j in range(i+1, len(testWords)):
        word1 = testWords[i]
        word2 = testWords[j]
        if(isomorphic(word1, word2)):
            print word1 , word2 , " is isomorphic"