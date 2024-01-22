package saga.messaging;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import saga.order.model.BikeOrderRequest;

@Getter
@Setter
@Builder
public class OrderProcessingMessage extends GenericMessage {

  private AtomicLong orderId;
  private BikeOrderRequest bikeOrderRequest;
  private String type;

  public OrderProcessingMessage(
      AtomicLong orderId,
      BikeOrderRequest bikeOrderRequest,
      String type
  ) {
    this.orderId = new AtomicLong(System.currentTimeMillis());
    this.bikeOrderRequest = bikeOrderRequest;
    this.type = type;
    // Set other fields
  }
}