package ru.skypro.teamwork.service;

import com.pengrad.telegrambot.request.SendMessage;

public interface MenuService {
    SendMessage defaultMenu(Long chatId, String messageText);

    SendMessage volunteerMenu(Long chatId, String messageText);

    SendMessage ownerMenu(Long chatId, String messageText);

    SendMessage adminMenu(Long chatId, String messageText);

}
