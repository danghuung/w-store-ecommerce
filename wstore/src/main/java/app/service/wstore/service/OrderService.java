package app.service.wstore.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.service.wstore.dto.OrderDetailDto;
import app.service.wstore.dto.OrderDto;
import app.service.wstore.entity.CustomUserDetails;
import app.service.wstore.entity.DiscountCode;
import app.service.wstore.entity.Order;
import app.service.wstore.entity.OrderDetail;
import app.service.wstore.entity.Product;
import app.service.wstore.entity.User;
import app.service.wstore.exception.NotFoundException;
import app.service.wstore.exception.UnauthorizedException;
import app.service.wstore.repository.DiscountCodeRepository;
import app.service.wstore.repository.OrderDetailRepository;
import app.service.wstore.repository.OrderRepositoty;
import app.service.wstore.repository.ProductRepository;
import app.service.wstore.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepositoty orderRepositoty;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DiscountCodeRepository discountCodeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Get All Order
    public List<OrderDto> getListOrder(CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);

        List<Order> orders = orderRepositoty.findByCreatedBy(user.getId());

        ArrayList<OrderDto> result = new ArrayList<>();

        for (Order order : orders) {

            Set<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
            Set<OrderDetailDto> orderDetailDtos = new HashSet<>();

            for (OrderDetail orderDetail : orderDetails) {
                orderDetailDtos.add(modelMapper.map(orderDetail, OrderDetailDto.class));
            }

            OrderDto resultOrderDto = modelMapper.map(order, OrderDto.class);
            resultOrderDto.setOrderDetails(orderDetailDtos);

            result.add(resultOrderDto);
        }

        return result;
    }

    public OrderDto getDettailOrder(int id, CustomUserDetails currentUser) {
        User user = userRepository.getUser(currentUser);

        Order order = orderRepositoty.findById(id)
                .orElseThrow(() -> new NotFoundException("Order not exist!"));

        User createdByOrder = userRepository.findById(order.getCreatedBy());

        if (createdByOrder.equals(user)) {
            Set<OrderDetail> orderDetails = orderDetailRepository.findByOrder(order);
            Set<OrderDetailDto> orderDetailDtos = new HashSet<>();

            for (OrderDetail orderDetail : orderDetails) {
                orderDetailDtos.add(modelMapper.map(orderDetail, OrderDetailDto.class));
            }
            OrderDto resultOrderDto = modelMapper.map(order, OrderDto.class);
            resultOrderDto.setOrderDetails(orderDetailDtos);

            return resultOrderDto;
        }

        throw new UnauthorizedException("You Dont Have Permission!");
    }

    public OrderDto create(OrderDto orderDto) {
        Order order = new Order();

        order.setFullName(orderDto.getFullName());
        order.setAddress(orderDto.getAddress());
        order.setPhone(orderDto.getPhone());
        order.setOrderStatus("proccessing");
        order.setPaymentStatus("incomplete");
        order.setDiscountCode(orderDto.getDiscountCode());
        orderRepositoty.save(order);

        Set<OrderDetailDto> setOrderDetailDto = orderDto.getOrderDetails();

        Set<OrderDetailDto> setResultOrderDetailDto = new HashSet<OrderDetailDto>();

        var totalTemp = 0;

        for (OrderDetailDto orderDetailDto : setOrderDetailDto) {

            Product product = productRepository.findById(orderDetailDto.getProductId())
                    .orElseThrow(() -> new NotFoundException("Product id not found"));

            if (product.getSale() > 0) {
                totalTemp += product.getSale() * orderDetailDto.getQuantity();
            } else {
                totalTemp += product.getPrice() * orderDetailDto.getQuantity();
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(orderDetailDto.getQuantity());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setSale(product.getSale());
            orderDetail.setOrder(order);

            setResultOrderDetailDto.add(modelMapper.map(orderDetailRepository.save(orderDetail), OrderDetailDto.class));

        }

        if (order.getDiscountCode() != "") {
            DiscountCode discountCode = discountCodeRepository.findByCode(order.getDiscountCode());

            totalTemp -= discountCode.getAmountOff();
        }
        order.setTotal(totalTemp);

        OrderDto resultOrder = modelMapper.map(orderRepositoty.save(order), OrderDto.class);
        resultOrder.setOrderDetails(setResultOrderDetailDto);

        return resultOrder;
    }
}
