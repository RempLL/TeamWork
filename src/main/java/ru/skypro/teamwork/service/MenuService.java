package ru.skypro.teamwork.service;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import ru.skypro.teamwork.constant.CommandEnum;
import ru.skypro.teamwork.constant.MessageEnum;

public interface MenuService {
    SendMessage mainMenu(Long chatId, String messageText);

    default SendMessage helpMenu(Long chatId) {

        InlineKeyboardMarkup inlineKeyboard;

        inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.START.getButton(), CommandEnum.HELP.getButton());
        return new SendMessage(chatId, MessageEnum.ERROR_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
    }
}