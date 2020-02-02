/* Licensed under Apache-2.0 */
package io.terrible.task.processor.controller;

import io.terrible.task.processor.services.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** @author Chris Turner (chris@forloop.space) */
@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {

  private final WorkerService workerService;

  @GetMapping("/thumbnails")
  public ResponseEntity<String> thumbnails(@RequestParam final String video) {

    final boolean messageSent = workerService.createThumbnail(video);

    return messageSent
        ? ResponseEntity.accepted().body("Message sent")
        : ResponseEntity.badRequest().body("Message failed");
  }

  @GetMapping("/directories")
  public ResponseEntity<String> directories(@RequestParam final String path) {

    final boolean messageSent = workerService.scanDirectory(path);

    return messageSent
        ? ResponseEntity.accepted().body("Message sent")
        : ResponseEntity.badRequest().body("Message failed");
  }
}
