import pytest

from tdd_python.src.dip_service_client import Service, ServiceImpl, ServiceMock, Client
# from tdd_python.src.dip_service_client import Service, Client


def test_dip_get_id():
    """
    # DIP: get id and assert
    """
    id_expected = 1234
    # GIVEN
    service = Service(id_expected)

    # WHEN
    id_actual = service.get_id()

    # THEN
    assert id_expected == id_actual


def test_dip_real_service():
    """
    # DIP: modify the classes to call a mock service
    """
    id_expected = 1234
    # GIVEN
    service = ServiceImpl(id_expected)
    client = Client(service)

    # WHEN
    id_actual = service.get_id()

    # THEN
    assert id_expected == id_actual


def test_dip_mock_service():
    """
    # DIP: modify the classes to call a mock service
    """
    id_expected = 1234
    # GIVEN
    service = ServiceMock(id_expected)
    client = Client(service)

    # WHEN
    id_actual = service.get_id()

    # THEN
    assert id_expected == id_actual
