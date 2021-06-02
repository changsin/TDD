import unittest

from importlib.util import find_spec
if find_spec("tdd_python") is None:
    import sys
    sys.path.append('../..')

import tdd_python.src.sample as sample


class TestSample(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        print("*****CLASS SETUP*****")

    @classmethod
    def tearDownClass(cls):
        print("*****TEARDOWN*****")

    def setUp(self):
        print("-----SETUP-----")

    def tearDown(self):
        print("-----TEARDOWN-----")

    def test_fib_valid(self):
        """
        id:   0 1 2 3 4 5 6
        Fib:  1 1 2 3 5 8 13
        """
        # res = sample.fib(1)
        # print("result: {}".format(res))
        self.assertEqual(sample.fib(0), 0)
        self.assertEqual(sample.fib(1), 1)
        self.assertEqual(sample.fib(3), 2)
        self.assertEqual(sample.fib(4), 3)
        self.assertEqual(sample.fib(5), 5)

    def test_fib_invalid(self):
        self.assertEqual(sample.fib(-1), 0)

        # ??? What should be the expected result?
        # self.assertEqual(sample.fib(.55), 1)

    def fib_this_does_not_run(self):
        self.assertEqual(sample.fib(-1), 0)


if __name__=='__main__':
    unittest.main()