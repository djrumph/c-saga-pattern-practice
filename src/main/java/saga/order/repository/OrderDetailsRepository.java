package saga.order.repository;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.sql.DataSource;
import org.mariadb.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import saga.order.model.OrderDetails;
import org.springframework.jdbc.support.KeyHolder;

@Repository
public class OrderDetailsRepository {

//  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public OrderDetailsRepository(@Qualifier("dataSource3306") HikariDataSource dataSource3306) {
    this.jdbcTemplate = new JdbcTemplate(dataSource3306);
  }

  public long save(OrderDetails orderDetails) {
    String insertOrderDetailsSql =
        """
          INSERT INTO order_details (
            customer_name, 
            bike_model, 
            quantity, 
            order_date
           ) 
          VALUES (?, ?, ?, ?)
        """;

    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

        PreparedStatement ps = connection.prepareStatement(insertOrderDetailsSql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, orderDetails.getCustomerName());
        ps.setString(2, orderDetails.getBikeModel());
        ps.setInt(3, orderDetails.getQuantity());
        ps.setTimestamp(4, Timestamp.valueOf(orderDetails.getOrderDate()));
        return ps;
      }
    }, keyHolder);

    // Retrieve the generated order_id
    Number orderId = keyHolder.getKey();
    if (orderId != null) {
      return orderId.longValue();
    } else {
      throw new RuntimeException("Failed to retrieve order_id after insertion");
    }
  }
}
