package ru.skypro.teamwork.service.impl;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
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
public class AdminMenuService implements MenuService {
    @Override
    public SendMessage mainMenu(Long chatId, String messageText) {
        InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(
                new InlineKeyboardButton[]{CommandEnum.HELP.getButton(), CommandEnum.HELP.getButton()},
                new InlineKeyboardButton[]{CommandEnum.HELP.getButton(), CommandEnum.HELP.getButton()});
        return new SendMessage(chatId, "Здесь будет меню админа").replyMarkup(inlineKeyboard);
    }
}