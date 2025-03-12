package org.example.talentstechchapter.controller;

import org.example.talentstechchapter.dto.DocumentDTO;
import org.example.talentstechchapter.service.TalentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent/{id}/documents")
public class DocumentController {

    private final TalentService talentService;

    public DocumentController(TalentService talentService) {
        this.talentService = talentService;
    }

    // GET /talent/{id}/documents -> All documents for a specific talent
    @GetMapping
    public ResponseEntity<List<DocumentDTO>> getDocumentsForTalent(@PathVariable String id) {
        List<DocumentDTO> documents = talentService.getDocumentsForTalent(id);
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    // GET /talent/{id}/documents/{documentId} -> A specific document
    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDTO> getDocumentForTalent(
            @PathVariable String id,
            @PathVariable String documentId) {
        DocumentDTO document = talentService.getDocumentForTalent(id, documentId);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }
}
