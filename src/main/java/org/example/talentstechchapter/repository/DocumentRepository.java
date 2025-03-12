package org.example.talentstechchapter.repository;


import org.example.talentstechchapter.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {
    List<Document> findByTalentId(String talentId);
}

