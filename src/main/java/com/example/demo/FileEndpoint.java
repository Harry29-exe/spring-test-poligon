package com.example.demo;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RestController
public class FileEndpoint {
    private final Path rootPath = Path.of("/home/kamil/Wideo/spring-test");
    private final Random random = new Random();

//    @PostMapping(path= "/file", consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    public void uploadFile(@RequestBody InputStream file) throws IOException {
//        var path = rootPath.resolve("" +  random.nextInt());
////        Files.copy(new ByteArrayInputStream(file), path);
//        Files.copy(file, path);
//    }

    @PostMapping("/file")
    public void uploadFile(@RequestPart("file") MultipartFile file) throws IOException {
        var path = rootPath.resolve("" + random.nextInt());
        file.transferTo(path.toFile());
    }
}

