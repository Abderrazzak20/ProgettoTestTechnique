package com.apirest.progetto_api_rest.Ripo;
import com.apirest.progetto_api_rest.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Repo extends JpaRepository<Persona, Long> {
}
