package com.konakun.modeling_amps.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    BrandRepository brandRepo;

    @PostMapping("/add")
    @ResponseBody
    public Brand saveBrand(@RequestBody Brand brand) {
        Brand saved_brand = brandRepo.save(brand);
        return saved_brand;
    }

    @DeleteMapping("/{primary_key}/delete")
    @ResponseBody
    public Brand deleteBrand(@PathVariable Integer primary_key, HttpServletResponse response){
        return null;
    }

    @GetMapping("/find/{primary_key}")
    @ResponseBody
    public Optional<Brand> getBrand(@PathVariable Integer primary_key, HttpServletResponse response){
        Optional<Brand> brand_item = brandRepo.findById(primary_key);
        System.out.println(brand_item);
        if(brand_item.isEmpty()){
            response.setStatus(400);
            return null;
        }
        response.setStatus(200);
        return brand_item;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Brand> getAllBrands(){
        return brandRepo.findAll();
    }

    @GetMapping("/available")
    @ResponseBody
    public List<Brand> getAvailableBrands(){
        return brandRepo.findActivated();
    }
}
