package com.cpan228.humbergear.repository;

import com.cpan228.humbergear.model.HardwareItem;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HardwareItemRepository extends JpaRepository<HardwareItem, Long> {

        @Query("SELECT h FROM HardwareItem h WHERE " +
                        "(:category IS NULL OR :category = '' OR h.category = :category) AND " +
                        "(:brand IS NULL OR :brand = '' OR h.brand = :brand) AND " +
                        "(:search IS NULL OR :search = '' OR LOWER(h.name) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(h.sku) LIKE LOWER(CONCAT('%', :search, '%')))")
        List<HardwareItem> findByFilters(
                        @Param("category") String category,
                        @Param("brand") String brand,
                        @Param("search") String search,
                        Sort sort);

        boolean existsBySku(String sku);
}
