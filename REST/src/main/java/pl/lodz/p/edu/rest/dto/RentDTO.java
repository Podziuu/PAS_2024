package pl.lodz.p.edu.rest.dto;

import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentDTO {
    private String id;
    private LocalDateTime beginTime;
    private LocalDateTime endTime;
    private int rentCost;
    private boolean archive;
    private String clientId;
    private String itemId;

    public RentDTO() {
    }

    public RentDTO(String id, LocalDateTime beginTime, LocalDateTime endTime,
                   int rentCost, boolean archive, String clientId, String itemId) {
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.rentCost = rentCost;
        this.archive = archive;
        this.clientId = clientId;
        this.itemId = itemId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public int getRentCost() {
        return rentCost;
    }

    public void setRentCost(int rentCost) {
        this.rentCost = rentCost;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}