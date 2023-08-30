package ru.skypro.teamwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.teamwork.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByChatId(Long chatId);
}