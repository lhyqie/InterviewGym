from bs4 import BeautifulSoup

#filepath = '../data/sample_webpage.html'
filepath =  r'D:\GitProjects\InterviewGym\data\sample_webpage.html'

print filepath

with open(filepath, 'r') as fr:
    html = fr.read()
    soup = BeautifulSoup(html)
    print soup.prettify()