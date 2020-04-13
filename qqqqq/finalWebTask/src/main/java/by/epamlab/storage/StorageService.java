package by.epamlab.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();
    void store(MultipartFile file, int category);
    Stream<Path> loadAll();
    Path load(String filename);
    Resource loadResource(String filename);
    void deleteAll();

}
