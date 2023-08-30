package ru.skypro.teamwork.service;

import ru.skypro.teamwork.entity.User;

public interface UserService {
    String addUser(Long chatId, String fullName);

    User getUser(Long chatId);

    void setAccessLevel(Long chatId, String accessLevel);

    String getAccessLevel(Long chatId, String name);

    void setShelter(Long chatId, String species);

    String getShelter(Long chatId);

    void setState(Long chatId, String currentState);

    String getState(Long chatId);
}
