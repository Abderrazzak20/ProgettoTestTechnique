package com.apirest.progetto_api_rest.Servizio;
import com.apirest.progetto_api_rest.Ripo.Repo;

import com.apirest.progetto_api_rest.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class Service1 {
    private Repo repository;
    private static final Logger logger = LoggerFactory.getLogger(Service1.class);

    public Service1(Repo repository) {
        this.repository = repository;
    }

       public ResponseEntity<?> AddPerson( Persona persona) {
        repository.save(persona);
        logAddedPerson(persona);
        return new ResponseEntity<>("good save", HttpStatus.OK);
    }

    public void AddPersonList(List<List<Persona>> paginatedPersons) {
        for (List<Persona> sublist : paginatedPersons) {
            repository.saveAll(sublist);
        }
    }

    public List<List<Persona>> splitPersons(List<Persona> persons,int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("la page size doit etre positive");
        }
        List<Persona> PersoneFiltre = new ArrayList<>(persons);

        if (pageSize >= PersoneFiltre.size()){
            return Collections.singletonList(new ArrayList<>(PersoneFiltre) );
        }
        List<List<Persona>> paginatedPersons = new ArrayList<>();

        for (int i = 0; i < PersoneFiltre.size(); i += pageSize) {
            int end = Math.min(i + pageSize, PersoneFiltre.size());
            paginatedPersons.add(PersoneFiltre.subList(i, end));
        }
        return paginatedPersons;
    }



    public ResponseEntity<?> GetPersonneById( Long id) {
        Optional<Persona> personneIsExist = repository.findById(id);
        if (personneIsExist.isPresent()) {
            return new ResponseEntity<>(personneIsExist.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("personne dont find ", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> PutPersona( Persona UpdatePersona, Long id) {
        Optional<Persona> PersonExist = repository.findById(id);
        if (PersonExist.isPresent()) {
            Persona BeforePersone = PersonExist.get();
            BeforePersone.setLastName(UpdatePersona.getLastName());
            BeforePersone.setFirstName(UpdatePersona.getFirstName());
         //   BeforePersone.setAge(UpdatePersona.getAge());
            Persona PersonneModifie = BeforePersone;
            repository.save(PersonneModifie);
            return new ResponseEntity<>("personne modifie", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("personne not found", HttpStatus.NOT_FOUND);
        }
    }



    // rajout de logger pour les post et les put
  private void logAddedPerson(Persona persona) {
    logger.info("Persona aggiunta: LastName = {}, FirstName = {} , Age = {}", persona.getLastName(), persona.getFirstName(), persona.getAge());
   }


   public List<Persona> FindAllPerson(){
        return repository.findAll();
   }
}
