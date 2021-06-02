import pytest

from importlib.util import find_spec
if find_spec("tdd_python") is None:
    import sys
    sys.path.append('../..')

import tdd_python.src.sample as sample

"""
NB: pytest requires the test files to end with "*_test.py"
"""


def test_fib_valid():
    """
    id:   0 1 2 3 4 5 6
    Fib:  1 1 2 3 5 8 13
    """
    assert sample.fib(1) == 1


def test_fib_invalid():
    """
    id:   0 1 2 3 4 5 6
    Fib:  1 1 2 3 5 8 13
    """
    assert sample.fib(-1) == 0


def test_fib_fail():
    """
    intentionally failing
    """
    assert sample.fib(8) == 0


if __name__ == '__main__':
    pytest.main()