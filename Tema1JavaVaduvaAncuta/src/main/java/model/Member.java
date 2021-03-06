package model;

public class Member {
    private String lastName;
    private String firstName;
    private String username;

    public Member(String lastName, String firstName, String username) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Member{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
