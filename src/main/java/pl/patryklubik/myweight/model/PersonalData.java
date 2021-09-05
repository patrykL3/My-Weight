package pl.patryklubik.myweight.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


/**
 * Create by Patryk Łubik on 04.09.2021.
 */

@Entity
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private int age;
    @NotBlank
    private int height;
    @NotBlank
    private String sex;
    @NotBlank
    private int desiredWeight;
    @OneToOne
    @JoinColumn(name = "user_id")
    private int userId;


    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(int desiredWeight) {
        this.desiredWeight = desiredWeight;
    }
}
