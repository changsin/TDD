import pytest

from tdd_python.src.isp_animal import Animal


def test_isp_feed():
    """
    # IPS: feed and check
    """
    # GIVEN
    feed_times = 3
    dog = Animal("dog")

    # WHEN
    feed_times_dog = dog.feed(feed_times)

    # THEN
    assert feed_times_dog == 3
