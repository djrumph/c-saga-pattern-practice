package saga.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saga.order.model.BikeOrderRequest;
import saga.order.service.BikeOrderService;

@RestController
@RequestMapping("/api/orders")
public class BikeOrderController {

  @Autowired
  private BikeOrderService bikeOrderService;

  @PostMapping("/process")
  public void processBikeOrder(@RequestBody BikeOrderRequest bikeOrderRequest) {
    bikeOrderService.processBikeOrder(bikeOrderRequest);
  }
}
