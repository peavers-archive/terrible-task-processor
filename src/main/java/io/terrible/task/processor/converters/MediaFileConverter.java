/* Licensed under Apache-2.0 */
package io.terrible.task.processor.converters;

import io.terrible.task.processor.domain.MediaFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FilenameUtils;

/** Converts a file to a type of MediaFile */
@Slf4j
@UtilityClass
public class MediaFileConverter {

  public MediaFile convert(final File file) {

    return MediaFile.builder()
        .id(getId(file))
        .name(getName(file))
        .extension(getExtension(file))
        .path(file.getAbsolutePath())
        .size(getSize(file))
        .createdTime(getCreationTime(file))
        .lastAccessTime(getLastAccessTime(file))
        .lastModifiedTime(getModificationTime(file))
        .build();
  }

  private LocalDateTime getCreationTime(final File file) {

    LocalDateTime time;
    try {
      final Path path = Paths.get(file.getAbsolutePath());
      final BasicFileAttributes fileAttributes =
          Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
      time =
          LocalDateTime.ofInstant(
              fileAttributes.creationTime().toInstant(), ZoneId.systemDefault());
    } catch (final Exception e) {
      time = null;
    }
    return time;
  }

  private LocalDateTime getModificationTime(final File file) {

    LocalDateTime time;
    try {
      final Path path = Paths.get(file.getAbsolutePath());
      final BasicFileAttributes fileAttributes =
          Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
      time =
          LocalDateTime.ofInstant(
              fileAttributes.lastModifiedTime().toInstant(), ZoneId.systemDefault());
    } catch (final Exception e) {
      time = null;
    }
    return time;
  }

  private LocalDateTime getLastAccessTime(final File file) {

    LocalDateTime time;
    try {
      final Path path = Paths.get(file.getAbsolutePath());
      final BasicFileAttributes fileAttributes =
          Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
      time =
          LocalDateTime.ofInstant(
              fileAttributes.lastAccessTime().toInstant(), ZoneId.systemDefault());
    } catch (final Exception e) {
      time = null;
    }
    return time;
  }

  private long getSize(final File file) {

    long size;
    try {
      final Path path = Paths.get(file.getAbsolutePath());
      final BasicFileAttributes fileAttributes =
          Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes();
      size = fileAttributes.size();
    } catch (final Exception e) {
      size = -1L;
    }
    return size;
  }

  private String getName(final File file) {

    return FilenameUtils.removeExtension(file.getName());
  }

  private String getExtension(final File file) {

    return FilenameUtils.getExtension(file.getName());
  }

  private String getId(final File file) {

    try {
      return DigestUtils.sha1Hex(file.getCanonicalPath());
    } catch (final IOException e) {
      return null;
    }
  }
}
