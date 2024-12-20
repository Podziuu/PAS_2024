package pl.lodz.p.edu.mvc.dto;

public class MovieDTO extends ItemDTO {
    private int minutes;
    private boolean casette;

    public MovieDTO() {
    }

    public MovieDTO(String id, int basePrice, String itemName, boolean available, int minutes, boolean casette) {
        super(id, basePrice, itemName, available);
        this.itemType = "movie";
        this.minutes = minutes;
        this.casette = casette;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isCasette() {
        return casette;
    }

    public void setCasette(boolean casette) {
        this.casette = casette;
    }
}