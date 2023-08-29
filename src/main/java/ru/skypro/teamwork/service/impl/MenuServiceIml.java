package ru.skypro.teamwork.service.impl;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.teamwork.service.GuestService;
import ru.skypro.teamwork.service.MenuService;


@RequiredArgsConstructor
@Slf4j
@Service
public class MenuServiceIml implements MenuService {
    private final TelegramBot telegramBot;
    private final GuestService guestService;
    private static final InlineKeyboardButton button0 = new InlineKeyboardButton("Выбор приюта").callbackData("/start");
    private static final InlineKeyboardButton button1 = new InlineKeyboardButton("Приют для кошек").callbackData("/cat");
    private static final InlineKeyboardButton button2 = new InlineKeyboardButton("Приют для собак").callbackData("/dog");
    private static final InlineKeyboardButton button3 = new InlineKeyboardButton("Информация о приюте").callbackData("/info");
    private static final InlineKeyboardButton button4 = new InlineKeyboardButton("Как взять животное из приюта").callbackData("/pet");
    private static final InlineKeyboardButton button5 = new InlineKeyboardButton("Прислать отчет о питомце").callbackData("/report");
    private static final InlineKeyboardButton button6 = new InlineKeyboardButton("Позвать волонтера").callbackData("/help");
    private static final InlineKeyboardButton button7 = new InlineKeyboardButton("Рассказать о приюте").callbackData("/shelterInfo");
    private static InlineKeyboardMarkup inlineKeyboard;

    @Override
    public void checkMessage(Long chatId, String name, String messageText) {

        switch (guestService.getAccessLevel(chatId, name)) {

            case "new" -> {
                String hello = "Привет, " + name + "!" +
                        "\nЭто бот приюта для животных в Астане." +
                        "\nМы хотим помочь вам забрать собаку или кошку домой." + "\n";
                guestService.setAccessLevel(chatId, "guest");
                guestMenu(chatId, messageText, hello);
            }
            case "guest" -> {
                guestMenu(chatId, messageText, "");
            }
            case "volunteer" -> {
            }
            case "host" -> {
            }
            case "admin" -> {
                inlineKeyboard = new InlineKeyboardMarkup(
                        new InlineKeyboardButton[]{button3, button4},
                        new InlineKeyboardButton[]{button6, button5});
                showMenu(chatId, "admin menu", inlineKeyboard);
            }
        }
    }

    private void guestMenu(Long chatId, String messageText, String helloText) {

        switch (messageText) {

            case "/start" -> {
                inlineKeyboard = new InlineKeyboardMarkup(button1, button2);
                String text = helloText + "Какой приют вас интересует?";
                showMenu(chatId, text, inlineKeyboard);
            }

            case "/cat" -> {
                inlineKeyboard = new InlineKeyboardMarkup(button3);
                inlineKeyboard.addRow(button4);
                inlineKeyboard.addRow(button6);
                showMenu(chatId, "Приют для кошек", inlineKeyboard);
            }

            case "/dog" -> {
                inlineKeyboard = new InlineKeyboardMarkup(button3);
                inlineKeyboard.addRow(button4);
                inlineKeyboard.addRow(button6);
                showMenu(chatId, "Приют для собак", inlineKeyboard);
            }
            case "/info" -> {
                String text = "Приют для " + guestService.getSpecies(chatId) + " приветствует вас!";
                inlineKeyboard = new InlineKeyboardMarkup(button3, button4);
                showMenu(chatId, text, inlineKeyboard);
            }

            default -> {
                inlineKeyboard = new InlineKeyboardMarkup(button0, button6);
                showMenu(chatId, "Некорректный ввод", inlineKeyboard);
            }
        }
    }

    @Override
    public void sendMsg(Long chatId, String text) {
        SendMessage message = new SendMessage(chatId, text);
        try {
            SendResponse response = telegramBot.execute(message);
            log.info("Sent: {}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showMenu(Long chatId, String text, InlineKeyboardMarkup inlineKeyboard) {
        SendMessage message = new SendMessage(chatId, text).replyMarkup(inlineKeyboard);
        try {
            SendResponse response = telegramBot.execute(message);
            log.info("Sent: {}", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}