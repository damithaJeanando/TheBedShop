package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.CustomOrders;
import org.springframework.data.repository.CrudRepository;

public interface CustomOrdersRepository extends CrudRepository<CustomOrders, String> {

    Iterable<CustomOrders> findAllByUserId(String userId);
}
