package ru.skypro.teamwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.teamwork.entity.Guest;
import ru.skypro.teamwork.repository.GuestRepository;
import ru.skypro.teamwork.service.GuestService;

@RequiredArgsConstructor
@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;

    @Override
    @Transactional
    public String addGuest(Long chatId, String messageText) {
        return "Некорректный ввод";
    }

    @Override
    public String getAccessLevel(Long chatId, String name) {
        try {
            return guestRepository.findFirstByChatId(chatId).getAccessLevel();
        } catch (NullPointerException e) {
            guestRepository.save(
                    Guest.builder()
                            .chatId(chatId)
                            .fullName(name)
                            .accessLevel("new")
                            .build());
            return "new";
        }
    }

    @Override
    @Transactional
    public void setAccessLevel(Long chatId, String accessLevel) {
        Guest guest = guestRepository.findFirstByChatId(chatId);
        guest.setAccessLevel("guest");
        guestRepository.save(guest);
    }

    @Override
    public String getSpecies(Long chatId) {
        return "собак";
    }

    @Override
    public void setSpecies(Long chatId, String species) {
    }

    @Override
    public Guest getGuest(Long chatId) {
        return guestRepository.findFirstByChatId(chatId);
    }
}
