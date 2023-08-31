package ru.skypro.teamwork.constant;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ButtonEnum {
    SHELTER_BUTTON("Выбор приюта", "/start"),
    DOG_BUTTON("Приют для собак", "/dog"),
    CAT_BUTTON("Приют для кошек", "/cat"),
    INFO_BUTTON("Информация о приюте", "/info"),
    GET_BUTTON("Как взять животное из приюта", "/get"),
    REPORT_BUTTON("Прислать отчет о питомце", "/report"),
    TEXT_BUTTON("Ввести текст отчета", "/text"),
    PICTURE_BUTTON("Отправить фото", "/picture"),
    HELP_BUTTON("Позвать волонтера", "/help");
    private final String text;
    private final String command;

    public InlineKeyboardButton get() {
        return new InlineKeyboardButton(text).callbackData(command);
    }
}