package saga.order.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDetails {

  private int orderId;
  private String customerName;
  private String bikeModel;
  private int quantity;
  private LocalDateTime orderDate;

  // getters and setters
}
