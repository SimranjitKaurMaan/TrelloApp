package com.simran.models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Board
{
    private String id;
    private String name;
    private BoardPrivacyStatus status;
    private String url;
    private Map<String,User> memberMap;
    private Map<String,BoardList> boardListMap;

    @Override
    public String toString() {
        return "Board{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", url='" + url + '\'' +
                ", memberMap=" + memberMap +
                ", boardListMap=" + boardListMap +
                '}';
    }

    public Board(String name, BoardPrivacyStatus status)
    {
        this.id = UUID.randomUUID().toString();
        this.url = "trello/board/"+id;
        this.name = name;
        this.status = status;
        this.memberMap = new HashMap<>();
        this.boardListMap = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public Map<String, User> getMemberMap() {
        return memberMap;
    }

    public Map<String, BoardList> getBoardListMap() {
        return boardListMap;
    }

}
