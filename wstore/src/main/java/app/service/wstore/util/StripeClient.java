package app.service.wstore.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;

@Component
public class StripeClient {

    @Autowired
    StripeClient() {
        Stripe.apiKey = "sk_test_51LXILOJvtSsB9DmY807yqCiAQ3EzpKLd62eSU2G9AmbYiSTnGv8MN7Eb368nRPmD3sw2SEkJdYZZZ7mImkgn3I5T00Br7ykapJ";
    }

    // Create Customer in Stripe
    public Customer createCustomer(String email) throws StripeException {
        Map<String, Object> customer = new HashMap<>();

        customer.put("email", email);

        return Customer.create(customer);
    }

    // Get Customer take Id
    public Customer getCustomer(String id) throws StripeException {
        return Customer.retrieve(id);
    }

}
