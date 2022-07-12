package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 11:27 AM
 * project DBP-Final
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject {
    private String subjectId;
    private String subName;
    private double credit;
    private String teacherId;
}
