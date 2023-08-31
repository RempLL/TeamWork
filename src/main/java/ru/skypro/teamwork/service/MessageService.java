package ru.skypro.teamwork.service;

import com.pengrad.telegrambot.request.SendMessage;

public interface MessageService {
    void checkMessage(Long chatId, String name, String messageText);

    void sendMsg(Long chatId, String text);

    void sendMsg(SendMessage message);
}
