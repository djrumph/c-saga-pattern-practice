package saga.messaging;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import saga.order.model.BikeOrderRequest;
import saga.order.model.ProcessedOrders;

@Getter
@Setter
@Builder
public class ProcessedOrdersMessage extends GenericMessage {

  private AtomicLong orderId;
  private String type;
  private ProcessedOrders processedOrders;

  public ProcessedOrdersMessage(
      AtomicLong orderId,
      String type,
      ProcessedOrders processedOrders
  ) {
    this.orderId = new AtomicLong(System.currentTimeMillis());
    this.processedOrders = processedOrders;
    this.type = type;
    // Set other fields
  }

}
