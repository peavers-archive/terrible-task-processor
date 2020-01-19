/* Licensed under Apache-2.0 */
package io.terrible.task.processor.services;

import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;

/** @author Chris Turner (chris@forloop.space) */
public interface MessageService {

  boolean send(GenericMessage<TaskLaunchRequest> message);
}
