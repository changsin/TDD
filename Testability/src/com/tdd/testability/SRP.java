package com.tdd.testability;

public class SRP {
    public static class Person {
        String _firstName;
        String _lastName;

        Person(String firstName, String lastName) {
            this._firstName = firstName;
            this._lastName = lastName;
        }

        public String getFullName() {
            return this._firstName + " " + this._lastName;
        }

        public String getReportRow() {
            return String.format("<tr><td>%s</td></tr>", this._firstName + " " + this._lastName);
        }
    }
}
