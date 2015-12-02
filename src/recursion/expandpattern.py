def expand(pattern):
    res = []
    start = pattern.find('[')
    if start < 0: 
        res.append(pattern)
    else:
        end = pattern.find(']', start )
        values = pattern[start+1:end].split(',')
        for value in values:
            for postfix in expand(pattern[end+1:]):
                res.append(pattern[:start] + value + postfix)
        #print values
    return res
        
ret = expand("app[1,2,3].corp[3,4,8].com")

for e in ret:
    print e