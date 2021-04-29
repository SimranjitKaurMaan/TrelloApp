package com.simran.models;

import java.util.UUID;

public class Card
{
    private String id;
    private String name;
    private String description;
    private User assignedUser;

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", assignedUser=" + assignedUser +
                '}';
    }

    public Card(String name, String description)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}
