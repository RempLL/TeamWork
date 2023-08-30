package ru.skypro.teamwork.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "access_level")
    private String accessLevel;
    @Column(name = "current_state")
    private String currentState;
    @Column(name = "current_shelter")
    private String currentShelter;

    @Override
    public String toString() {
        return id + ": " + fullName + ", " + accessLevel + " -" + chatId;
    }
}
