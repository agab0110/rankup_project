package com.ing.rankup_b.file;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/fileApi")
@CrossOrigin
public class FileController {
    
    @Autowired
    private FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
      try {
        File uploadFile = service.uploadFile(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadFile.getId());
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
      }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = service.getAllFiles().map(file -> {
          String fileDownloadUri = ServletUriComponentsBuilder
              .fromCurrentContextPath()
              .path("/fileApi/files/")
              .path(file.getId())
              .toUriString();
    
          return new ResponseFile(
            file.getName(),
            fileDownloadUri,
            file.getType(),
            file.getData().length);
        }).collect(Collectors.toList());
    
        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        File file = service.getFile(id);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
            .body(file.getData());
    }
}
