package pl.lodz.p.edu.rest.controller;

import jakarta.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.RentDTO;
import pl.lodz.p.edu.rest.service.RentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/rents")
public class RentController {
    RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentDTO rentItem(@RequestBody @Valid RentDTO rentDTO) {
        if (rentDTO.getBeginTime() == null) {
            rentDTO.setBeginTime(LocalDateTime.now());
        }
        return rentService.rentItem(rentDTO);
    }

    @PutMapping("return/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void returnItem(@PathVariable ObjectId id) {
        rentService.returnRent(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RentDTO> getRent(@PathVariable ObjectId id) {
        RentDTO rentDTO = rentService.getRentById(id);
        return ResponseEntity.ok(rentDTO);
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getActiveRents() {
        List<RentDTO> activeRents = rentService.getActiveRents();
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("inactive")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getInactiveRents() {
        List<RentDTO> inactiveRents = rentService.getInactiveRents();
        return ResponseEntity.ok(inactiveRents);
    }

    @GetMapping("/item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getRentsByItem(@PathVariable ObjectId itemId) {
        List<RentDTO> rents = rentService.getRentsByItem(itemId);
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/active/item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getActiveRentsByItem(@PathVariable ObjectId itemId) {
        List<RentDTO> activeRents = rentService.getActiveRentsByItem(itemId);
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/inactive/item/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getInactiveRentsByItem(@PathVariable ObjectId itemId) {
        List<RentDTO> activeRents = rentService.getInactiveRentsByItem(itemId);
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/client/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getRentsByClient(@PathVariable ObjectId clientId) {
        List<RentDTO> rents = rentService.getRentsByClient(clientId);
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/active/client/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getActiveRentsByClient(@PathVariable ObjectId clientId) {
        List<RentDTO> activeRents = rentService.getActiveRentsByClient(clientId);
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/inactive/client/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<RentDTO>> getInactiveRentsByClient(@PathVariable ObjectId clientId) {
        List<RentDTO> activeRents = rentService.getInactiveRentsByClient(clientId);
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/isRented/{itemId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Boolean> isItemRented(@PathVariable ObjectId itemId) {
        boolean isRented = rentService.isItemRented(itemId);
        return ResponseEntity.ok(isRented);
    }
}