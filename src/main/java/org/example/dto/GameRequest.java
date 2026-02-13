package org.example.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class GameRequest {

    @NotBlank
    private String title;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal price;

    @Min(1970)
    @Max(2100)
    private Integer releaseYear;

    @NotNull
    private Integer developerId;

    @NotNull
    private Integer genreId;

    @NotNull
    private GameType type;

    // DIGITAL
    private String platform;

    @DecimalMin(value = "0.0", inclusive = true)
    private BigDecimal downloadSizeGb;

    // PHYSICAL
    private String mediaType;

    @Min(0)
    private Integer stock;

    public GameRequest() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public Integer getDeveloperId() { return developerId; }
    public void setDeveloperId(Integer developerId) { this.developerId = developerId; }

    public Integer getGenreId() { return genreId; }
    public void setGenreId(Integer genreId) { this.genreId = genreId; }

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
