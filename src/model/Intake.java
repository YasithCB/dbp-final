package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 11:22 AM
 * project DBP-Final
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Intake {
    private String intakeId;
    private LocalDate startDate;
    private String intakeCol;
    private String description;
    private String courseId;
}
