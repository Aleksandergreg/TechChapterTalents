package org.example.talentstechchapter.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String id;

    private String name;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "talent_id")
    private Talent talent;

    @PrePersist
    public void generateIdIfEmpty() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }
}
