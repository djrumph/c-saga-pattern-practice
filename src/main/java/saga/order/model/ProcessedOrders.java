package saga.order.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProcessedOrders implements Serializable {

  private int processedOrderId;
  private long orderId;
  private LocalDateTime processingDate;
  private String deliveryStatus;

  // getters and setters
}
