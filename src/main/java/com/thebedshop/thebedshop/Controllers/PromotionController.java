package com.thebedshop.thebedshop.Controllers;


import com.thebedshop.thebedshop.Models.Promotion;
import com.thebedshop.thebedshop.Repositories.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/promotion")
public class PromotionController {

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping(path = "/getpromotion")
    public @ResponseBody Iterable<Promotion> getPromotions(){
        System.out.println("Get all promotions");
        return promotionRepository.findAll();
    }

    @GetMapping(path = "/{promotion_id}")
    public Promotion getPromotion(@PathVariable String promotion_id){

        return promotionRepository.findById(promotion_id).get();
    }

    @PostMapping(path = "/new_promotion")
    public Promotion newPromotion(@RequestBody Promotion promotion){

        System.out.println(promotion.getPromotionName() + "is added");

        return  promotionRepository.save(promotion);
    }

    @PutMapping(path = "/update_promotion")
    public void updatePromotion(@RequestBody Promotion promotion){

        promotionRepository.save(promotion);
    }

    @DeleteMapping(path = "/{promotion_id}")
    public void deletePromotion(@PathVariable String promotion_id) {

        promotionRepository.deleteById(promotion_id);
    }
}
