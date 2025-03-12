package org.example.talentstechchapter.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "talent")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Talent {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String id;

    private String name;

    private String title;

    @Column(name = "profile_text")
    private String profileText;

    private String email;

    private String phone;

    private String city;

    private String country;

    private String github;

    private String linkedin;

    // One-to-many relationship with Document
    @OneToMany(mappedBy = "talent", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Document> documents = new ArrayList<>();

    @PrePersist
    public void generateIdIfEmpty() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
