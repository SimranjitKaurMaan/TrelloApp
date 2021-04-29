package com.simran.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BoardList
{
    private String id;
    private String name;
    private Map<String,Card> cardMap;

    @Override
    public String toString() {
        return "BoardList{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cardMap=" + cardMap +
                '}';
    }

    public BoardList(String name)
    {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.cardMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Map<String, Card> getCardMap() {
        return cardMap;
    }

}
