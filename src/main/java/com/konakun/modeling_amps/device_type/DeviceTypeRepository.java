package com.konakun.modeling_amps.device_type;

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
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {
    @Query("SELECT dt FROM DeviceType dt WHERE dt.active = " + ACTIVE)
    List<DeviceType> findActivatedDeviceTypes();

    @Modifying
    @Transactional
    @Query("UPDATE DeviceType dt SET dt.active = " + INACTIVE + " WHERE dt.pk_type = :primary_key")
    Integer disableDeviceType(@Param("primaryKey") Integer primaryKey);

    @Modifying
    @Transactional
    @Query("UPDATE DeviceType dt SET dt.name = :#{#deviceType.name}, dt.active = "+ ACTIVE
            + " WHERE dt.pk_type = :primaryKey")
    Integer modifyDeviceType(@Param("primaryKey") Integer primaryKey, @Param("deviceType") DeviceType deviceType);
}
