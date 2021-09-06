# Python good practices

## OOP

- Do not introduce an attribute outside of __init__ method
  - No way to communicate this to the user
  - Cannot force the order of execution to call set_balance
  (source: [CS229_Python_Tutorial](https://d1b10bmlvqabco.cloudfront.net/attach/jkbylqx4kcp1h3/jm8g1m67da14eq/jn7zkozyyol7/CS229_Python_Tutorial.pdf)

```
class Customer(object):
  def __init__(self, name):
    self.name = name
    
  def set_balance(self, balance=0.0):
    self.balance = balance
  
  def withdraw(self, amount):
    if amount > self.balance:
      raise RuntimeError('Amount greater than available balance'
    self.balance -= amount
    return self.balance
    
  def deposit(self, amount):
    self.balance += amount
    return self.balance
```
