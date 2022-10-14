package com.konakun.modeling_amps.brand;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query(value = "SELECT * FROM BRANDS WHERE ACTIVE = TRUE", nativeQuery = true)
    List<Brand> findActivated();

}
