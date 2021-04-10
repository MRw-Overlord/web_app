package com.epam.jwd.hardziyevich.hr.model.entityDto;

import com.epam.jwd.hardziyevich.hr.model.entity.Role;

import java.util.Objects;

public class UserDto {

    private String login;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Role role;
    private String status;

    public UserDto(String login, String firstName, String lastName,
                   int age, String email, Role role, String status) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return age == userDto.age && Objects.equals(login, userDto.login) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(email, userDto.email) && role == userDto.role && Objects.equals(status, userDto.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, age, email, role, status);
    }
}
