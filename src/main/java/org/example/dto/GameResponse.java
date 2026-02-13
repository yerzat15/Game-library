package org.example.dto;

import java.math.BigDecimal;

public class GameResponse {

    private Integer id;
    private String title;
    private BigDecimal price;
    private Integer releaseYear;

    private Integer developerId;
    private String developerName;

    private Integer genreId;
    private String genreName;

    private GameType type;

    // DIGITAL
    private String platform;
    private BigDecimal downloadSizeGb;

    // PHYSICAL
    private String mediaType;
    private Integer stock;

    public GameResponse() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public Integer getDeveloperId() { return developerId; }
    public void setDeveloperId(Integer developerId) { this.developerId = developerId; }

    public String getDeveloperName() { return developerName; }
    public void setDeveloperName(String developerName) { this.developerName = developerName; }

    public Integer getGenreId() { return genreId; }
    public void setGenreId(Integer genreId) { this.genreId = genreId; }

    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }

    public GameType getType() { return type; }
    public void setType(GameType type) { this.type = type; }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public BigDecimal getDownloadSizeGb() { return downloadSizeGb; }
    public void setDownloadSizeGb(BigDecimal downloadSizeGb) { this.downloadSizeGb = downloadSizeGb; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
