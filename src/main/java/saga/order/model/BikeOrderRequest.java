package saga.order.model;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeOrderRequest implements Serializable {

  private int id = new Random().nextInt();
  private String customerName;
  private String bikeModel;
  private int quantity;

  // getters and setters
}
