package com.epam.jwd.hardziyevich.hr.model.entity;

import java.util.Objects;

public class User {
    private long id;
    private String login;
    private Role role;
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private String password;
    private String status;
    private String avatarPath;

    public User(long id, String login, Role role, String firstName, String lastName, int age,
                String email, String password, String status, String avatarPath) {
        this.id = id;
        this.login = login;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.status = status;
        this.avatarPath = avatarPath;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * The method is used to determine whether the user is banned or not
     *
     * @return 0 if user ACTIVE or if user BANNED 1
     */
    public int retrieveStatusId() {
        return "ACTIVE".equalsIgnoreCase(status) ? 0 : 1; //1-- banned equals hidden
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && age == user.age && Objects.equals(login, user.login) && role == user.role && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(status, user.status) && Objects.equals(avatarPath, user.avatarPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, role, firstName, lastName, age, email, password, status, avatarPath);
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", avatarPath='" + avatarPath + '\'' +
                '}';
    }
}
