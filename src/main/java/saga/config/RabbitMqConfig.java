package saga.config;

import java.util.List;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

  @Bean
  public Queue orderProcessingQueue() {
    return new Queue("orderProcessingQueue", true);
  }

  @Bean
  public SimpleMessageConverter converter() {
    SimpleMessageConverter converter = new SimpleMessageConverter();
    converter.setAllowedListPatterns(List.of("saga*", "java.time.Ser"));
    return converter;
  }


}