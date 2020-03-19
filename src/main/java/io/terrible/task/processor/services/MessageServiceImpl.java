/* Licensed under Apache-2.0 */
package io.terrible.task.processor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@RequiredArgsConstructor
@EnableBinding(Source.class)
public class MessageServiceImpl implements MessageService {

  private final Source source;

  @Override
  public boolean send(final GenericMessage<TaskLaunchRequest> message) {

    return this.source.output().send(message);
  }
}
