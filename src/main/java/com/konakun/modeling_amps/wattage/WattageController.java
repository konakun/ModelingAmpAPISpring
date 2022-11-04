package com.konakun.modeling_amps.wattage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/power")
public class WattageController {
    @Autowired
    WattageRepository powerRepo;

    @GetMapping("/all")
    @ResponseBody
    public List<Wattage> getAllPowers(HttpServletResponse response){
        List<Wattage> wattages = powerRepo.findAll();
        if(wattages.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return wattages;
    }

    @GetMapping("/available")
    @ResponseBody
    public List<Wattage> getAvailablePowers(HttpServletResponse response){
        List<Wattage> availableWattages = powerRepo.findActivatedPowers();
        if(availableWattages.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return availableWattages;
    }

    @PostMapping("/add")
    @ResponseBody
    public Wattage savePower(@RequestBody Wattage wattage, HttpServletResponse response){
        Wattage addedWattage = powerRepo.save(wattage);

        if(addedWattage != null){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_CREATED);
        return addedWattage;
    }

    @GetMapping("/{primary_key}")
    @ResponseBody
    public Optional<Wattage> getPower(@PathVariable Integer primary_key, HttpServletResponse response){
        Optional<Wattage> power = powerRepo.findById(primary_key);
        if(power.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return power;
    }

    @PutMapping("/{primary_key}")
    @ResponseBody
    public Optional<Wattage> modifyPower(@PathVariable Integer primary_key, @RequestBody Wattage wattage,
                                         HttpServletResponse response){
        Integer modified = powerRepo.modifyPower(primary_key, wattage);

        if(modified < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<Wattage> modifiedPower = powerRepo.findById(primary_key);

        if(modifiedPower.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);

        return modifiedPower;
    }

    @DeleteMapping("/{primary_key}")
    @ResponseBody
    public Optional<Wattage> deletePower(@PathVariable Integer primary_key, HttpServletResponse response){
        Integer deactivated = powerRepo.disablePower(primary_key);
        if(deactivated < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<Wattage> deactivatedPower = powerRepo.findById(primary_key);

        if(deactivatedPower.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;

        }
        response.setStatus(SC_OK);
        return deactivatedPower;
    }


}
