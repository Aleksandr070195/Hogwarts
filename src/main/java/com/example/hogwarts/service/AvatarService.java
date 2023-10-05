package com.example.hogwarts.service;

import com.example.hogwarts.model.Avatar;
import com.example.hogwarts.repository.AvatarRepository;
import com.example.hogwarts.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;
    @Value("${path.to.avatars.folder}")
    private Path avatarPath;

    public AvatarService(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public Long save(Long studentId, MultipartFile multipartFile, byte[] data) throws IOException {

        Files.createDirectories(avatarPath);
        int dotIndex = multipartFile.getOriginalFilename().lastIndexOf(".");
        String fileExtension = multipartFile.getOriginalFilename().substring(dotIndex + 1);
        Path path = avatarPath.resolve(studentId + "." + fileExtension);
        Files.write(path, data, StandardOpenOption.CREATE);

        Avatar avatar = new Avatar();
        avatar.setStudent(studentRepository.getReferenceById(studentId));
        avatar.setMediaType(multipartFile.getContentType());
        avatar.setFileSize(multipartFile.getSize());
        avatar.setData(data);
        avatar.setFilePath(path.toAbsolutePath().toString());
        avatarRepository.save(avatar);
        return avatar.getId();
    }
}
