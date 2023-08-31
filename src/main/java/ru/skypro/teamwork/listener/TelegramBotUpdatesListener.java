package ru.skypro.teamwork.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.skypro.teamwork.service.MessageService;


import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    private final MessageService messageService;
    private final TelegramBot telegramBot;
    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            log.info("Processing update: {}", update);

            if (update.callbackQuery() != null) {
                String firstName = (update.callbackQuery().from().firstName());
                String username = update.callbackQuery().from().username();
                String lastName = update.callbackQuery().from().lastName();
                String fullName = (firstName == null ? "" : firstName) +
                        (username == null ? "" : "\"" + username + "\"") +
                        (lastName == null ? "" : " " + lastName);
                messageService.checkMessage(
                        update.callbackQuery().from().id(),
                        fullName,
                        update.callbackQuery().data());
            } else if (update.message() != null && update.message().text() != null) {
                String firstName = (update.message().from().firstName());
                String username = update.message().from().username();
                String lastName = update.message().from().lastName();
                String fullName = (firstName == null ? "" : firstName) +
                        (username == null ? "" : "\"" + username + "\"") +
                        (lastName == null ? "" : " " + lastName);
                messageService.checkMessage(
                        update.message().chat().id(),
                        fullName,
                        update.message().text());
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}