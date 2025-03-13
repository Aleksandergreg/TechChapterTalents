package org.example.talentstechchapter.config;


import org.example.talentstechchapter.entity.Document;
import org.example.talentstechchapter.entity.Talent;
import org.example.talentstechchapter.repository.DocumentRepository;
import org.example.talentstechchapter.repository.TalentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class InitData {

    @Bean
    public CommandLineRunner initDatabase(
            TalentRepository talentRepository,
            DocumentRepository documentRepository
    ) {
        return args -> {
            // Create some Talent entities
            Talent t1 = Talent.builder()
                    .name("Alice Anderson")
                    .title("Software Engineer")
                    .profileText("Passionate about coding and open-source.")
                    .email("alice@example.com")
                    .phone("+123456789")
                    .city("Wonderland")
                    .country("Dreamland")
                    .github("https://github.com/alice")
                    .linkedin("https://linkedin.com/in/alice")
                    .build();

            Talent t2 = Talent.builder()
                    .name("Bob Brown")
                    .title("Data Scientist")
                    .profileText("Expert in Machine Learning and AI.")
                    .email("bob.brown@example.com")
                    .phone("+987654321")
                    .city("Metropolis")
                    .country("Fictionland")
                    .github("https://github.com/bob")
                    .linkedin("https://linkedin.com/in/bob")
                    .build();

            Talent t3 = Talent.builder()
                    .name("Aleksander Gregersen")
                    .title("Studying AP Computer Science at KEA")
                    .profileText("Big fan of learning automated processes, programming languages and DevOps")
                    .email("agregersen0gmail.com")
                    .phone("+4542755293")
                    .city("Copenhagen")
                    .country("Denmark")
                    .github("https://github.com/Aleksandergreg")
                    .linkedin("https://www.linkedin.com/in/aleksander-gregersen/")
                    .build();




            // Save talents
            talentRepository.saveAll(List.of(t3, t1, t2));

            Document doc1 = Document.builder()
                    .name("Resume - Alice")
                    .content("Alice's Resume Content Here...")
                    .build();
            doc1.setTalent(t1);

            Document doc2 = Document.builder()
                    .name("Portfolio - Alice")
                    .content("Alice's Portfolio Content Here...")
                    .build();
            doc2.setTalent(t1);

            Document doc3 = Document.builder()
                    .name("Resume - Bob")
                    .content("Bob's Resume Content Here...")
                    .build();
            doc3.setTalent(t2);

            Document doc4 = Document.builder()
                    .name("Resume - Aleksander")
                    .content("Check out my LinkedIn or the application sent earlier ")
                    .build();
            doc3.setTalent(t2);



            // Save documents
            documentRepository.saveAll(List.of(doc1, doc2, doc3));
        };
    }
}
