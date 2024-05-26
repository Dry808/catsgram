package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Модель, которая описывает изображения, прикреплённые к конкретному сообщению
 */

@Data
@EqualsAndHashCode(of = {"id"})
public class Image {
    private Long id; // идентификатор изображения
    private long postId; // идентификатор поста где опубликованно изображение
    private String originalFileName;
    private String filePath;
}
