/* Licensed under Apache-2.0 */
package io.terrible.task.processor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/** @author Chris Turner (chris@forloop.space) */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaFile {

  @Id private String id;

  private String absolutePath;

  private String mimeType;
}
