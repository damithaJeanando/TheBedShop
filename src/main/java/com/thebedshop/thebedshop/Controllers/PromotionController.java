package com.thebedshop.thebedshop.Controllers;


import com.thebedshop.thebedshop.Models.Promotion;
import com.thebedshop.thebedshop.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping(path = "/public/all")
    public @ResponseBody Iterable<Promotion> getPromotions(){
        System.out.println("Get all promotions");
        return promotionRepository.findAll();
    }

    @GetMapping(path = "/public/{promoid}")
    public Promotion getPromotion(@PathVariable String promotion_id){

        return promotionRepository.findById(promotion_id).get();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(path = "/auth/add")
    public Promotion newPromotion(@RequestBody Promotion promotion){

        System.out.println(promotion.getPromotionName() + "is added");

        return  promotionRepository.save(promotion);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(path = "/auth")
    public void updatePromotion(@RequestBody Promotion promotion){

        promotionRepository.save(promotion);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/auth/{pid}")
    public void deletePromotion(@PathVariable String promotion_id) {

        promotionRepository.deleteById(promotion_id);
    }
}
