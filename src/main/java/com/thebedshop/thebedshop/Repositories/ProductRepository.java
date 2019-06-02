package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    Iterable<Product> findAllByCategoryId(String categoryId);
}
