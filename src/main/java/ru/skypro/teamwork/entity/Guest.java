package ru.skypro.teamwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Guest")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Guest {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    private String fullName;
    @Column(name = "access_level")
    private String accessLevel;

    @Override
    public String toString() {
        return fullName + " " + id + " " + chatId + " " + accessLevel;
    }
}
