package com.epam.jwd.hardziyevich.hr.model.entityDto;

import com.epam.jwd.hardziyevich.hr.model.entity.Status;

import java.util.Objects;

public class VacancyDto {
    private int vacancyId;
    private String vacancyName;
    private String companyName;
    private String description;
    private String skillsDescription;
    private Status status;

    public VacancyDto(int vacancyId, String vacancyName, String companyName, String description, String skillsDescription, Status status) {
        this.vacancyId = vacancyId;
        this.vacancyName = vacancyName;
        this.companyName = companyName;
        this.description = description;
        this.skillsDescription = skillsDescription;
        this.status = status;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VacancyDto that = (VacancyDto) o;
        return Objects.equals(vacancyName, that.vacancyName) && Objects.equals(companyName, that.companyName) && Objects.equals(description, that.description) && Objects.equals(skillsDescription, that.skillsDescription) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyName, companyName, description, skillsDescription, status);
    }

    @Override
    public String toString() {
        return "VacancyDto{" +
                "vacancyName='" + vacancyName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", skillsDescription='" + skillsDescription + '\'' +
                ", status=" + status +
                '}';
    }
}
