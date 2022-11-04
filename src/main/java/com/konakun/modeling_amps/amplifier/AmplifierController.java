package com.konakun.modeling_amps.amplifier;

import com.konakun.modeling_amps.brand.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/amplifier")
public class AmplifierController {
    @Autowired
    AmplifierRepository amplifierRepo;

    @GetMapping("/all ")
    @ResponseBody
    public List<AmplifierBean> getAmplifiers(HttpServletResponse response){
        List<AmplifierBean> amplifiers = amplifierRepo.fetch();

        if(amplifiers.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return amplifiers;
    }

    @GetMapping("/available")
    @ResponseBody
    public List<AmplifierBean> getAvailableAmplifiers(HttpServletResponse response){
        List<AmplifierBean> amplifiers = amplifierRepo.fetchAvailable();

        if(amplifiers.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return amplifiers;
    }

    @PostMapping("/add")
    @ResponseBody
    public Optional<AmplifierBean> addAmplifier(@RequestBody Amplifier amplifier, HttpServletResponse response){
        Amplifier addedAmplifier = amplifierRepo.save(amplifier);

        if(addedAmplifier == null){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_CREATED);
        return amplifierRepo.retrieveById(addedAmplifier.getPk_amplifier());
    }

    @GetMapping("/{primary_key}/amplifier")
    @ResponseBody
    public Optional<AmplifierBean> getAmplifier(@PathVariable Integer primary_key, HttpServletResponse response){
        Optional<AmplifierBean> amplifier = amplifierRepo.retrieveById(primary_key);

        if(amplifier.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return amplifier;
    }

    @GetMapping("/{primary_key}/brand")
    @ResponseBody
    public List<AmplifierBean> getAmplifierByBrand(@PathVariable Integer primary_key, HttpServletResponse response){
        List<AmplifierBean> amplifiers = amplifierRepo.retrieveByBrand(primary_key);

        if(amplifiers.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return amplifiers;
    }

    @GetMapping("/{primary_key}/wattage")
    @ResponseBody
    public List<AmplifierBean> getAmplifierByWattage(@PathVariable Integer primary_key, HttpServletResponse response){
        List<AmplifierBean> amplifiers = amplifierRepo.retrieveByWattage(primary_key);

        if(amplifiers.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return amplifiers;
    }

    @PutMapping("/{primary_key}")
    @ResponseBody
    public Optional<AmplifierBean> modifyAmplifier(@PathVariable Integer primary_key, HttpServletResponse response,
                                                   Amplifier amplifier){
        Integer modified = amplifierRepo.modify(primary_key, amplifier);

        if(modified < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<AmplifierBean> modifiedAmplifier = amplifierRepo.retrieveById(primary_key);

        if(modifiedAmplifier.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);

        return modifiedAmplifier;
    }

    @DeleteMapping("/{primary_key}")
    @ResponseBody
    public Optional<AmplifierBean> deleteAmplifier(@PathVariable Integer primary_key, HttpServletResponse response){
        Integer deactivate = amplifierRepo.deactivate(primary_key);
        if(deactivate < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<AmplifierBean> deactivated_amplifier = amplifierRepo.retrieveById(primary_key);

        if(deactivated_amplifier.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;

        }
        response.setStatus(SC_OK);
        return deactivated_amplifier;
    }
}
