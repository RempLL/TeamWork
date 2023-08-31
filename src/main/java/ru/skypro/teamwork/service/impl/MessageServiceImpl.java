package ru.skypro.teamwork.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.teamwork.constant.MessageEnum;
import ru.skypro.teamwork.service.MenuService;
import ru.skypro.teamwork.service.MessageService;
import ru.skypro.teamwork.service.UserService;

@RequiredArgsConstructor
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {
    private final TelegramBot telegramBot;
    private final UserService userService;
    private final MenuService menuService;

    @Override
    public void checkMessage(Long chatId, String name, String messageText) {

        switch (userService.getAccessLevel(chatId, name)) {

            case "new" -> {
                sendMsg(chatId, "Привет, " + name + "!" + MessageEnum.NEWUSER_MESSAGE.getMessage());
                userService.setAccessLevel(chatId, "guest");
                sendMsg(menuService.defaultMenu(chatId, messageText));
            }
            case "guest" -> sendMsg(menuService.defaultMenu(chatId, messageText));
            case "volunteer" -> sendMsg(menuService.volunteerMenu(chatId, messageText));
            case "owner" -> sendMsg(menuService.ownerMenu(chatId, messageText));
            case "admin" -> sendMsg(menuService.adminMenu(chatId, messageText));
        }
    }

    @Override
    public void sendMsg(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        sendMsg(message);
    }

    @Override
    public void sendMsg(SendMessage message) {
        try {
            SendResponse response = telegramBot.execute(message);
            log.info("Sent: {}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
