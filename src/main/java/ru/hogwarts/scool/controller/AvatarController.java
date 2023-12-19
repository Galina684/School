package ru.hogwarts.scool.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.scool.service.AvatarService;

import java.io.IOException;

@RestController
@RequestMapping("avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
public ResponseEntity<String> uploaderAvatar(@PathVariable long id, @RequestParam MultipartFile avatar) throws IOException {
        if(avatar.getSize() > 1024* 300){
            return ResponseEntity.badRequest().body("Файл слишком большой");
        }
        avatarService.uploaderAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }
}
