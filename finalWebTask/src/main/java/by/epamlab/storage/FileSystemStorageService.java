package by.epamlab.storage;

import by.epamlab.model.beans.CategoryType;
import by.epamlab.service.ProductRegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileSystemStorageService.class);

    @Value("${storage.upload-dir-location}")
    private Path rootLocation;

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file, int category) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()){
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                System.out.println("this.rootLocation.resolve(filename) = " + this.rootLocation.resolve(CategoryType.values()[category].name().toLowerCase())
                        .resolve(filename).toString());
                Path target = this.rootLocation
                        .resolve(CategoryType.values()[category].name().toLowerCase())
                        .resolve(filename);
                Files.copy(inputStream, target, StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadResource(String filename) {
        return null;
    }

    @Override
    public void deleteAll() {

    }
}
