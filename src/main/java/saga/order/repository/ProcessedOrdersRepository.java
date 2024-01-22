package saga.order.repository;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import saga.order.model.ProcessedOrders;

@Repository
public class ProcessedOrdersRepository {

//  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public ProcessedOrdersRepository(@Qualifier("dataSource3307") DataSource dataSource3307) {
    this.jdbcTemplate = new JdbcTemplate(dataSource3307);
  }

  public void save(ProcessedOrders processedOrders) {
    String insertProcessedOrderSql =
        """
          INSERT INTO processed_orders(
            order_id, 
            processing_date, 
            delivery_status
           ) 
          VALUES (?, ?, ?)
        """;
    jdbcTemplate.update(insertProcessedOrderSql,
        processedOrders.getOrderId(),
        processedOrders.getProcessingDate(),
        processedOrders.getDeliveryStatus());
  }
}
