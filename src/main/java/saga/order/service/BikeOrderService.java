package saga.order.service;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import saga.messaging.OrderProcessingMessage;
import saga.order.model.BikeOrderRequest;
import saga.order.model.OrderDetails;
import saga.order.model.ProcessedOrders;
import saga.order.repository.OrderDetailsRepository;
import saga.order.repository.ProcessedOrdersRepository;

@Service
public class BikeOrderService {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  @Autowired
  private ProcessedOrdersRepository processedOrdersRepository;

  AtomicInteger orderId;

  @Transactional
  public void processBikeOrder(BikeOrderRequest bikeOrderRequest) {

    // Create a message for the next step (order processing)
    OrderProcessingMessage orderProcessingMessage
        = OrderProcessingMessage
          .builder()
          .type("order")
          .bikeOrderRequest(bikeOrderRequest)
          .build();

    // Send the message to the RabbitMQ queue
    rabbitTemplate.convertAndSend("orderProcessingQueue", orderProcessingMessage);



//    orderDetailsRepository.save(orderDetails);
//
//    // Process the order (in this example, just mark it as processed)
//    ProcessedOrders processedOrder = new ProcessedOrders();
//    processedOrder.setOrderId(orderDetails.getOrderId());
//    processedOrder.setProcessingDate(LocalDateTime.now());
//    processedOrder.setDeliveryStatus("Processed");
//
//    processedOrdersRepository.save(processedOrder);
  }
}