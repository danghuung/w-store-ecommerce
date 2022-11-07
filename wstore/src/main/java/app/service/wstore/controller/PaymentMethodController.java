package app.service.wstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stripe.exception.StripeException;
import com.stripe.model.SetupIntent;

import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.jwt.CurrentUser;
import app.service.wstore.payload.CardResponse;
import app.service.wstore.payload.StripeResponse;
import app.service.wstore.service.PaymentMethodService;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/setup-intent")
    public StripeResponse createSetupIntent(@CurrentUser CustomUserDetails currentUser) throws StripeException {

        SetupIntent setupIntent = paymentMethodService.createSetupIntent(currentUser);

        return new StripeResponse(setupIntent.getId(), setupIntent.getClientSecret());
    }

    @GetMapping("")
    public List<CardResponse> getListPaymentMethod(@CurrentUser CustomUserDetails currentUser) throws StripeException {
        return paymentMethodService.getListCard(currentUser);
    }

    @DeleteMapping("/{pm_id}")
    public ResponseEntity<?> deleteCard(@CurrentUser CustomUserDetails currentUser, @PathVariable String pm_id)
            throws StripeException {
        paymentMethodService.deletCard(currentUser, pm_id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
