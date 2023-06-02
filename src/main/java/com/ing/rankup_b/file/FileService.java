package com.ing.rankup_b.file;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    @Autowired
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File getFile(String fileId) {
        return this.fileRepository.findById(fileId).get();
    }

    public File uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileDB = new File(fileName, file.getContentType(), file.getBytes());
        return this.fileRepository.save(fileDB);
    }

    public Stream<File> getAllFiles() {
        return this.fileRepository.findAll().stream();
    }
}
