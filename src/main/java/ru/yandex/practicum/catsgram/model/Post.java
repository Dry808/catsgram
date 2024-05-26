package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

/**
 * модель, описывающая сообщения в социальной сети.
 */

@Data
@EqualsAndHashCode(of = {"id"})
public class Post {
    private Long id;  // индетификатор сообщения
    private long authorId;
    private String description;
    private Instant postDate;
}
