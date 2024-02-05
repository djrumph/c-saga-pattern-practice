package saga.order.service;

import java.util.Stack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saga.command.BikeServiceCommand;
import saga.command.Command;
import saga.command.ProcessedOrderServiceCommand;
import saga.order.model.BikeOrderRequest;

@Service
public class OrderService {
  Stack<Command> operations = new Stack<>();

  @Autowired
  BikeOrderService bikeOrderService;

  @Autowired
  ProcessedOrderService processedOrderService;

  public void order(BikeOrderRequest bikeOrderRequest){
    try {
      //call each service and do its methods
      Command bikeServiceCommand = new BikeServiceCommand(bikeOrderRequest, bikeOrderService);
      bikeServiceCommand.execute();
      operations.push(bikeServiceCommand);

      Command processedOrderServiceCommand
          = new ProcessedOrderServiceCommand(bikeOrderRequest, processedOrderService);
      processedOrderServiceCommand.execute();
      operations.push(processedOrderServiceCommand);


      //make another service to write to a db and test rollbacks

//      throw new Exception("oh no");
    } catch(Exception exception){
      //undo everything
      while(!operations.isEmpty()){
        operations.pop().rollBack();
      }
    }
  }
}
