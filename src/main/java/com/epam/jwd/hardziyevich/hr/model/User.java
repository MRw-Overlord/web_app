package com.epam.jwd.hardziyevich.hr.model;

public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String login;
    private final String mail;
    private final String password;

    public User(int id, String firstName, String lastName, int age, String login, String password, String mail) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.login = login;
        this.mail = mail;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getLogin() {
        return login;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }
}
