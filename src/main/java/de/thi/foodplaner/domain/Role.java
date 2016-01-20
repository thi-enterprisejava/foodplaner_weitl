package de.thi.foodplaner.domain;

/**
 * Created by Philipp on 14.01.16.
 */
public enum Role {
    USER("user"),
    ADMIN("admin");

    /******* Variables *******/
    private final String name;

    /******** Constructor ********/
    private Role(String s) {
        name = s;
    }

    /******** Methods ********/
    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
