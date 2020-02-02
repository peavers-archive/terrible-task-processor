package io.terrible.task.processor.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.terrible.task.processor.domain.MediaFile;
import io.terrible.task.processor.services.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(MessageBinding.class)
public class DirectoryListener {

    private final WorkerService workerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @StreamListener(target = MessageBinding.DIRECTORY_CHANNEL)
    public void processHelloChannelGreeting(String message) {
        log.info("received {}", message);

        try {
            MediaFile mediaFile = objectMapper.readValue(message, MediaFile.class);

            workerService.createThumbnail(mediaFile.getAbsolutePath());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
