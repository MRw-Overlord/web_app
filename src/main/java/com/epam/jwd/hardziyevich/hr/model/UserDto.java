package com.epam.jwd.hardziyevich.hr.model;

import java.util.Objects;

public class UserDto {

    private String firstName;
    private String lastName;
    private int age;
    private String login;
    private String mail;

    public UserDto(String firstName, String lastName, int age, String login, String mail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.login = login;
        this.mail = mail;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(age, userDto.age) && Objects.equals(login, userDto.login) && Objects.equals(mail, userDto.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, age, login, mail);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
