package com.konakun.modeling_amps.wattage;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

import static com.konakun.modeling_amps.utilities.Constants.ACTIVE;
import static com.konakun.modeling_amps.utilities.Constants.INACTIVE;

@Scope("singleton")
public interface WattageRepository extends JpaRepository<Wattage, Integer> {
    @Query("SELECT p from Wattage p WHERE p.active = " + ACTIVE)
    List<Wattage> findActivatedPowers();

    @Modifying
    @Transactional
    @Query("UPDATE Wattage p SET p.active = " + INACTIVE + " WHERE p.pk_wattage = :primary_key")
    Integer disablePower(@Param("primary_key") Integer primary_key);

    @Modifying
    @Transactional
    @Query("UPDATE Wattage w SET w.name = :#{#wattage.name}, w.wattage = :#{#wattage.wattage} , " +
            "w.active = " + ACTIVE + " WHERE w.pk_wattage = :primary_key")
    Integer modifyPower(@Param("primary_key") Integer primary_key, @Param("wattage") Wattage wattage);
}
