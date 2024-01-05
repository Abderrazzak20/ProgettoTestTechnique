package com.apirest.progetto_api_rest.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;
    int Age;
    @NotBlank(message = "le champ FirstName ne peut pas etre vide")
    @NotNull(message = "le champ FirstName ne peut pas etre null")
    String FirstName;
    @NotBlank(message = "le champ LastName ne peut pas etre vide")
    @NotNull(message = "le champ LastName ne peut pas etre null")
     String LastName;


}
