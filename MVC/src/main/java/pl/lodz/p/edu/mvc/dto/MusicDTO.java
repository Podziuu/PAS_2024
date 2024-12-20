package pl.lodz.p.edu.mvc.dto;

import pl.lodz.p.edu.mvc.model.item.MusicGenre;

public class MusicDTO extends ItemDTO {
    private MusicGenre genre;
    private boolean vinyl;

    public MusicDTO() {

    }

    public MusicDTO(String id, int basePrice, String itemName, boolean available, MusicGenre genre, boolean vinyl) {
        super(id, basePrice, itemName, available);
        this.itemType = "music";
        this.genre = genre;
        this.vinyl = vinyl;
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