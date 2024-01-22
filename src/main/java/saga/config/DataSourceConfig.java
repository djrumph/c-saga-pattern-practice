//package config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfig {
//
//  @Bean(name = "dataSource3306")
//  public JdbcTemplate jdbcTemplate3306(@Qualifier("dataSource3306") DataSource dataSource) {
//    return new JdbcTemplate(dataSource);
//  }

//  @Bean(name = "dataSource3307")
//  public JdbcTemplate jdbcTemplate3307(@Qualifier("dataSource3307") DataSource dataSource) {
//    return new JdbcTemplate(dataSource);
//  }
//}

