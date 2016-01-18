package de.thi.foodplaner.domain;

/**
 * Created by Philipp on 14.01.16.
 */
public enum Role {
    user ("user"),
    admin ("admin");

    private final String name;

    private Role(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
