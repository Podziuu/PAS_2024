package pl.lodz.p.edu.rest.dto;

import jakarta.validation.constraints.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentDTO {
    private String id;
    @NotNull(message = "Begin time cannot be null")
    @PastOrPresent(message = "Begin time must be in the past or present")
    private LocalDateTime beginTime;
    @NotNull(message = "End time cannot be null")
    @Future(message = "End time must be in the future")
    private LocalDateTime endTime;
    @Min(value = 0, message = "Rent cost must be at least 0")
    private int rentCost;
    private boolean archive;
    @NotNull(message = "Client ID cannot be null")
    @NotEmpty(message = "Client ID cannot be empty")
    private String clientId;
    @NotNull(message = "Item ID cannot be null")
    @NotEmpty(message = "Item ID cannot be empty")
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

    public RentDTO(LocalDateTime beginTime, LocalDateTime endTime, int rentCost, boolean archive, String clientId, String itemId) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.rentCost = rentCost;
        this.archive = archive;
        this.clientId = clientId;
        this.itemId = itemId;
    }

    public RentDTO(LocalDateTime beginTime, int rentCost, String clientId, String itemId) {
        this.beginTime = beginTime;
        this.rentCost = rentCost;
        this.clientId = clientId;
        this.itemId = itemId;
    }

    public RentDTO(int rentCost, String clientId, String itemId) {
        this.rentCost = rentCost;
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