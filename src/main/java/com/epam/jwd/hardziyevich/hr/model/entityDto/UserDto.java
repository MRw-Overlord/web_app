package com.epam.jwd.hardziyevich.hr.model.entityDto;

import com.epam.jwd.hardziyevich.hr.model.entity.Role;

import java.util.Objects;

public class UserDto {

    private int id;
    private String login;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private Role role;
    private String status;
    private String avatarPath;
    private String avatarImg;

    public UserDto(int id, String login, String firstName, String lastName, int age,
                   String email, Role role, String status, String avatarPath, String avatarImg) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatarPath = avatarPath;
        this.avatarImg = avatarImg;
    }

    public UserDto(int id, String login, String firstName, String lastName, int age, String email, Role role, String status, String avatarPath) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatarPath = avatarPath;
    }

    public UserDto(String login, String firstName, String lastName,
                   int age, String email, Role role, String status, String avatarPath) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.role = role;
        this.status = status;
        this.avatarPath = avatarPath;
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

    public String getRoleName(){
        return this.role.name();
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

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return age == userDto.age && Objects.equals(login, userDto.login) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(email, userDto.email) && role == userDto.role && Objects.equals(status, userDto.status) && Objects.equals(avatarPath, userDto.avatarPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, lastName, age, email, role, status, avatarPath);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status='" + status + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatarImg() {
        return avatarImg;
    }

    public void setAvatarImg(String avatarImg) {
        this.avatarImg = avatarImg;
    }
}
