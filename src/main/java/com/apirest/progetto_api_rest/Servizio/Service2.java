package com.apirest.progetto_api_rest.Servizio;

import com.apirest.progetto_api_rest.Ripo.Repo;
import com.apirest.progetto_api_rest.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
//a.	Filtrer la liste des Person lorsque Age > 40.
public class Service2 {
    private Repo repository;

    public Service2(Repo repository) {
        this.repository = repository;
    }

    public ResponseEntity<?> Filter() {
        try {
            List<Persona> FilterPerson = repository.findAll();
            FilterPerson=FilterPerson.stream().filter(p -> p.getAge() > 40)
            //filtrage par attribut
            .sorted(Comparator.comparing(Persona::getAge).thenComparing(Persona::getLastName).thenComparing(Persona::getFirstName)).collect(Collectors.toList());

            repository.saveAll(FilterPerson);
            FilterText(FilterPerson , "Mario.txt");
            return new ResponseEntity<>("perssone filtre et savegaurde : " + FilterPerson, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error de filter et sauvegarde : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void FilterText(List<Persona> filteredPersons,String outputPath) throws IOException {
        try (FileWriter scrivi = new FileWriter(outputPath)) {
            filteredPersons.forEach(persona1 -> {
                try {
                    scrivi.write(persona1.toString() + System.lineSeparator());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        }

    }
}
