package org.example.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("DIGITAL")
public class DigitalGame extends GameBase {

    private String platform;
    private BigDecimal downloadSizeGb;

    public DigitalGame() {}

    public DigitalGame(String title, BigDecimal price, Integer releaseYear,
                       Developer developer, Genre genre,
                       String platform, BigDecimal downloadSizeGb) {
        super(title, price, releaseYear, developer, genre);
        this.platform = platform;
        this.downloadSizeGb = downloadSizeGb;
    }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) { this.platform = platform; }

    public BigDecimal getDownloadSizeGb() { return downloadSizeGb; }
    public void setDownloadSizeGb(BigDecimal downloadSizeGb) { this.downloadSizeGb = downloadSizeGb; }
}
