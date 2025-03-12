package org.example.talentstechchapter.repository;


import org.example.talentstechchapter.entity.Talent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalentRepository extends JpaRepository<Talent, String> {
}
