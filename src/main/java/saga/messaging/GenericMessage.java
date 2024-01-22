package saga.messaging;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericMessage implements Serializable {

  private String type;
  // other fields...

  // getters and setters...
}