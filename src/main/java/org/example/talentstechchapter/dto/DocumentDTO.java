package org.example.talentstechchapter.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentDTO {
    private String id;
    private String name;
    private String content;
}
