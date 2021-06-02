def fib(id):
    """
    id: index (zero-based)
    returns: Fibonacci number for the given index

    id:   0 1 2 3 4 5 6 7
    Fib:  0 1 1 2 3 5 8 13
    """
    if id < 0:
        return 0

    if id == 0:
        return 0
    if id == 1:
        return 1

    first = 0
    second = 1
    counter = 2
    fib_num = 0
 
    while counter <= id:
        fib_num = first + second
        first = second
        second = fib_num
        counter = counter + 1

        print(counter, fib_num)

    return fib_num
