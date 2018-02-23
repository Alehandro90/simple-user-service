package com.dgeorgiev.userservice.domain;

public class User
{
    public final int id;
    public final String name;
    public final String email;

    public User(int id, String name, String email)
    {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
