package model;

public class Developer {
    private int developerId;
    private String name;
    private String country;
    private int foundedYear;

    // Constructors
    public Developer() {}

    public Developer(int developerId, String name, String country, int foundedYear) {
        this.developerId = developerId;
        this.name = name;
        this.country = country;
        this.foundedYear = foundedYear;
    }

    // Getters and Setters
    public int getDeveloperId() { return developerId; }
    public void setDeveloperId(int developerId) { this.developerId = developerId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public int getFoundedYear() { return foundedYear; }
    public void setFoundedYear(int foundedYear) { this.foundedYear = foundedYear; }

    @Override
    public String toString() {
        return "Developer: " + name + " (" + country + ")";
    }
}