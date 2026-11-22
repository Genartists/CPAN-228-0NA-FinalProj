package com.cpan228.humbergear.controller;

import com.cpan228.humbergear.service.HardwareItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    private final HardwareItemService hardwareItemService;

    public PageController(HardwareItemService hardwareItemService) {
        this.hardwareItemService = hardwareItemService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("totalSkus", hardwareItemService.getTotalCount());
        model.addAttribute("totalUnits", hardwareItemService.getTotalStockUnits());
        model.addAttribute("inventoryValue", hardwareItemService.getTotalInventoryValue());
        model.addAttribute("lowStockCount", hardwareItemService.getLowStockCount());
        model.addAttribute("recentItems", hardwareItemService.getItems(null, null, null, "createdAt", "desc"));
        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/depots")
    public String depots() {
        return "depots";
    }
}
