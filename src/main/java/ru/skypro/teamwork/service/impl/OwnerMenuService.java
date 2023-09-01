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
public class OwnerMenuService implements MenuService {
    private final GuestMenuService guestMenuService;

    @Override
    public SendMessage mainMenu(Long chatId, String messageText) {

        switch (messageText) {

            case "/report" -> {
                InlineKeyboardMarkup inlineKeyboard = new InlineKeyboardMarkup(CommandEnum.TEXT.getButton(), CommandEnum.PICTURE.getButton());
                String text = "Выберите вид сообщения";
                return new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
            }
            default -> {
                return guestMenuService.mainMenu(chatId, messageText);
            }
        }
    }
}