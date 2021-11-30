import pytest

from tdd_python.src.srp_person import Person, PersonReport


def test_srp_full_name():
    """
    # SRP: normal case
    """
    # GIVEN
    person1 = Person("Tom", "John")

    # WHEN
    full_name = person1.get_full_name()

    # THEN
    assert full_name == "Tom John"


def test_srp_two_new_fields():
    """
    # SRP: add two new fields: title & middle name
    """
    # GIVEN

    # WHEN

    # THEN
    pass


def test_srp_report_format_changed():
    """
    # SRP: what if you want to change the report format to
    <div>...</div>
    """
    # GIVEN
    person1 = Person("Tom", "John")
    person2 = Person("John", "Lee")

    people = [person1, person2]
    # WHEN
    report = PersonReport()
    report.get_full_names(people)


if __name__ == '__main__':
    pytest.main()
