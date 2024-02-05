package saga.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import saga.order.model.BikeOrderRequest;
import saga.order.service.BikeOrderService;

//@ComponentScan("saga.order.service")
public class BikeServiceCommand implements Command {


  BikeOrderService bikeOrderService;
  BikeOrderRequest bikeOrderRequest;

//  @Autowired
  public BikeServiceCommand(BikeOrderRequest bikeOrderRequest, BikeOrderService bikeOrderService) {
    this.bikeOrderRequest = bikeOrderRequest;
    this.bikeOrderService = bikeOrderService;
  }

  public void execute(){
    bikeOrderService.processBikeOrder(this.bikeOrderRequest);
  };
  public void rollBack(){
    //rename to undo bike order
    bikeOrderService.rollbackBikeOrder(this.bikeOrderRequest);

  };


}
