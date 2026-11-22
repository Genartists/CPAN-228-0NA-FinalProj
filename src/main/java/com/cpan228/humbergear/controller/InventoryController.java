package com.cpan228.humbergear.controller;

import com.cpan228.humbergear.model.HardwareItem;
import com.cpan228.humbergear.service.HardwareItemService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    private final HardwareItemService hardwareItemService;

    public InventoryController(HardwareItemService hardwareItemService) {
        this.hardwareItemService = hardwareItemService;
    }

    @GetMapping
    public String listInventory(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "createdAt") String sortBy,
            @RequestParam(required = false, defaultValue = "desc") String sortDir,
            Model model
    ) {
        List<HardwareItem> items = hardwareItemService.getItems(category, brand, search, sortBy, sortDir);

        model.addAttribute("items", items);
        model.addAttribute("selectedCategory", category != null ? category : "");
        model.addAttribute("selectedBrand", brand != null ? brand : "");
        model.addAttribute("search", search != null ? search : "");
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("categories", HardwareItemService.CATEGORIES);
        model.addAttribute("brands", HardwareItemService.BRANDS);

        return "inventory/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        if (!model.containsAttribute("hardwareItem")) {
            model.addAttribute("hardwareItem", new HardwareItem());
        }
        populateFormOptions(model);
        return "inventory/form";
    }

    @PostMapping("/new")
    public String createHardwareItem(
            @Valid @ModelAttribute("hardwareItem") HardwareItem hardwareItem,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            populateFormOptions(model);
            return "inventory/form";
        }

        hardwareItemService.saveItem(hardwareItem);
        redirectAttributes.addFlashAttribute(
                "successMessage",
                "Successfully added " + hardwareItem.getName() + " (" + hardwareItem.getSku() + ") to warehouse inventory."
        );
        return "redirect:/inventory";
    }

    private void populateFormOptions(Model model) {
        model.addAttribute("categories", HardwareItemService.CATEGORIES);
        model.addAttribute("brands", HardwareItemService.BRANDS);
        model.addAttribute("warehouses", HardwareItemService.WAREHOUSES);
    }
}
