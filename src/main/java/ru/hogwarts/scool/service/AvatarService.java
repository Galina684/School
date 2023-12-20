package ru.hogwarts.scool.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.scool.model.Avatar;
import ru.hogwarts.scool.model.Student;
import ru.hogwarts.scool.repository.AvatarRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${students.avatar.dir.path}")
    String avatarsDir;
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }

    public void uploaderAvatar(long student_id, MultipartFile file) throws IOException {
        Student student = studentService.readStudent(student_id);

        Path filePath = Path.of(avatarsDir, student_id + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
            Avatar avatar = findAvatar(student_id);
            avatar.setStudent(student);
            avatar.setFilePath(filePath.toString());
            avatar.setFileSize(file.getSize());
            avatar.setMediaType(file.getContentType());
            avatar.setData(file.getBytes());

            avatarRepository.save(avatar);

        }
    }

    public Avatar findAvatar(long student_Id) {
        return avatarRepository.findByAvatar(student_Id).orElseGet(Avatar::new);
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

//    private byte[] generateDataForDB(Path filePath) throws IOException {
//        try (
//                InputStream is = Files.newInputStream(filePath);
//                BufferedInputStream bis = new BufferedInputStream(is, 1024);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream() {
//                    BufferedImage image = ImageIO.read(bis);
//                    int height = image.getHeight() / (image.getWidth() / 100);
//                    BufferedImage preview = new BufferedImage(100, height, image.getType());
//                    Graphics2D graphics = preview.createGraphics();
//                    graphics.drawImage();
//                    graphics.dispose();
//                }
//        )
//    }
}
