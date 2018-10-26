a = ['a','f','frw','r','faf']#i
b = ['f','ret','thy','a','r']#j
for i in a:
    for j in  b:
        if j == i:
            break
    else:
        b.append(i)
print(b)

