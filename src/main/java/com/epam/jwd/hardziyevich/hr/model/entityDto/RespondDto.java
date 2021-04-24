package com.epam.jwd.hardziyevich.hr.model.entityDto;

import com.epam.jwd.hardziyevich.hr.model.entity.Respond;

import java.sql.Timestamp;
import java.util.Objects;

public class RespondDto {

    private int respondId;
    private int vacancyId;
    private int userId;
    private Timestamp date;

    public RespondDto(int respondId, int vacancyId, int userId, Timestamp date) {
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
        RespondDto that = (RespondDto) o;
        return respondId == that.respondId && userId == that.userId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(respondId, vacancyId, userId, date);
    }

    @Override
    public String toString() {
        return "RespondDto{" +
                "respondId=" + respondId +
                ", vacancyId=" + vacancyId +
                ", userId=" + userId +
                ", date=" + date +
                '}';
    }
}
