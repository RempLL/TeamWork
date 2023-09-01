package ru.skypro.teamwork.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.teamwork.entity.User;
import ru.skypro.teamwork.repository.UserRepository;
import ru.skypro.teamwork.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String addUser(Long chatId, String messageText) {
        return "Некорректный ввод";
    }

    @Override
    public User getUser(Long chatId) {
        return userRepository.findFirstByChatId(chatId);
    }

    @Override
    @Transactional
    public void setAccessLevel(Long chatId, String accessLevel) {
        User user = userRepository.findFirstByChatId(chatId);
        user.setAccessLevel(accessLevel);
        userRepository.save(user);
    }

    @Override
    public String getAccessLevel(Long chatId, String name) {
        try {
            return userRepository.findFirstByChatId(chatId).getAccessLevel();
        } catch (NullPointerException e) {
            userRepository.save(
                    User.builder()
                            .chatId(chatId)
                            .fullName(name)
                            .accessLevel("new")
                            .build());
            return "new";
        }
    }

    @Override
    @Transactional
    public void setState(Long chatId, String currentState) {
        User user = userRepository.findFirstByChatId(chatId);
        user.setCurrentState(currentState);
        userRepository.save(user);
    }

    @Override
    public String getState(Long chatId) {
        return userRepository.findFirstByChatId(chatId).getCurrentState();
    }
}