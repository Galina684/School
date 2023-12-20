package ru.hogwarts.scool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.scool.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository <Avatar, Long> {
Optional<Avatar> findByAvatar(long student_id);

}
