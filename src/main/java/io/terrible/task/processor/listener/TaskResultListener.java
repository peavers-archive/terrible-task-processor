/* Licensed under Apache-2.0 */

package io.terrible.task.processor.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.terrible.task.processor.domain.DirectoryScanResult;
import io.terrible.task.processor.domain.ThumbnailCreateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(MessageBinding.class)
public class TaskResultListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WebClient webClient = WebClient.create("http://localhost:8081");

    @StreamListener(target = MessageBinding.THUMBNAIL_CHANNEL)
    public void processThumbnailMessage(final String message) {

        try {
            final ThumbnailCreateResult result = objectMapper.readValue(message, ThumbnailCreateResult.class);

            webClient.post().uri("/thumbnails").bodyValue(result).exchange().subscribe();

        } catch (final Exception e) {
            log.info("Unable save media file {}", e.getMessage());
        }
    }

    @StreamListener(target = MessageBinding.DIRECTORY_CHANNEL)
    public void processDirectoryMessage(final String message) {

        try {
            final DirectoryScanResult result = objectMapper.readValue(message, DirectoryScanResult.class);

            if (!result.getAbsolutePath().contains("sample")) {
                webClient.post().uri("/media-files").bodyValue(result).exchange().subscribe();
            }

        } catch (final Exception e) {
            log.info("Unable save media file {}", e.getMessage());
        }
    }

}
