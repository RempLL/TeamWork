package ru.skypro.teamwork.constant;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandEnum {
    START("Выбор приюта", "/start"),
    DOG_SHELTER("Приют для собак", "/dog"),
    CAT_SHELTER("Приют для кошек", "/cat"),
    INFO("Информация о приюте", "/info"),
    GET("Как взять животное из приюта", "/get"),
    REPORT("Прислать отчет о питомце", "/report"),
    TEXT("Ввести текст отчета", "/text"),
    PICTURE("Отправить фото", "/picture"),
    HELP("Позвать волонтера", "/help");
    private final String description;
    private final String command;

    public InlineKeyboardButton getButton() {
        return new InlineKeyboardButton(description).callbackData(command);
    }
}