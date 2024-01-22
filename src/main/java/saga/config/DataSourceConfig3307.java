package saga.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig3307 {

  @Bean(name = "dataSource3307")
  public HikariDataSource dataSource3307() {
    HikariConfig config = new HikariConfig();
//    config.setDataSourceClassName("org.mariadb.jdbc.Driver");
    config.setDriverClassName("org.mariadb.jdbc.Driver");
    config.setJdbcUrl("jdbc:mariadb://localhost:3307/database3307");
    config.setUsername("user3307");
    config.setPassword("password3307");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    HikariDataSource ds = new HikariDataSource(config);

    return ds;
  }
}
