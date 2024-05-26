package ru.yandex.practicum.catsgram.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private Map<Long, User> users = new HashMap<>();
    private Long idCounter = 0L;

    @GetMapping
    public Collection<User> usersList() {
        return users.values();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        if (user.getEmail() == null) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }

        if (isEmailExists(user)) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }
        user.setId(getNextId());
        user.setRegistrationDate(Instant.now());
        users.put(user.getId(), user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        if (user.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }

        if (user.getEmail() != null) {
            if (isEmailExists(user)) {
                throw new DuplicatedDataException("Этот имейл уже используется");
            }
        } else {
            users.get(user.getId()).setEmail(user.getEmail());
        }

        if (user.getUsername() != null) {
            users.get(user.getId()).setUsername(user.getUsername());
        }

        if (user.getPassword() != null) {
            users.get(user.getId()).setPassword(user.getPassword());
        }

        return user;
    }

    private boolean isEmailExists(User user) {
        return users.values().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));
    }

    private long getNextId() {
        idCounter += 1L;
        return idCounter;
    }
}
