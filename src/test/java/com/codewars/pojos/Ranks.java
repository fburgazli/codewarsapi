package com.codewars.pojos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Ranks {
    private Rank overall;
    private Languages languages;
}
