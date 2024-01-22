package saga.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig3306 {

  @Bean(name = "dataSource3306")
//  @ConfigurationProperties(prefix = "spring.datasource.db1")
//  public DataSource dataSource3306() {
//    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
//    dataSourceBuilder.driverClassName("org.mariadb.jdbc.Driver");
//    dataSourceBuilder.url("jdbc:mariadb://localhost:3306/database3306");
//    dataSourceBuilder.username("user3306");
//    dataSourceBuilder.password("password3306");
//    spring.datasource.db1.driver-class-name=org.mariadb.jdbc.Driver
//    spring.datasource.db1.url=jdbc:mariadb://localhost:3306/database3306
//    spring.datasource.db1.username=user3306
//    spring.datasource.db1.password=password3306
//    return dataSourceBuilder.build();
//  }

  public HikariDataSource  dataSource3306() {
    HikariConfig config = new HikariConfig();
//    config.setDataSourceClassName("org.mariadb.jdbc.Driver");
    config.setDriverClassName("org.mariadb.jdbc.Driver");
    config.setJdbcUrl("jdbc:mariadb://localhost:3306/database3306");
    config.setUsername("user3306");
    config.setPassword("password3306");
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

    HikariDataSource ds = new HikariDataSource(config);

    return ds;
  }


}