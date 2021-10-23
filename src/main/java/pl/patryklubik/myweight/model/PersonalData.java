package pl.patryklubik.myweight.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * Create by Patryk Łubik on 04.09.2021.
 */

@Entity
public class PersonalData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private int age;
    @NotNull
    private int height;
    @NotNull
    private String sex;
    @NotNull
    private float desiredWeight;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


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

    public float getDesiredWeight() {
        return desiredWeight;
    }

    public void setDesiredWeight(float desiredWeight) {
        this.desiredWeight = desiredWeight;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserUsername() {
        return user.getUsername();
    }

    public void update(final PersonalData source) {

        age = source.age;
        height = source.height;
        sex = source.sex;
        desiredWeight = source.desiredWeight;
    }
}
