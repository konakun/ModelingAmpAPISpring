package com.konakun.modeling_amps.brand;


import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.konakun.modeling_amps.utilities.Constants.ACTIVE;
import static com.konakun.modeling_amps.utilities.Constants.INACTIVE;

@Scope("singleton")
public interface BrandRepository extends JpaRepository<Brand, Integer> {

    @Query("SELECT b from Brand b WHERE b.active = " + ACTIVE)
    List<Brand> findActivatedBrands();

    @Modifying
    @Transactional
    @Query("UPDATE Brand b SET b.active = " + INACTIVE + " WHERE b.pk_brand = :primary_key")
    Integer disableBrand(@Param("primary_key") Integer primary_key);

    @Modifying
    @Transactional
    @Query("UPDATE Brand b SET b.name = :#{#brand.name}, b.active = " + ACTIVE
            + " WHERE b.pk_brand = :primary_key")
    Integer modifyBrand(@Param("primary_key") Integer primary_key, @Param("brand") Brand brand);
}
