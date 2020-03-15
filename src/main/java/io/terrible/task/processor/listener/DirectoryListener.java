/* Licensed under Apache-2.0 */
package io.terrible.task.processor.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.terrible.task.processor.domain.MediaFile;
import io.terrible.task.processor.services.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.file.Path;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(MessageBinding.class)
public class DirectoryListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final WorkerService workerService;

    // TODO Eureka is coming...
    private final WebClient webClient = WebClient.create("http://localhost:8081");

    @StreamListener(target = MessageBinding.THUMBNAIL_CHANNEL)
    public void processThumbnailMessage(final String message) {

        log.info("Message {}", message);

        try {
            final List<Path> thumbnails = objectMapper
                    .readValue(message, objectMapper
                            .getTypeFactory()
                            .constructCollectionType(List.class, Path.class));

            thumbnails.forEach(path -> log.info("Thumbnail {}", path.toAbsolutePath()));

        } catch (final Exception e) {
            log.info("Unable save media file {}", e.getMessage());
        }
    }

    @StreamListener(target = MessageBinding.DIRECTORY_CHANNEL)
    public void processDirectoryMessage(final String message) {

        try {
            final MediaFile mediaFile = objectMapper.readValue(message, MediaFile.class);

            webClient.post().uri("/media-files").bodyValue(mediaFile).exchange().subscribe();

        } catch (final Exception e) {
            log.info("Unable save media file {}", e.getMessage());
        }
    }
}
