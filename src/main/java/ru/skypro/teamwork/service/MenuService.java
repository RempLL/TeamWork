package ru.skypro.teamwork.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;

public interface MenuService {
    void checkMessage(Long chatId, String name, String messageText);

    void sendMsg(Long chatId, String text);

    void showMenu(Long chatId, String text, InlineKeyboardMarkup inlineKeyboard);
}
