/* Licensed under Apache-2.0 */
package io.terrible.task.processor.services;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.task.launcher.TaskLaunchRequest;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

  private final MessageService messageService;

  @Override
  public boolean createThumbnail(final String video) {
    final String url = "maven://io.terrible:io.terrible.thumbnail.creator:0.0.1-SNAPSHOT";

    final List<String> input = new ArrayList<>();
    input.add("--video");
    input.add(StringUtils.trimWhitespace(video));

    final TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null, null);

    return messageService.send(new GenericMessage<>(request));
  }
}
