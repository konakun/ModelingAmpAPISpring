package com.konakun.modeling_amps.amplifier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.konakun.modeling_amps.utilities.Constants.ACTIVE;
import static com.konakun.modeling_amps.utilities.Constants.INACTIVE;

public interface AmplifierRepository extends JpaRepository<Amplifier, Integer> {
    @Query("SELECT a.pk_amplifier, b.name as brand, a.model, a.series, a.name, w.name AS wattage" +
            " FROM Amplifier a JOIN Brand b ON b.pk_brand = a.pk_brand" +
            " JOIN Wattage w on w.pk_wattage = a.pk_wattage")
    List<AmplifierBean> fetch();

    @Query("SELECT a.pk_amplifier, b.name as brand, a.model, a.series, a.name, w.name AS wattage" +
            " FROM Amplifier a JOIN Brand b ON b.pk_brand = a.pk_brand" +
            " JOIN Wattage w on w.pk_wattage = a.pk_wattage WHERE a.active = " + ACTIVE)
    List<AmplifierBean> fetchAvailable();

    @Query("SELECT a.pk_amplifier, b.name as brand, a.model, a.series, a.name, w.name AS wattage" +
            " FROM Amplifier a JOIN Brand b ON b.pk_brand = a.pk_brand" +
            " JOIN Wattage w on w.pk_wattage = a.pk_wattage WHERE a.pk_amplifier = :primary_key")
    Optional<AmplifierBean> retrieveById(@Param("primary_key") Integer primary_key);

    @Query("SELECT a.pk_amplifier, b.name as brand, a.model, a.series, a.name, w.name AS wattage" +
            " FROM Amplifier a JOIN Brand b ON b.pk_brand = a.pk_brand" +
            " JOIN Wattage w on w.pk_wattage = a.pk_wattage WHERE a.pk_brand = :pk_brand")
    List<AmplifierBean> retrieveByBrand(@Param("pk_brand") Integer pk_brand);

    @Query("SELECT a.pk_amplifier, b.name as brand, a.model, a.series, a.name, w.name AS wattage" +
            " FROM Amplifier a JOIN Brand b ON b.pk_brand = a.pk_brand" +
            " JOIN Wattage w on w.pk_wattage = a.pk_wattage WHERE a.pk_wattage = :pk_wattage")
    List<AmplifierBean> retrieveByWattage(@Param("pk_wattage") Integer pk_wattage);

    @Modifying
    @Transactional
    @Query("UPDATE Amplifier a SET a.active = " + INACTIVE + " WHERE a.pk_amplifier = :primary_key")
    Integer deactivate(@Param("primary_key") Integer primary_key);

    @Modifying
    @Transactional
    @Query("UPDATE Amplifier a SET a.name = :#{#amplifier.name}, a.model = :#{#amplifier.model}, " +
            "a.series = :#{#amplifier.series}, a.pk_wattage = :#{#amplifier.pk_wattage}, " +
            "a.pk_brand = :#{#amplifier.pk_brand}, a.active = " + ACTIVE + " WHERE a.pk_amplifier = :primary_key")
    Integer modify(@Param("primary_key") Integer primary_key, @Param("amplifier") Amplifier amplifier);
}
