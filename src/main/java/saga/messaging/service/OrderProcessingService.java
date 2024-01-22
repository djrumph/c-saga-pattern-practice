package saga.messaging.service;

import java.time.LocalDateTime;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saga.messaging.GenericMessage;
import saga.messaging.OrderProcessingMessage;
import saga.messaging.ProcessedOrdersMessage;
import saga.order.model.OrderDetails;
import saga.order.model.ProcessedOrders;
import saga.order.repository.OrderDetailsRepository;
import saga.order.repository.ProcessedOrdersRepository;

@Service
public class OrderProcessingService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;

  @Autowired
  private ProcessedOrdersRepository processedOrdersRepository;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @RabbitListener(queues = "orderProcessingQueue")
  public void processOrderProcessing(GenericMessage message) {
    String messageType = message.getType();

    switch (messageType) {
      case "order":
        // Perform operation for TypeA messages
        processTypeA((OrderProcessingMessage) message);
        break;

      case "orderprocess":
        // Perform operation for TypeB messages
        processTypeB( ( ProcessedOrdersMessage) message);
        break;

      // Add more cases as needed

      default:
        // Handle unknown message types or log a warning
        handleUnknownMessageType(message);
    }
  }

  @Transactional
  private void processTypeA(OrderProcessingMessage orderProcessingMessage) {
    // Perform specific logic for TypeA messages
    System.out.println("order message received, adding to database");

    OrderDetails orderDetails
        = OrderDetails
        .builder()
        .customerName(orderProcessingMessage.getBikeOrderRequest().getCustomerName())
        .orderDate(LocalDateTime.now())
        .bikeModel(orderProcessingMessage.getBikeOrderRequest().getBikeModel())
        .quantity(orderProcessingMessage.getBikeOrderRequest().getQuantity())
        .build();
     long orderId = orderDetailsRepository.save(orderDetails);

    ProcessedOrders processedOrder = ProcessedOrders
        .builder()
        .processedOrderId(orderProcessingMessage.getOrderId().intValue())
        .processingDate(LocalDateTime.now())
        .orderId(orderId)
        .deliveryStatus("Delivered")
        .build();

    ProcessedOrdersMessage processedOrdersMessage
        = ProcessedOrdersMessage
        .builder()
        .type("orderprocess")
        .processedOrders(processedOrder)
        .build();

    // Send the message to the RabbitMQ queue
    rabbitTemplate.convertAndSend("orderProcessingQueue", processedOrdersMessage);
  }

  @Transactional
  private void processTypeB(ProcessedOrdersMessage processedOrdersMessage) {
    // Perform specific logic for TypeB messages
    processedOrdersRepository.save(processedOrdersMessage.getProcessedOrders());
  }


  private void handleUnknownMessageType(GenericMessage message) {
    // Handle unknown message types, e.g., log a warning
    System.out.println("Unknown message type: " + message.getType());
  }
}