#先排序再用二分法查找
                            #查找最小值
def find_smallest(arr):
    smallest = arr[0]
    smallest_index = 0
    for i in range(1,len(arr)):
        if arr[i] < smallest:
            smallest = arr[i]
            smallest_index = i
    return smallest_index  #返回值是最小值的索引（位置）

                           #从小到大排序
def Sort(arr):
    Newarr = []
    for i in range(len(arr)):
        b = find_smallest(arr)
        Newarr.append(arr.pop(b))
    return Newarr

                            #二分法查找
def binary_search(arr,item):
    c = Sort(arr)
    low = 0
    high = len(c)-1
    while low <= high:
        mid = int((low + high) / 2)
        guess = c[mid]
        if guess == item:
            return mid     #返回中间元素的索引（位置）
        elif guess < item:
            low = mid + 1
        else:
            high = mid - 1
    return ('不存在！')


b = [1,5,9,3,4,-9,6,-30]
print(Sort(b))
a = [1,5,9,3,4,-9,6,-30]
print(binary_search(a,40))
