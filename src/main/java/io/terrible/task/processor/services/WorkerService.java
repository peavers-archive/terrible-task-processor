/* Licensed under Apache-2.0 */
package io.terrible.task.processor.services;


public interface WorkerService {

  boolean createThumbnail(String path);

  boolean scanDirectory(String path);
}
