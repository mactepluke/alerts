package com.safetynet.alerts.model;


import java.util.ArrayList;
import java.util.List;

public class ChildFromAddress {
    private List<Child> child = null;
    private List<Person> others;

    public List<Person> getOthers() {
        return others;
    }

    public void setOthers(List<Person> others) {
        this.others = others;
    }

    public void addChild(String firstName, String lastName, byte age) {
        if (this.child == null) {
            this.child = new ArrayList<>();
        }
        this.child.add(new ChildFromAddress.Child(firstName, lastName, age));
    }

    public List<Child> getChild() {
        return child;
    }

    class Child {
        String firstName;
        String lastName;
        byte age;

        public Child(String firstName, String lastName, byte age)   {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public byte getAge() {
            return age;
        }
    }
}
