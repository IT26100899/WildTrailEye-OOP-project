package com.wildtrail.modules.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildtrail.exception.ApiException;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

// GUIDE CRUD: reads and writes resources.txt (JSON-formatted text).
@Repository
// FileGuideRepository: Repository for the WildTrail application.
public class FileGuideRepository {
    private final Path file;
    private final ObjectMapper mapper;

    public FileGuideRepository(ObjectMapper mapper, @Value("${wildtrail.data-dir}") String directory) {
        this.mapper = mapper.copy().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.file = Path.of(directory).toAbsolutePath().resolve("resources.txt");
    }

    // READ: return the saved guides.
    public synchronized List<Guide> findAll() {
        if (!Files.exists(file)) {
            return new ArrayList<>();
        }
        try {
            String json = Files.readString(file, StandardCharsets.UTF_8);
            List<Guide> saved = mapper.readValue(json, new TypeReference<List<Guide>>() {});
            List<Guide> guides = new ArrayList<>();
            for (Guide guide : saved) {
                guides.add(Guide.create(guide.getId(), guide.getFullName(), guide.getPhone(),
                        guide.getLicenseNumber(), guide.getSpecialty(), guide.isAvailable()));
            }
            return guides;
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to read guide records. Existing data was preserved.", ex);
        }
    }

    // CREATE when id == 0; UPDATE when id > 0.
    public synchronized Guide save(int id, Guide input) {
        List<Guide> guides = new ArrayList<>(findAll());
        boolean found = false;
        int nextId = 1;

        for (Guide guide : guides) {
            nextId = Math.max(nextId, guide.getId() + 1);
            if (guide.getId() == id) {
                found = true;
            }
            if (guide.getId() != id && guide.getLicenseNumber().equalsIgnoreCase(input.getLicenseNumber())) {
                throw ApiException.conflict("Guide licence is already registered");
            }
        }
        if (id > 0 && !found) {
            throw ApiException.notFound("Guide was not found");
        }
        int savedId = id > 0 ? id : nextId;
        Guide result = Guide.create(savedId, input.getFullName(), input.getPhone(),
                input.getLicenseNumber(), input.getSpecialty(), input.isAvailable());

        guides.removeIf(guide -> guide.getId() == id);
        guides.add(result);
        write(guides);
        return result;
    }

    // DELETE: remove the matching guide.
    public synchronized void delete(int id) {
        List<Guide> guides = new ArrayList<>(findAll());
        boolean removed = guides.removeIf(guide -> guide.getId() == id);
        if (!removed) {
            throw ApiException.notFound("Guide was not found");
        }
        write(guides);
    }

    // Write to a temporary file first so existing data is not half-written.
    private void write(List<Guide> guides) {
        Path temp = null;
        try {
            Files.createDirectories(file.getParent());
            temp = Files.createTempFile(file.getParent(), "resources-", ".tmp");
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(guides);
            Files.writeString(temp, json, StandardCharsets.UTF_8);
            try (FileChannel channel = FileChannel.open(temp, StandardOpenOption.WRITE)) {
                channel.force(true);
            }
            Files.move(temp, file, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            throw new IllegalStateException("Unable to save guide records", ex);
        } finally {
            if (temp != null) {
                try { Files.deleteIfExists(temp); } catch (IOException ignored) { }
            }
        }
    }
}
