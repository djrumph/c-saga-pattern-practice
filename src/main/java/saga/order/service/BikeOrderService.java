package saga.order.service;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import saga.order.model.BikeOrderRequest;
import saga.order.model.OrderDetails;
import saga.order.model.ProcessedOrders;
import saga.order.repository.OrderDetailsRepository;
import saga.order.repository.ProcessedOrdersRepository;

@Component
public class BikeOrderService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;
  @Autowired
  private ProcessedOrdersRepository processedOrdersRepository;

  AtomicInteger orderId;

  @Transactional
  public void processBikeOrder(BikeOrderRequest bikeOrderRequest) {
    OrderDetails orderDetails = OrderDetails
        .builder()
        .orderDate(LocalDateTime.now())
        .bikeModel(bikeOrderRequest.getBikeModel())
        .quantity(bikeOrderRequest.getQuantity())
        .customerName(bikeOrderRequest.getCustomerName())
        .orderId(bikeOrderRequest.getId())
        .build();

    orderDetailsRepository.save(orderDetails);

    //write to another table to test ou transactions
  }

  @Transactional
  public void rollbackBikeOrder(BikeOrderRequest bikeOrderRequest) {
    OrderDetails orderDetails = OrderDetails
        .builder()
        .orderDate(LocalDateTime.now())
        .bikeModel(bikeOrderRequest.getBikeModel())
        .quantity(bikeOrderRequest.getQuantity())
        .customerName(bikeOrderRequest.getCustomerName())
        .orderId(bikeOrderRequest.getId())
        .build();

    orderDetailsRepository.delete(orderDetails);

    //delete from another table to test transactions
  }
}