package com.apirest.progetto_api_rest.Controller;
import aj.org.objectweb.asm.TypeReference;
import com.apirest.progetto_api_rest.Ripo.Repo;
import com.apirest.progetto_api_rest.Servizio.Service1;
import com.apirest.progetto_api_rest.Servizio.Service2;

import com.apirest.progetto_api_rest.model.Persona;
import com.apirest.progetto_api_rest.model.SplitRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/persons")
public class PersonController {

    private  Service1 service1;
    private  Service2 service2;
    private Repo ripo;


    public PersonController(Service1 service1, Service2 service2,Repo ripo) {
        this.service1 = service1;
        this.service2 = service2;
        this.ripo = ripo;
    }

    @PostMapping("/add")
    public void createPerson(@Valid @RequestBody Persona person) {
        service1.AddPerson(person);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable Long id) {
        return service1.GetPersonneById(id);
    }
    @GetMapping("/filter")
    public ResponseEntity<?> filterPersons() {
        return service2.Filter();
    }

    @PostMapping("/split")
    public ResponseEntity<List<List<Persona>>> splitPersons(@RequestBody List<Persona> persons, @RequestParam int pageSize) {
        List<List<Persona>> paginatedPersons = service1.splitPersons(persons, pageSize);
        service1.AddPersonList(paginatedPersons);
        return ResponseEntity.ok(paginatedPersons);
    }
    @GetMapping("/splitGet")
    public ResponseEntity<List<List<Persona>>> splitPersonsGet( @RequestParam int pageSize) {
        List<Persona> persons = service1.FindAllPerson();
        List<List<Persona>> paginatedPersons = service1.splitPersons(persons, pageSize);
        service1.AddPersonList(paginatedPersons);
        return ResponseEntity.ok(paginatedPersons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putPerson(@RequestBody Persona updatePersona, @PathVariable Long id) {
        return service1.PutPersona(updatePersona, id);
    }

    @PostMapping("/splitRequest")
    public ResponseEntity<List<List<Persona>>> splitPersons(@RequestBody SplitRequest splitRequest) {
        List<List<Persona>> paginatedPersons = service1.splitPersons(splitRequest.getPersons(), splitRequest.getPageSize());
        service1.AddPersonList(paginatedPersons);
        return ResponseEntity.ok(paginatedPersons);
    }



}
