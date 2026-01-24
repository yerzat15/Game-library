package model;

import interfaces.Playable;

public class DigitalGame extends GameBase implements Playable {
    private String platform;
    private double downloadSize;
    private String activationKey;

    public DigitalGame(int gameId, String title, int releaseYear, String publisher,
                       Genre genre, Developer developer, String platform,
                       double downloadSize, String activationKey) {
        super(gameId, title, releaseYear, publisher, genre, developer, "DIGITAL");
        this.platform = platform;
        this.downloadSize = downloadSize;
        this.activationKey = activationKey;
    }

    @Override
    public void displayInfo() {
        System.out.println("=== Digital Game ===");
        System.out.println("Title: " + title);
        System.out.println("Platform: " + platform);
        System.out.println("Download Size: " + downloadSize + " GB");
        System.out.println("Genre: " + genre.getName());
        System.out.println("Developer: " + developer.getName());
    }

    @Override
    public String getGameDetails() {
        return String.format("[Digital] %s - %s (%.2f GB)", title, platform, downloadSize);
    }

    @Override
    public boolean canPlay() {
        return activationKey != null && !activationKey.isEmpty();
    }

    @Override
    public void startGame() {
        if (canPlay()) {
            System.out.println(" Launching " + title + " on " + platform + "...");
        } else {
            System.out.println(" Cannot play: No activation key!");
        }
    }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public double getDownloadSize() { return downloadSize; }
    public void setDownloadSize(double downloadSize) { this.downloadSize = downloadSize; }

    public String getActivationKey() { return activationKey; }
    public void setActivationKey(String activationKey) { this.activationKey = activationKey; }
}