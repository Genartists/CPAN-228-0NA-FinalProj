package com.cpan228.humbergear.service;

import com.cpan228.humbergear.model.HardwareItem;
import com.cpan228.humbergear.repository.HardwareItemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class HardwareItemService {

    private final HardwareItemRepository repository;

    public static final List<String> CATEGORIES = List.of(
            "GPU", "CPU", "Motherboard", "RAM", "Storage", "Peripheral", "PowerSupply"
    );

    public static final List<String> BRANDS = List.of(
            "NVIDIA", "AMD", "Intel", "ASUS", "Corsair", "Logitech", "MSI"
    );

    public static final List<String> WAREHOUSES = List.of(
            "Toronto Hub - Zone A",
            "Vancouver Depot - Zone B",
            "Montreal Facility - Zone C"
    );

    public HardwareItemService(HardwareItemRepository repository) {
        this.repository = repository;
    }

    public List<HardwareItem> getItems(String category, String brand, String search, String sortBy, String sortDir) {
        String sortProperty = (sortBy != null && !sortBy.isBlank()) ? sortBy : "createdAt";
        Sort.Direction direction = "asc".equalsIgnoreCase(sortDir) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortProperty);

        return repository.findByFilters(category, brand, search, sort);
    }

    public HardwareItem saveItem(HardwareItem item) {
        return repository.save(item);
    }

    public Optional<HardwareItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public long getTotalCount() {
        return repository.count();
    }

    public int getTotalStockUnits() {
        return repository.findAll().stream()
                .mapToInt(item -> item.getStockQuantity() != null ? item.getStockQuantity() : 0)
                .sum();
    }

    public BigDecimal getTotalInventoryValue() {
        return repository.findAll().stream()
                .filter(item -> item.getPrice() != null && item.getStockQuantity() != null)
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getStockQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public long getLowStockCount() {
        return repository.findAll().stream()
                .filter(item -> item.getStockQuantity() != null && item.getStockQuantity() <= 10)
                .count();
    }
}
