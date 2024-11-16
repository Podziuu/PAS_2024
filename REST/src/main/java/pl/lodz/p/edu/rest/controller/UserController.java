package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.UpdateUserDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.model.user.Role;
import pl.lodz.p.edu.rest.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers(@RequestParam(required = false) Role role) {
        List<UserDTO> users;
        if (role == null) {
            users = userService.getAllUsers();
        } else {
            users = userService.getUsersByRole(role);
        }
        return ResponseEntity.ok(users);
    }

    @PostMapping()
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        UserDTO user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable ObjectId id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserDTO> updateUser(@PathVariable ObjectId id, @RequestBody UpdateUserDTO userDTO) {
        UpdateUserDTO user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<UserDTO> activateUser(@PathVariable ObjectId id) {
        UserDTO user = userService.activateUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<UserDTO> deactivateUser(@PathVariable ObjectId id) {
        UserDTO user = userService.deactivateUser(id);
        return ResponseEntity.ok(user);
    }
}
