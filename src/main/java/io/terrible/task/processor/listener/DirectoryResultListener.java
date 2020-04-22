/* Licensed under Apache-2.0 */
package io.terrible.task.processor.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.terrible.task.processor.binders.MessageBinding;
import io.terrible.task.processor.converters.MediaFileConverter;
import io.terrible.task.processor.domain.MediaFile;
import java.io.File;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(MessageBinding.class)
public class DirectoryResultListener {

  private final ObjectMapper objectMapper;

  @SendTo(MessageBinding.API_CHANNEL)
  @StreamListener(target = MessageBinding.DIRECTORY_CHANNEL)
  public GenericMessage<MediaFile> processDirectoryMessage(final String message) {

    try {
      final File result = objectMapper.readValue(message, File.class);

      log.info("Result {}", result);

      if (!result.getAbsolutePath().contains("sample")) {
        return new GenericMessage<>(MediaFileConverter.convert(result));
      }

    } catch (final Exception e) {
      log.info("Unable to process task {} {}", e.getMessage(), e.getStackTrace());
    }

    return null;
  }
}
