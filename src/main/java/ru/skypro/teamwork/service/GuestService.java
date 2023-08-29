package ru.skypro.teamwork.service;

import ru.skypro.teamwork.entity.Guest;

public interface GuestService {
    String addGuest(Long chatId, String fullName);

    Guest getGuest(Long chatId);

    String getAccessLevel(Long chatId, String name);

    void setAccessLevel(Long chatId, String accessLevel);

    String getSpecies(Long chatId);

    void setSpecies(Long chatId, String species);

}
