package dev.sk.springbatchsamplev5upgrade.model;


import lombok.Data;
import java.time.LocalDate;

@Data
public class Person {
    String name;
    LocalDate dob;
    int age;
}
