package com.apirest.progetto_api_rest.model;

import java.util.List;

public class SplitRequest {
    private List<Persona> persons;
    private int pageSize;

    public SplitRequest() {
    }

    public SplitRequest(List<Persona> persons, int pageSize) {
        this.persons = persons;
        this.pageSize = pageSize;
    }

    public List<Persona> getPersons() {
        return persons;
    }

    public void setPersons(List<Persona> persons) {
        this.persons = persons;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
