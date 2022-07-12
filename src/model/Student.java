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
public class Student {
    private String id;
    private String name;
    private String email;
    private String contact;
    private String address;
    private String nic;

    public Student(String id, String name, String email, String contact, String address, String nic) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.nic = nic;
    }
}
