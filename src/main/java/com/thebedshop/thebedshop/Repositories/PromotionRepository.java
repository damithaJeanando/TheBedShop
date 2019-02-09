package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PromotionRepository extends CrudRepository<Promotion, String> {
}
