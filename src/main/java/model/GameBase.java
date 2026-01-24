package model;

public abstract class GameBase {
    protected int gameId;
    protected String title;
    protected int releaseYear;
    protected String publisher;
    protected Genre genre;
    protected Developer developer;
    protected String gameType;

    public GameBase(int gameId, String title, int releaseYear, String publisher,
                    Genre genre, Developer developer, String gameType) {
        this.gameId = gameId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.publisher = publisher;
        this.genre = genre;
        this.developer = developer;
        this.gameType = gameType;
    }

    public abstract void displayInfo();
    public abstract String getGameDetails();

    public boolean isNewRelease() {
        return releaseYear >= 2020;
    }

    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getReleaseYear() { return releaseYear; }
    public void setReleaseYear(int releaseYear) { this.releaseYear = releaseYear; }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; }

    public Genre getGenre() { return genre; }
    public void setGenre(Genre genre) { this.genre = genre; }

    public Developer getDeveloper() { return developer; }
    public void setDeveloper(Developer developer) { this.developer = developer; }

    public String getGameType() { return gameType; }
    public void setGameType(String gameType) { this.gameType = gameType; }

    @Override
    public String toString() {
        return "Game: " + title + " (" + releaseYear + ") by " + publisher;
    }
}