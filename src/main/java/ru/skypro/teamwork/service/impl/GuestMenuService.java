package ru.skypro.teamwork.service.impl;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.teamwork.constant.CommandEnum;
import ru.skypro.teamwork.constant.MessageEnum;
import ru.skypro.teamwork.service.UserService;
import ru.skypro.teamwork.service.MenuService;


@RequiredArgsConstructor
@Slf4j
@Service
public class GuestMenuService implements MenuService {
    private final UserService userService;

    @Override
    public SendMessage mainMenu(Long chatId, String messageText) {

        InlineKeyboardMarkup inlineKeyboard;

        switch (messageText) {

            case "/start" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.DOG_SHELTER.getButton(), CommandEnum.CAT_SHELTER.getButton());
                return new SendMessage(chatId, MessageEnum.START_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
            case "/dog" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.INFO.getButton());
                inlineKeyboard.addRow(CommandEnum.GET.getButton());
                inlineKeyboard.addRow(CommandEnum.HELP.getButton());
                userService.setState(chatId, "dog");
                return new SendMessage(chatId, MessageEnum.DOG_SHELTER_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
            case "/cat" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.INFO.getButton());
                inlineKeyboard.addRow(CommandEnum.GET.getButton());
                inlineKeyboard.addRow(CommandEnum.HELP.getButton());
                userService.setState(chatId, "cat");
                return new SendMessage(chatId, MessageEnum.CAT_SHELTER_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
            case "/info" -> {
                String shelter = (userService.getState(chatId).equals("dog") ? "собак" : "кошек");
                String text = MessageEnum.INFO_MESSAGE.getMessage() + shelter;
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.GET.getButton());
                inlineKeyboard.addRow(CommandEnum.HELP.getButton());
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }
            case "/help" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.START.getButton(), CommandEnum.HELP.getButton());
                return new SendMessage(chatId, "Пока помочь некому").replyMarkup(inlineKeyboard);
            }
            case "/get" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.START.getButton(), CommandEnum.HELP.getButton());
                return new SendMessage(chatId, "Скоро расскажем").replyMarkup(inlineKeyboard);
            }
            default -> {
                return helpMenu(chatId);
            }
        }
    }
}