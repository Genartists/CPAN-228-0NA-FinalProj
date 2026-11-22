package com.cpan228.humbergear.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "hardware_items")
public class HardwareItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "SKU code is required")
    @Pattern(regexp = "^[A-Z]{2,4}(-[A-Z0-9]{2,6})+$", message = "SKU must follow format (e.g., NX-GPU-4090)")
    @Column(nullable = false, unique = true, length = 20)
    private String sku;

    @NotBlank(message = "Hardware name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Brand must be selected")
    @Column(nullable = false, length = 50)
    private String brand;

    @NotBlank(message = "Category must be selected")
    @Column(nullable = false, length = 50)
    private String category;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "1.00", message = "Price must be at least $1.00")
    @DecimalMax(value = "10000.00", message = "Price cannot exceed $10,000.00")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Stock cannot be negative")
    @Max(value = 10000, message = "Stock cannot exceed 10,000 units")
    @Column(nullable = false)
    private Integer stockQuantity;

    @NotBlank(message = "Warehouse location is required")
    @Column(nullable = false, length = 100)
    private String warehouseLocation;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    @Column(nullable = false, length = 500)
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public HardwareItem() {
    }

    public HardwareItem(String sku, String name, String brand, String category, BigDecimal price,
                        Integer stockQuantity, String warehouseLocation, String description) {
        this.sku = sku;
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.warehouseLocation = warehouseLocation;
        this.description = description;
    }

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
