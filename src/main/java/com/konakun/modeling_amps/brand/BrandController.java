package com.konakun.modeling_amps.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static javax.servlet.http.HttpServletResponse.*;

@RestController
@RequestMapping("/brands")
public class BrandController {
    @Autowired
    BrandRepository brandRepo;

    @GetMapping("/all")
    @ResponseBody
    public List<Brand> getAllBrands(HttpServletResponse response){
        List<Brand> brands =  brandRepo.findAll();
        if(brands.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return brands;
    }

    @GetMapping("/available")
    @ResponseBody
    public List<Brand> getAvailableBrands(HttpServletResponse response){
        List<Brand> activated_brands = brandRepo.findActivatedBrands();
        if(activated_brands.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return activated_brands;
    }

    @PostMapping("/add")
    @ResponseBody
    public Brand saveBrand(@RequestBody Brand brand, HttpServletResponse response) {
        Brand saved_brand = brandRepo.save(brand);

        if(saved_brand != null){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_CREATED);
        return saved_brand;
    }

    @GetMapping("/{primary_key}")
    @ResponseBody
    public Optional<Brand> getBrand(@PathVariable Integer primary_key, HttpServletResponse response){
        Optional<Brand> brand_item = brandRepo.findById(primary_key);
        if(brand_item.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);
        return brand_item;
    }

    @PutMapping("/{primary_key}")
    @ResponseBody
    public Optional<Brand> modifyBrand(@PathVariable Integer primary_key, @RequestBody Brand brand,
                                       HttpServletResponse response){
        Integer modify_brand = brandRepo.modifyBrand(primary_key, brand);

        if(modify_brand < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<Brand> modified_brand = brandRepo.findById(primary_key);

        if(modify_brand < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }
        response.setStatus(SC_OK);

        return modified_brand;
    }

    @DeleteMapping("/{primary_key}")
    @ResponseBody
    public Optional<Brand> deleteBrand(@PathVariable Integer primary_key, HttpServletResponse response){
        Integer deactivate_brand = brandRepo.disableBrand(primary_key);
        if(deactivate_brand < 1){
            response.setStatus(SC_BAD_REQUEST);
            return null;
        }

        Optional<Brand> deactivated_brand = brandRepo.findById(primary_key);

        if(deactivated_brand.isEmpty()){
            response.setStatus(SC_BAD_REQUEST);
            return null;

        }
        response.setStatus(SC_OK);
        return deactivated_brand;
    }
}
