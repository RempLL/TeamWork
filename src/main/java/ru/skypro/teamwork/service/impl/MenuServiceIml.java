package ru.skypro.teamwork.service.impl;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.teamwork.constant.ButtonEnum;
import ru.skypro.teamwork.constant.MessageEnum;
import ru.skypro.teamwork.service.UserService;
import ru.skypro.teamwork.service.MenuService;


@RequiredArgsConstructor
@Slf4j
@Service
public class MenuServiceIml implements MenuService {
    private final UserService userService;
    private static InlineKeyboardMarkup inlineKeyboard;

    @Override
    public SendMessage defaultMenu(Long chatId, String messageText) {

        switch (messageText) {

            case "/start" -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.DOG_BUTTON.get(), ButtonEnum.CAT_BUTTON.get());
                return new SendMessage(chatId, MessageEnum.START_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
            case "/dog" -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.INFO_BUTTON.get());
                inlineKeyboard.addRow(ButtonEnum.GET_BUTTON.get());
                inlineKeyboard.addRow(ButtonEnum.HELP_BUTTON.get());
                userService.setShelter(chatId, "dog");
                return new SendMessage(chatId, MessageEnum.DOGSHELTER_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
            case "/cat" -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.INFO_BUTTON.get());
                inlineKeyboard.addRow(ButtonEnum.GET_BUTTON.get());
                inlineKeyboard.addRow(ButtonEnum.HELP_BUTTON.get());
                userService.setShelter(chatId, "cat");
                return new SendMessage(chatId, MessageEnum.CATSHELTER_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
            case "/info" -> {
                String shelter = (userService.getShelter(chatId).equals("dog") ? "собак" : "кошек");
                String text = MessageEnum.INFO_MESSAGE.getMessage() + shelter;
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.INFO_BUTTON.get(), ButtonEnum.GET_BUTTON.get());
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }
            default -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.SHELTER_BUTTON.get(), ButtonEnum.HELP_BUTTON.get());
                return new SendMessage(chatId, MessageEnum.ERROR_MESSAGE.getMessage()).replyMarkup(inlineKeyboard);
            }
        }
    }

    @Override
    public SendMessage ownerMenu(Long chatId, String messageText) {

        switch (messageText) {

            case "/report" -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.TEXT_BUTTON.get(), ButtonEnum.PICTURE_BUTTON.get());
                String text = "Выберите вид сообщения";
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }
            default -> {
                return defaultMenu(chatId, messageText);
            }
        }
    }

    @Override
    public SendMessage volunteerMenu(Long chatId, String messageText) {

        switch (messageText) {

            case "/start" -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.DOG_BUTTON.get(), ButtonEnum.CAT_BUTTON.get());
                String text = "Выберите приют для работы";
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }

            case "/take" -> {
                inlineKeyboard = new InlineKeyboardMarkup(ButtonEnum.DOG_BUTTON.get(), ButtonEnum.CAT_BUTTON.get());
                String text = "Соединить вас с чатом?";
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }
            default -> {
                return defaultMenu(chatId, messageText);
            }
        }
    }

    @Override
    public SendMessage adminMenu(Long chatId, String messageText) {
        inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{ButtonEnum.INFO_BUTTON.get(), ButtonEnum.GET_BUTTON.get()},
                new InlineKeyboardButton[]{ButtonEnum.HELP_BUTTON.get(), ButtonEnum.REPORT_BUTTON.get()});
        return new SendMessage(chatId, "admin menu").replyMarkup(inlineKeyboard);
    }
}