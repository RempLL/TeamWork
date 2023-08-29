package ru.skypro.teamwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.teamwork.entity.Guest;


@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Guest findFirstByChatId(Long chatId);
}
