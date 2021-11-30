import pytest

from importlib.util import find_spec
if find_spec("tdd_python") is None:
    import sys
    sys.path.append('../..')

from tdd_python.src.solid import Analyzer

"""
NB: pytest requires the test files to end with "*_test.py"
"""


def test_fib_valid():
    order = Analyzer()
    order.add_item("Keyboard", 1, 50)
    order.add_item("SSD", 1, 150)
    order.add_item("USB cable", 2, 5)

    print(order.total_price())
    order.pay("debit", "0372846")
