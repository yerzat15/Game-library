package org.example.dto;

import jakarta.validation.constraints.*;

public class DeveloperRequest {

    @NotBlank
    private String name;

    private String country;

    @Min(1950)
    @Max(2100)
    private Integer foundedYear;

    public DeveloperRequest() {}

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public Integer getFoundedYear() { return foundedYear; }
    public void setFoundedYear(Integer foundedYear) { this.foundedYear = foundedYear; }
}
