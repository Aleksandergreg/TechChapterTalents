package org.example.talentstechchapter.controller;


import org.example.talentstechchapter.dto.DocumentDTO;
import org.example.talentstechchapter.dto.TalentDTO;
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
        // optionally validate the talent
        return talentService.getTalentById(id)
                .map(talentDto -> {
                    List<DocumentDTO> documents = talentService.getDocumentsForTalent(id);
                    return new ResponseEntity<>(documents, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET /talent/{id}/documents/{documentId} -> A specific document
    @GetMapping("/{documentId}")
    public ResponseEntity<DocumentDTO> getDocumentForTalent(
            @PathVariable String id,
            @PathVariable String documentId) {
        return talentService.getDocumentForTalent(id, documentId)
                .map(document -> new ResponseEntity<>(document, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

