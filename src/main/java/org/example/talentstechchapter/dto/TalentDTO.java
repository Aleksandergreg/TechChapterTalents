package org.example.talentstechchapter.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TalentDTO {
    private String id;
    private String name;
    private String title;
    private String profileText;
    private String email;
    private String phone;
    private String city;
    private String country;
    private String github;
    private String linkedin;
}

