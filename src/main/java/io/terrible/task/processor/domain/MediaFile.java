/* Licensed under Apache-2.0 */

package io.terrible.task.processor.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaFile {

    private String id;

    private String name;

    private String path;

    private String extension;

    private long size;

    @Builder.Default
    private ArrayList<String> thumbnails = new ArrayList<>();

    private LocalDateTime createdTime;

    private LocalDateTime lastAccessTime;

    private LocalDateTime lastModifiedTime;

}
