package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 11:28 AM
 * project DBP-Final
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String teacherId;
    private String name;
    private String nic;
    private String contact;
    private String address;
}
