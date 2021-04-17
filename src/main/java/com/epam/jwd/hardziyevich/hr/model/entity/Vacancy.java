package com.epam.jwd.hardziyevich.hr.model.entity;

import java.util.Objects;

public class Vacancy {
    private int id;
    private String vacancyName;
    private String companyName;
    private String description;
    private String skillsDescription;
    private Status status;

    public Vacancy(int id, String vacancyName, String companyName, String description,
                   String skillsDescription, Status status) {
        this.id = id;
        this.vacancyName = vacancyName;
        this.companyName = companyName;
        this.description = description;
        this.skillsDescription = skillsDescription;
        this.status = status;
    }

    public Vacancy(String vacancyName, String companyName, String description,
                   String skillsDescription, Status status) {
        this.vacancyName = vacancyName;
        this.companyName = companyName;
        this.description = description;
        this.skillsDescription = skillsDescription;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVacancyName() {
        return vacancyName;
    }

    public void setVacancyName(String vacancyName) {
        this.vacancyName = vacancyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkillsDescription() {
        return skillsDescription;
    }

    public void setSkillsDescription(String skillsDescription) {
        this.skillsDescription = skillsDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id && Objects.equals(vacancyName, vacancy.vacancyName) && Objects.equals(companyName, vacancy.companyName) && Objects.equals(description, vacancy.description) && Objects.equals(skillsDescription, vacancy.skillsDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vacancyName, companyName, description, skillsDescription);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", vacancyName='" + vacancyName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", skillsDescription='" + skillsDescription + '\'' +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
