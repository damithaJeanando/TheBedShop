package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
}
