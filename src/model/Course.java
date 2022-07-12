package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 11:20 AM
 * project DBP-Final
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    private String courseId;
    private String courseName;
    private double cost;
    private String duration;
    private String subjectId;
}
