package com.thebedshop.thebedshop.Repositories;

import com.thebedshop.thebedshop.Models.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, String> {
}
