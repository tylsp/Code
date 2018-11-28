#递归求斐波拉契数列
'''i = int(input("请输入一个大于0的整数："))
def factorial(i):
    if i == 1or i == 2:
        return 1
    else:
        return (factorial(i-2) + factorial(i-1))

print(factorial(i))'''

#while循环求斐波拉契数列
a= 0
b= 1
i= int(input("请输入一个大于0的整数："))
if i == 1:
    print(b)
elif i > 1:
    while i>1:
        a = a + b
        b = a + b
        i = i - 2
    if i / 2 ==0:
        print(a)
    else:
        print(b)
else:
    print("输入有误，请输入一个大于0的整数！")