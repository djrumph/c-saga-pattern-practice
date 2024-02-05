package saga.command;

import saga.order.model.BikeOrderRequest;
import saga.order.service.BikeOrderService;
import saga.order.service.ProcessedOrderService;

//@ComponentScan("saga.order.service")
public class ProcessedOrderServiceCommand implements Command {


  ProcessedOrderService processedOrderService;
  BikeOrderRequest bikeOrderRequest;

//  @Autowired
  public ProcessedOrderServiceCommand(BikeOrderRequest bikeOrderRequest,  ProcessedOrderService processedOrderService) {
    this.bikeOrderRequest = bikeOrderRequest;
    this.processedOrderService = processedOrderService;
  }

  public void execute(){
    processedOrderService.processOrder(this.bikeOrderRequest);
  };
  public void rollBack(){
    //rename to undo bike order
    processedOrderService.rollbackOrder(this.bikeOrderRequest);

  };


}
