package ru.skypro.teamwork.service.impl;

import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.teamwork.constant.CommandEnum;
import ru.skypro.teamwork.service.MenuService;

@RequiredArgsConstructor
@Slf4j
@Service
public class VolunteerMenuService implements MenuService {

    @Override
    public SendMessage mainMenu(Long chatId, String messageText) {

        InlineKeyboardMarkup inlineKeyboard;

        switch (messageText) {

            case "/start" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.DOG_SHELTER.getButton(), CommandEnum.CAT_SHELTER.getButton());
                String text = "Выберите приют для работы";
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }

            case "/take" -> {
                inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.DOG_SHELTER.getButton(), CommandEnum.CAT_SHELTER.getButton());
                String text = "Соединить вас с чатом?";
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }
            default -> {
                return helpMenu(chatId);
            }
        }
    }
}
