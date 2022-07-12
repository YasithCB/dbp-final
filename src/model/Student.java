package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * author  Yasith C Bandara
 * created 7/12/2022 - 10:05 AM
 * project DBP-Final
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String nic;
}
