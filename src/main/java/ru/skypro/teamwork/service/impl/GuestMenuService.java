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
        String sendingMessage;

        switch (messageText) {

            case "/start" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.DOG_SHELTER.getButton(), CommandEnum.CAT_SHELTER.getButton());
                sendingMessage = MessageEnum.START_MESSAGE.getMessage();
            }
            case "/dog" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.INFO.getButton());
                inlineKeyboard.addRow(CommandEnum.GET.getButton());
                userService.setState(chatId, "dog");
                sendingMessage = MessageEnum.DOG_SHELTER_MESSAGE.getMessage();
            }
            case "/cat" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.INFO.getButton());
                inlineKeyboard.addRow(CommandEnum.GET.getButton());
                userService.setState(chatId, "cat");
                sendingMessage = MessageEnum.CAT_SHELTER_MESSAGE.getMessage();
            }
            case "/info" -> {
                String shelter = (userService.getState(chatId).equals("dog") ? "собак" : "кошек");
                sendingMessage = MessageEnum.INFO_MESSAGE.getMessage() + shelter;
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.GET.getButton());
            }
            case "/help" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.START.getButton());
                sendingMessage = "Пока помочь некому";
            }
            case "/get" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.START.getButton());
                sendingMessage = "Скоро расскажем";
            }
            default -> {
                return helpMenu(chatId);
            }
        }
        inlineKeyboard.addRow(CommandEnum.HELP.getButton());
        return new SendMessage(chatId, sendingMessage).replyMarkup(inlineKeyboard);
    }
}