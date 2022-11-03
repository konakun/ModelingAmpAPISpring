package com.konakun.modeling_amps.device_type;

import com.konakun.modeling_amps.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/device_type")
public class DeviceTypeController {
    @Autowired
    DeviceTypeRepository deviceTypeRepository;

    @GetMapping("/all")
    @ResponseBody
    public List<DeviceType> getAllDeviceTypes(HttpServletResponse response){
        List<DeviceType> deviceTypes = deviceTypeRepository.findAll();
        if(deviceTypes.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return deviceTypes;
    }

    @GetMapping("/available")
    @ResponseBody
    public List<DeviceType> getAvailableDeviceTypes(HttpServletResponse response){
        List<DeviceType> deviceTypes = deviceTypeRepository.findActivatedDeviceTypes();
        if(deviceTypes.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return deviceTypes;
    }

    @PostMapping("/add")
    @ResponseBody
    public DeviceType saveDeviceType(@RequestBody DeviceType deviceType, HttpServletResponse response){
        DeviceType addedDeviceType = deviceTypeRepository.save(deviceType);

        if(addedDeviceType != null){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_CREATED);
        return addedDeviceType;
    }

    @GetMapping("/{primary_key}")
    @ResponseBody
    public Optional<DeviceType> getDeviceType(@PathVariable Integer primary_key, HttpServletResponse response){
        Optional<DeviceType> deviceType = deviceTypeRepository.findById(primary_key);
        if(deviceType.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return deviceType;
    }

    @PutMapping("/{primary_key}")
    @ResponseBody
    public Optional<DeviceType> modifyDeviceType(@PathVariable Integer primaryKey, @RequestBody DeviceType deviceType,
                                       HttpServletResponse response){
        Integer modified = deviceTypeRepository.modifyDeviceType(primaryKey, deviceType);

        if(modified < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<DeviceType> retrievedDeviceType = deviceTypeRepository.findById(primaryKey);

        if(retrievedDeviceType.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);

        return retrievedDeviceType;
    }

    @DeleteMapping("/{primary_key}")
    @ResponseBody
    public Optional<DeviceType> deleteDeviceType(@PathVariable Integer primary_key, HttpServletResponse response){
        Integer deactivated= deviceTypeRepository.disableDeviceType(primary_key);
        if(deactivated < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<DeviceType> deviceType = deviceTypeRepository.findById(primary_key);

        if(deviceType.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;

        }
        response.setStatus(SC_OK);
        return deviceType;
    }
}
