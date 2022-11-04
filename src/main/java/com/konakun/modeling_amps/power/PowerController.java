package com.konakun.modeling_amps.power;

import com.konakun.modeling_amps.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/power")
public class PowerController {
    @Autowired
    PowerRepository powerRepo;

    @GetMapping("/all")
    @ResponseBody
    public List<Power> getAllPowers(HttpServletResponse response){
        List<Power> powers = powerRepo.findAll();
        if(powers.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return powers;
    }

    @GetMapping("/available")
    @ResponseBody
    public List<Power> getAvailablePowers(HttpServletResponse response){
        List<Power> availablePowers = powerRepo.findActivatedPowers();
        if(availablePowers.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return availablePowers;
    }

    @PostMapping("/add")
    @ResponseBody
    public Power savePower(@RequestBody Power power, HttpServletResponse response){
        Power addedPower = powerRepo.save(power);

        if(addedPower != null){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_CREATED);
        return addedPower;
    }

    @GetMapping("/{primary_key}")
    @ResponseBody
    public Optional<Power> getPower(@PathVariable Integer primary_key, HttpServletResponse response){
        Optional<Power> power = powerRepo.findById(primary_key);
        if(power.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return power;
    }

    @PutMapping("/{primary_key}")
    @ResponseBody
    public Optional<Power> modifyPower(@PathVariable Integer primary_key, @RequestBody Power power,
                                       HttpServletResponse response){
        Integer modified = powerRepo.modifyPower(primary_key, power);

        if(modified < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<Power> modifiedPower = powerRepo.findById(primary_key);

        if(modifiedPower.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);

        return modifiedPower;
    }

    @DeleteMapping("/{primary_key}")
    @ResponseBody
    public Optional<Power> deletePower(@PathVariable Integer primary_key, HttpServletResponse response){
        Integer deactivated = powerRepo.disablePower(primary_key);
        if(deactivated < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<Power> deactivatedPower = powerRepo.findById(primary_key);

        if(deactivatedPower.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;

        }
        response.setStatus(SC_OK);
        return deactivatedPower;
    }


}
