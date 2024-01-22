package saga.order.model;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeOrderRequest implements Serializable {

  private String customerName;
  private String bikeModel;
  private int quantity;

  // getters and setters
}
