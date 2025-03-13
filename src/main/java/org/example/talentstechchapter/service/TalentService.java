package org.example.talentstechchapter.service;

import org.example.talentstechchapter.entity.Document;
import org.example.talentstechchapter.entity.Talent;
import org.example.talentstechchapter.dto.DocumentDTO;
import org.example.talentstechchapter.dto.TalentDTO;
import org.example.talentstechchapter.exception.TalentNotFoundException;
import org.example.talentstechchapter.exception.DocumentNotFoundException;
import org.example.talentstechchapter.repository.DocumentRepository;
import org.example.talentstechchapter.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return talentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // 2) Get a specific talent by ID (throw if not found)
    public TalentDTO getTalentById(String id) {
        Talent talent = talentRepository.findById(id)
                .orElseThrow(() ->
                        new TalentNotFoundException("Talent not found with id: " + id));
        return mapToDTO(talent);
    }

    // 3) Get documents for a specific talent
    public List<DocumentDTO> getDocumentsForTalent(String talentId) {
        // Check talent existence first (otherwise we can't read docs)
        Talent talent = talentRepository.findById(talentId)
                .orElseThrow(() ->
                        new TalentNotFoundException("Talent not found with id: " + talentId));



        return documentRepository.findByTalentId(talentId).stream()
                .map(this::mapToDocumentDTO)
                .collect(Collectors.toList());
    }

    // 4) Get a specific document for a specific talent
    public DocumentDTO getDocumentForTalent(String talentId, String documentId) {
        Talent talent = talentRepository.findById(talentId)
                .orElseThrow(() ->
                        new TalentNotFoundException("Talent not found with id: " + talentId));

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() ->
                        new DocumentNotFoundException("Document not found with id: " + documentId));

        // Check that document belongs to the talent
        if (!document.getTalent().getId().equals(talent.getId())) {
            throw new DocumentNotFoundException(
                    "Document with id: " + documentId + " does not belong to talent with id: " + talentId
            );
        }

        return mapToDocumentDTO(document);
    }

    /*
     * Private helper methods for mapping
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
