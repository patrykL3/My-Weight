package pl.patryklubik.myweight.model.dto;


import java.util.Date;


/**
 * Create by Patryk Łubik on 24.10.2021.
 */
public class WeightDto {
    private int id;
    private float value;
    private Date date;

    public WeightDto(int id, float value, Date date) {
        this.id = id;
        this.value = value;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
