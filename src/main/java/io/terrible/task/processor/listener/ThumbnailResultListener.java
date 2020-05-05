/* Licensed under Apache-2.0 */
package io.terrible.task.processor.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.terrible.task.processor.binders.MessageBinding;
import io.terrible.task.processor.domain.ThumbnailCreateResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.GenericMessage;

@Slf4j
@RequiredArgsConstructor
@EnableBinding(MessageBinding.class)
public class ThumbnailResultListener {

  private final ObjectMapper objectMapper;

  @SendTo(MessageBinding.API_CHANNEL_THUMBNAIL)
  @StreamListener(target = MessageBinding.THUMBNAIL_CHANNEL)
  public GenericMessage<ThumbnailCreateResult> processThumbnailMessage(final String message) {

    try {
      final ThumbnailCreateResult thumbnailCreateResult = objectMapper.readValue(message, ThumbnailCreateResult.class);

      log.info("Created {}", thumbnailCreateResult);

      return new GenericMessage<>(thumbnailCreateResult);

    } catch (final Exception e) {
      log.info("Unable to process task {} {}", e.getMessage(), e.getStackTrace());
    }

    return null;
  }
}
