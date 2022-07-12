package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 11:25 AM
 * project DBP-Final
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration {
    private String registrationId;
    private LocalDate regDate;
    private String studentId;
    private String intakeId;
}
