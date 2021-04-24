package com.epam.jwd.hardziyevich.hr.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Respond {

    private int respondId;
    private int vacancyId;
    private int userId;
    private Timestamp date;

    public Respond(int userId, int vacancyId, Timestamp date) {
        this.vacancyId = vacancyId;
        this.userId = userId;
        this.date = date;
    }

    public Respond(int respondId, int vacancyId, int userId, Timestamp date) {
        this.vacancyId = vacancyId;
        this.respondId = respondId;
        this.userId = userId;
        this.date = date;
    }


    public int getRespondId() {
        return respondId;
    }

    public void setRespondId(int respondId) {
        this.respondId = respondId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getVacancyId() {
        return vacancyId;
    }

    public void setVacancyId(int vacancyId) {
        this.vacancyId = vacancyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Respond respond = (Respond) o;
        return vacancyId == respond.vacancyId && respondId == respond.respondId && userId == respond.userId && Objects.equals(date, respond.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancyId, respondId, userId, date);
    }

    @Override
    public String toString() {
        return "Respond{" +
                "getVacancyId=" + vacancyId +
                ", respondId=" + respondId +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
