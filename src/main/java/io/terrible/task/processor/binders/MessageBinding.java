/* Licensed under Apache-2.0 */
package io.terrible.task.processor.binders;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MessageBinding {

  String API_CHANNEL = "apiChannel";

  String DIRECTORY_CHANNEL = "directoryChannel";

  String THUMBNAIL_CHANNEL = "thumbnailChannel";

  String LAUNCHER_CHANNEL = "launcherChannel";

  @Output(API_CHANNEL)
  MessageChannel apiOutput();

  @Output(LAUNCHER_CHANNEL)
  MessageChannel launcherOutput();

  @Input(DIRECTORY_CHANNEL)
  SubscribableChannel directoryChannel();

  @Input(THUMBNAIL_CHANNEL)
  SubscribableChannel thumbnailChannel();
}
