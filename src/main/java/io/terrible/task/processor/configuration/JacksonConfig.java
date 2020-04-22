/* Licensed under Apache-2.0 */
package io.terrible.task.processor.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

  @Bean
  public ObjectMapper objectMapper() {

    final ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.findAndRegisterModules();
  }
}
