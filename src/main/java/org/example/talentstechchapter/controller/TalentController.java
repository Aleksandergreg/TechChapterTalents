package org.example.talentstechchapter.controller;

import org.example.talentstechchapter.dto.TalentDTO;
import org.example.talentstechchapter.service.TalentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent")
public class TalentController {

    private final TalentService talentService;

    public TalentController(TalentService talentService) {
        this.talentService = talentService;
    }

    // GET /talent -> List of talents
    @GetMapping
    public ResponseEntity<List<TalentDTO>> getAllTalents() {
        List<TalentDTO> talents = talentService.getAllTalents();
        return new ResponseEntity<>(talents, HttpStatus.OK);
    }

    // GET /talent/{id} -> A specific talent
    @GetMapping("/{id}")
    public ResponseEntity<TalentDTO> getTalentById(@PathVariable String id) {
        TalentDTO talent = talentService.getTalentById(id);
        return new ResponseEntity<>(talent, HttpStatus.OK);
    }
}
