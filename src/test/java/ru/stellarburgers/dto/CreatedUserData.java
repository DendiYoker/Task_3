package ru.stellarburgers.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatedUserData {
    private String email;
    private String password;
    private String name;
}
