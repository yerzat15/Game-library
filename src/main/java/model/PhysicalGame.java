package model;

import interfaces.Tradeable;

public class PhysicalGame extends GameBase implements Tradeable {
    private String condition;
    private String barcode;
    private String shelfLocation;

    public PhysicalGame(int gameId, String title, int releaseYear, String publisher,
                        Genre genre, Developer developer, String condition,
                        String barcode, String shelfLocation) {
        super(gameId, title, releaseYear, publisher, genre, developer, "PHYSICAL");
        this.condition = condition;
        this.barcode = barcode;
        this.shelfLocation = shelfLocation;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Physical Game ===");
        System.out.println("Title: " + title);
        System.out.println("Condition: " + condition);
        System.out.println("Location: " + shelfLocation);
        System.out.println("Genre: " + genre.getName());
        System.out.println("Developer: " + developer.getName());
    }

    @Override
    public String getGameDetails() {
        return String.format("[Physical] %s - %s (Location: %s)", title, condition, shelfLocation);
    }

    @Override
    public double getMarketValue() {
        double baseValue = 60.0;
        switch (condition.toLowerCase()) {
            case "new":
                return baseValue;
            case "used":
                return baseValue * 0.6;
            case "collector edition":
                return baseValue * 1.5;
            default:
                return baseValue * 0.5;
        }
    }

    @Override
    public boolean isAvailableForTrade() {
        return !condition.equalsIgnoreCase("collector edition");
    }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getShelfLocation() { return shelfLocation; }
    public void setShelfLocation(String shelfLocation) { this.shelfLocation = shelfLocation; }
}