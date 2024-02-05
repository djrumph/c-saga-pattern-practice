package saga.order.service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import saga.order.model.BikeOrderRequest;
import saga.order.model.OrderDetails;
import saga.order.model.ProcessedOrders;
import saga.order.repository.OrderDetailsRepository;
import saga.order.repository.ProcessedOrdersRepository;

@Component
public class ProcessedOrderService {

  @Autowired
  private OrderDetailsRepository orderDetailsRepository;
  @Autowired
  private ProcessedOrdersRepository processedOrdersRepository;

  AtomicInteger orderId;

  @Transactional
  public void processOrder(BikeOrderRequest bikeOrderRequest) {
//    OrderDetails orderDetails = OrderDetails
//        .builder()
//        .orderDate(LocalDateTime.now())
//        .bikeModel(bikeOrderRequest.getBikeModel())
//        .quantity(bikeOrderRequest.getQuantity())
//        .customerName(bikeOrderRequest.getCustomerName())
//        .orderId(bikeOrderRequest.getId())
//        .build();

    ProcessedOrders processedOrder = ProcessedOrders
        .builder()
        .orderId(bikeOrderRequest.getId())
        .processingDate(LocalDateTime.now())
        .deliveryStatus("Completed")
        .build();

    processedOrdersRepository.save(processedOrder);

    //write to another table to test ou transactions
  }

  @Transactional
  public void rollbackOrder(BikeOrderRequest bikeOrderRequest) {
    ProcessedOrders processedOrder = ProcessedOrders
        .builder()
        .orderId(bikeOrderRequest.getId())
        .processingDate(LocalDateTime.now())
        .deliveryStatus("Completed")
        .build();


    processedOrdersRepository.delete(processedOrder);

    //delete from another table to test transactions
  }
}