package com.epam.jwd.hardziyevich.hr.client;

public enum ClientType {
    GUEST(0),
    USER(1),
    ADMINISTRATION(2);

    private int id=0;
    ClientType(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }



}
