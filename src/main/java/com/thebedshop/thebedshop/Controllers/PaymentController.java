package com.thebedshop.thebedshop.Controllers;

import com.thebedshop.thebedshop.Models.Payment;
import com.thebedshop.thebedshop.Models.Product;
import com.thebedshop.thebedshop.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping(path = "/getall")
    public @ResponseBody
    Iterable<Payment> getPayments(){
        System.out.println("Get all payments");
        return paymentRepository.findAll();
    }

    @GetMapping(path = "/{payment_id}")
    public Payment getPayment(@PathVariable String payment_id){

        return paymentRepository.findById(payment_id).get();
    }

    @PostMapping(path = "/new_payment")
    public Payment newPayment(@RequestBody Payment payment){

        System.out.println(payment.getPaymentName() + "is added");

        return  paymentRepository.save(payment);
    }

    @PutMapping(path = "/update_payment")
    public void updatePayment(@RequestBody Payment payment){

        paymentRepository.save(payment);
    }

    @DeleteMapping(path = "/{payment_id}")
    public void deletePayment(@PathVariable String payment_id) {

        paymentRepository.deleteById(payment_id);
    }
}
