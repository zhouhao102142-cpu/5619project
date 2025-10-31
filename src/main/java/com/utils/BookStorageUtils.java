package com.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Utility methods for working with the local book storage under {@code storage/books}.
 */
public final class BookStorageUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookStorageUtils.class);
    private static final Path BOOKS_ROOT = Paths.get("storage", "books");

    private BookStorageUtils() {
    }

    public static Optional<BookMetadata> findMetadataByTitle(String title) {
        if (!Files.isDirectory(BOOKS_ROOT)) {
            LOGGER.warn("Books root directory does not exist: {}", BOOKS_ROOT.toAbsolutePath());
            return Optional.empty();
        }
        if (StringUtils.isBlank(title)) {
            return Optional.empty();
        }
        String trimmedTitle = title.trim();
        try (DirectoryStream<Path> directories = Files.newDirectoryStream(BOOKS_ROOT)) {
            for (Path directory : directories) {
                if (!Files.isDirectory(directory)) {
                    continue;
                }
                Path metadataPath = directory.resolve("metadata.json");
                if (!Files.isRegularFile(metadataPath)) {
                    continue;
                }
                try {
                    String json = new String(Files.readAllBytes(metadataPath), StandardCharsets.UTF_8);
                    json = removeBom(json);
                    JSONObject object = JSONObject.parseObject(json);
                    String metadataTitle = object.getString("title");
                    if (metadataTitle != null && metadataTitle.trim().equalsIgnoreCase(trimmedTitle)) {
                        String storagePath = object.getString("storagePath");
                        String coverPath = object.getString("coverPath");
                        String description = object.getString("description");
                        String authorName = object.getString("authorName");
                        return Optional.of(new BookMetadata(
                            metadataTitle,
                            authorName,
                            description,
                            resolvePath(storagePath),
                            resolvePath(coverPath)
                        ));
                    }
                } catch (IOException e) {
                    LOGGER.error("Failed to read metadata file: {}", metadataPath.toAbsolutePath(), e);
                } catch (UncheckedIOException e) {
                    LOGGER.error("Failed to process metadata file: {}", metadataPath.toAbsolutePath(), e);
                } catch (Exception e) {
                    LOGGER.error("Invalid metadata file: {}", metadataPath.toAbsolutePath(), e);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Unable to scan books directory", e);
        }
        return Optional.empty();
    }

    private static Path resolvePath(String relativePath) {
        if (StringUtils.isBlank(relativePath)) {
            return null;
        }
        Path resolved = BOOKS_ROOT.resolve(relativePath).normalize();
        if (!resolved.startsWith(BOOKS_ROOT)) {
            LOGGER.warn("Attempted to access path outside of books root: {}", relativePath);
            return null;
        }
        return resolved;
    }

    private static String removeBom(String value) {
        if (value != null && value.startsWith("\ufeff")) {
            return value.substring(1);
        }
        return value;
    }

    public static final class BookMetadata {
        private final String title;
        private final String authorName;
        private final String description;
        private final Path contentPath;
        private final Path coverPath;

        private BookMetadata(String title, String authorName, String description, Path contentPath, Path coverPath) {
            this.title = title;
            this.authorName = authorName;
            this.description = description;
            this.contentPath = contentPath;
            this.coverPath = coverPath;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthorName() {
            return authorName;
        }

        public String getDescription() {
            return description;
        }

        public Path getContentPath() {
            return contentPath;
        }

        public Path getCoverPath() {
            return coverPath;
        }

        public boolean hasReadableContent() {
            return contentPath != null && Files.isRegularFile(contentPath);
        }

        public String readContent() throws IOException {
            if (!hasReadableContent()) {
                throw new IOException("Content file is missing for book: " + title);
            }
            byte[] bytes = Files.readAllBytes(contentPath);
            String text = new String(bytes, StandardCharsets.UTF_8);
            return removeBom(text);
        }

        public String getSuggestedFileName(String fallback) {
            String baseName = StringUtils.isNotBlank(title) ? title : fallback;
            if (StringUtils.isBlank(baseName)) {
                baseName = "book";
            }
            String sanitized = baseName.replaceAll("[\\\\/:*?\"<>|]", "_");
            if (!sanitized.toLowerCase().endsWith(".txt")) {
                sanitized = sanitized + ".txt";
            }
            return sanitized;
        }
    }
}
