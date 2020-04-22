/* Licensed under Apache-2.0 */
package io.terrible.task.processor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThumbnailCreateResult {

  private String videoPath;

  private ArrayList<String> thumbnails;
}
