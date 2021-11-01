package pl.patryklubik.myweight.model.dto;


import java.util.Date;


/**
 * Create by Patryk Łubik on 24.10.2021.
 */

public class WeightDto {
    private final int id;
    private final float value;
    private final Date date;

    public WeightDto(int id, float value, Date date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public float getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }
}