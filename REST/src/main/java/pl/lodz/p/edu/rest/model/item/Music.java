package pl.lodz.p.edu.rest.model.item;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonProperty;

@BsonDiscriminator("music")
public class Music extends Item {
    @BsonProperty("genre")
    private MusicGenre genre;
    @BsonProperty("vinyl")
    private boolean vinyl;

    public Music(@BsonProperty("basePrice") int basePrice,
                 @BsonProperty("itemName") String itemName,
                 @BsonProperty("genre") MusicGenre genre,
                 @BsonProperty("vinyl") boolean vinyl) {
        super(basePrice, itemName);
        this.itemType = "music";
        this.genre = genre;
        this.vinyl = vinyl;
    }

    public Music() {

    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public boolean isVinyl() {
        return vinyl;
    }

    public void setVinyl(boolean vinyl) {
        this.vinyl = vinyl;
    }
}