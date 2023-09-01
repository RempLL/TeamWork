package ru.skypro.teamwork.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum MessageEnum {
    NEW_USER_MESSAGE("""
                        
            Это бот приюта для животных в Астане.
            Мы хотим помочь вам забрать собаку или кошку домой."""),
    START_MESSAGE("Какой приют вас интересует?"),
    DOG_SHELTER_MESSAGE("Приют для собак"),
    CAT_SHELTER_MESSAGE("Приют для кошек"),
    ERROR_MESSAGE("Некорректный ввод"),
    INFO_MESSAGE("Вас приветствует приют для ");
    private final String message;
}