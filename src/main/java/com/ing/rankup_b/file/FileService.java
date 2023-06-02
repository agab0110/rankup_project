package com.ing.rankup_b.file;

import java.io.IOException;
import java.util.List;
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

    public File getFile(Long fileId) {
        return this.fileRepository.findById(fileId).get();
    }

    public File saveFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileDB = new File(fileName, file.getContentType(), file.getBytes());
        return this.fileRepository.save(fileDB);
    }

    public List<File> getAllFiles() {
        List<File> files = this.fileRepository.findAll();
        return files;
    }
}
