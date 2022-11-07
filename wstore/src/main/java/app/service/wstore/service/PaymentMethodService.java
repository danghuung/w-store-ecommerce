package app.service.wstore.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethod;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.model.SetupIntent;
import com.stripe.param.CustomerListPaymentMethodsParams;

import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.entity.User;
import app.service.wstore.payload.CardResponse;
import app.service.wstore.repository.UserRepository;

@Service
public class PaymentMethodService {

    @Autowired
    private UserRepository userRepository;

    // Create SetupIntent send cline secret to FE setup card
    public SetupIntent createSetupIntent(CustomUserDetails currentUser) throws StripeException {
        User user = userRepository.getUser(currentUser);
        Customer customer = Customer.retrieve(user.getStripeAccount());
        List<Object> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", paymentMethodTypes);
        params.put("customer", customer.getId());

        return SetupIntent.create(params);
    }

    // Get List Card in Stripe of Customer
    public List<CardResponse> getListCard(CustomUserDetails currentUser) throws StripeException {
        User user = userRepository.getUser(currentUser);
        PaymentMethodCollection paymentMethodCollection = getCard(user.getStripeAccount());

        List<PaymentMethod> listCardData = paymentMethodCollection.getData();
        ArrayList<CardResponse> cardResponses = new ArrayList<>();

        for (PaymentMethod card : listCardData) {
            CardResponse cardResponse = new CardResponse(
                    card.getId(),
                    card.getCard().getBrand(),
                    card.getCard().getExpMonth(),
                    card.getCard().getExpYear(),
                    card.getCard().getLast4());

            cardResponses.add(cardResponse);
        }

        return cardResponses;
    }

    public PaymentMethodCollection getCard(String customerId) throws StripeException {
        Customer customer = Customer.retrieve(customerId);

        CustomerListPaymentMethodsParams params = CustomerListPaymentMethodsParams.builder()
                .setType(CustomerListPaymentMethodsParams.Type.CARD)
                .build();

        return customer.listPaymentMethods(params);
    }

    public void deletCard(CustomUserDetails currentUser, String cardId) throws StripeException {
        User user = userRepository.getUser(currentUser);

        Map<String, Object> retrieveParams = new HashMap<>();
        List<String> expandList = new ArrayList<>();

        expandList.add("sources");
        retrieveParams.put("expand", expandList);

        Customer customer = Customer.retrieve(
                user.getStripeAccount(),
                retrieveParams,
                null);

        Card card = (Card) customer.getSources().retrieve(cardId);
        card.delete();
    }
}
