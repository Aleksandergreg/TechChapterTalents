package org.example.talentstechchapter.service;


import org.example.talentstechchapter.entity.Document;
import org.example.talentstechchapter.entity.Talent;
import org.example.talentstechchapter.dto.DocumentDTO;
import org.example.talentstechchapter.dto.TalentDTO;
import org.example.talentstechchapter.repository.DocumentRepository;
import org.example.talentstechchapter.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TalentService {

    private final TalentRepository talentRepository;
    private final DocumentRepository documentRepository;

    public TalentService(TalentRepository talentRepository,
                         DocumentRepository documentRepository) {
        this.talentRepository = talentRepository;
        this.documentRepository = documentRepository;
    }

    // 1) Get all talents
    public List<TalentDTO> getAllTalents() {
        return talentRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 2) Get a specific talent by ID
    public Optional<TalentDTO> getTalentById(String id) {
        return talentRepository.findById(id)
                .map(this::mapToDTO);
    }

    // 3) Get documents for a specific talent
    public List<DocumentDTO> getDocumentsForTalent(String talentId) {
        return documentRepository.findByTalentId(talentId).stream()
                .map(this::mapToDocumentDTO)
                .collect(Collectors.toList());
    }

    // 4) Get a specific document for a specific talent
    public Optional<DocumentDTO> getDocumentForTalent(String talentId, String documentId) {
        // first check if the talent exists (optional)
        return talentRepository.findById(talentId).flatMap(talent ->
                documentRepository.findById(documentId)
                        .filter(doc -> doc.getTalent().getId().equals(talent.getId()))
                        .map(this::mapToDocumentDTO)
        );
    }

    /*
     * Private helper methods to map between Entity and DTO.
     */

    private TalentDTO mapToDTO(Talent talent) {
        return TalentDTO.builder()
                .id(talent.getId())
                .name(talent.getName())
                .title(talent.getTitle())
                .profileText(talent.getProfileText())
                .email(talent.getEmail())
                .phone(talent.getPhone())
                .city(talent.getCity())
                .country(talent.getCountry())
                .github(talent.getGithub())
                .linkedin(talent.getLinkedin())
                .build();
    }

    private DocumentDTO mapToDocumentDTO(Document document) {
        return DocumentDTO.builder()
                .id(document.getId())
                .name(document.getName())
                .content(document.getContent())
                .build();
    }
}
