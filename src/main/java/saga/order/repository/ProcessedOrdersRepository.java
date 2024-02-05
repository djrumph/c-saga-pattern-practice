package saga.order.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.mariadb.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;
import saga.order.model.ProcessedOrders;

@Repository
public class ProcessedOrdersRepository {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public ProcessedOrdersRepository(@Qualifier("dataSource3307") DataSource dataSource3307) {
    this.jdbcTemplate = new JdbcTemplate(dataSource3307);
  }

  public void delete(ProcessedOrders processedOrder) {
    String deleteOrderDetailsSql =
        """
          DELETE FROM processed_orders 
          WHERE order_id = ?
        """;

    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(deleteOrderDetailsSql, Statement.NO_GENERATED_KEYS);
        ps.setLong(1, processedOrder.getOrderId());
        return ps;
      }
    });
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
