package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
}
