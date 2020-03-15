/* Licensed under Apache-2.0 */
package io.terrible.task.processor.controller;

import io.terrible.task.processor.services.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * These are quick endpoints for development, they will be replaced with something else at some
 * point...
 *
 * @author Chris Turner (chris@forloop.space)
 */
@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class TaskController {

    private final WorkerService workerService;

    /**
     * Generate the thumbnails for a given video file.
     *
     * @param path where the video file is located
     */
    @GetMapping("/task/thumbnails")
    public Mono<Boolean> thumbnails(@RequestParam final String path) {

        final boolean messageSent = workerService.createThumbnail(path);

        return Mono.justOrEmpty(messageSent);
    }

    /**
     * Scan a directory looking for videos
     *
     * @param path for where a recursive scan should start
     */
    @GetMapping("/task/directories")
    public Mono<Boolean> directories(@RequestParam final String path) {

        final boolean messageSent = workerService.scanDirectory(path);

        return Mono.justOrEmpty(messageSent);
    }
}
