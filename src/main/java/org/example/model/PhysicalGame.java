package org.example.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PHYSICAL")
public class PhysicalGame extends GameBase {

    private String mediaType;
    private Integer stock;

    public PhysicalGame() {}

    public PhysicalGame(String title, BigDecimal price, Integer releaseYear,
                        Developer developer, Genre genre,
                        String mediaType, Integer stock) {
        super(title, price, releaseYear, developer, genre);
        this.mediaType = mediaType;
        this.stock = stock;
    }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
