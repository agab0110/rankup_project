package com.ing.rankup_b.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
        /*File observedFile = this.fileRepository.findById(fileId).get();
        File file = new File(observedFile.getName(), observedFile.getType(), decompressBytes(observedFile.getData()));
        return file;*/
        return this.fileRepository.findById(fileId).get();
    }

    public File uploadFile(MultipartFile file) throws IOException {
        /*String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File FileDB = new File(fileName, file.getContentType(), compressBytes(file.getBytes()));

        return fileRepository.save(FileDB);*/
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        File fileDB = new File(fileName, file.getContentType(), file.getBytes());   
        return this.fileRepository.save(fileDB);
      }

      public Stream<File> getAllFiles() {
        /*List<File> files = this.fileRepository.findAll();
        return files.stream().map(file -> {
            File observedFile = new File(file.getName(), file.getType(), decompressBytes(file.getData()));
            return observedFile;
        });*/
        List<File> files = this.fileRepository.findAll();
        return files.stream();
    }

    /*private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
    }

    private byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}*/
}
