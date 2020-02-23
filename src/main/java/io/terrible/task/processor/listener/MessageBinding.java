/* Licensed under Apache-2.0 */
package io.terrible.task.processor.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MessageBinding {

    String DIRECTORY_CHANNEL = "directoryChannel";

    String THUMBNAIL_CHANNEL = "thumbnailChannel";

    @Input(DIRECTORY_CHANNEL)
    SubscribableChannel directoryChannel();

    @Input(THUMBNAIL_CHANNEL)
    SubscribableChannel thumbnailChannel();
}
