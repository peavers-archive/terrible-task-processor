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

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {

  private final MessageService messageService;

  @Override
  public boolean createThumbnail(final String path) {
    final String url = "maven://io.terrible:terrible-thumbnail-creator:0.0.1-SNAPSHOT";

    final List<String> input = new ArrayList<>();
    input.add("--video");
    input.add(StringUtils.trimWhitespace(path));

    final TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null, null);

    return messageService.send(new GenericMessage<>(request));
  }

  @Override
  public boolean scanDirectory(final String path) {
    final String url = "maven://io.terrible:terrible-directory-scanner:0.0.1-SNAPSHOT";

    final List<String> input = new ArrayList<>();
    input.add("--directory");
    input.add(StringUtils.trimWhitespace(path));

    final TaskLaunchRequest request = new TaskLaunchRequest(url, input, null, null, null);

    return messageService.send(new GenericMessage<>(request));
  }
}
