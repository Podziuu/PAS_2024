package pl.lodz.p.edu.mvc.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.dto.RentDTO;
import pl.lodz.p.edu.mvc.service.RentService;

import java.util.List;

@Controller
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/")
    public String showAllocations(Model model) {
        List<RentDTO> rents = rentService.getRents();

        if (rents.isEmpty()) {
            model.addAttribute("message", "Brak rentów");
        }

        model.addAttribute("rents", rents);
        return "allocations";
    }

    @GetMapping("/createRent")
    public String showRegistrationForm(Model model) {
        model.addAttribute("rentDTO", new RentDTO());
        return "create_allocation";
    }

    @PostMapping("/createRent")
    public String registerClient(@Valid @ModelAttribute RentDTO rentDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "create_allocation";
        }

        try {
            RentDTO rent = rentService.createRent(rentDTO);
            model.addAttribute("rent", rent);
            return "create_success";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/return/{id}")
    public String returnRent(@PathVariable String id) {
        rentService.returnRent(id);
        return "redirect:/rents";
    }
}
