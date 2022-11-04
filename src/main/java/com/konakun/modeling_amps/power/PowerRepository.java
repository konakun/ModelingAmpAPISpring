package com.konakun.modeling_amps.power;

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
public interface PowerRepository extends JpaRepository<Power, Integer> {
    @Query("SELECT p from Power p WHERE p.active = " + ACTIVE)
    List<Power> findActivatedPowers();

    @Modifying
    @Transactional
    @Query("UPDATE Power p SET p.active = " + INACTIVE + " WHERE p.pk_power = :primary_key")
    Integer disablePower(@Param("primary_key") Integer primary_key);

    @Modifying
    @Transactional
    @Query("UPDATE Power p SET p.name = :#{#power.name}, p.active = " + ACTIVE
            + " WHERE p.pk_power = :primary_key")
    Integer modifyPower(@Param("primary_key") Integer primary_key, @Param("brand") Power power);
}
