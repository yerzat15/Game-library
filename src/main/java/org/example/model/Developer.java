package org.example.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "developers")
public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    private String country;

    @Column(name = "founded_year")
    private Integer foundedYear;

    @OneToMany(mappedBy = "developer")
    private List<GameBase> games;

    public Developer() {}

    public Developer(String name, String country, Integer foundedYear) {
        this.name = name;
        this.country = country;
        this.foundedYear = foundedYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getFoundedYear() {
        return foundedYear;
    }

    public void setFoundedYear(Integer foundedYear) {
        this.foundedYear = foundedYear;
    }

    public List<GameBase> getGames() {
        return games;
    }

    public void setGames(List<GameBase> games) {
        this.games = games;
    }
}
