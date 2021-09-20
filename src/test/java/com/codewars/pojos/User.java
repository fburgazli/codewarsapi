package com.codewars.pojos;

import lombok.Data;

import java.util.List;


@Data
public class User {
    private String username;
    private String name;
    private int honor;
    private String clan;
    private int leaderboardPosition;
    private List<String> skills;
    private Ranks ranks;
    private Overall overall;
    private CodeChallenges codeChallenges;


}

