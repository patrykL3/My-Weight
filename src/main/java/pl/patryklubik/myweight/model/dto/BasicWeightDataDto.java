package pl.patryklubik.myweight.model.dto;


import java.util.Date;


/**
 * Create by Patryk Łubik on 30.09.2021.
 */

public class BasicWeightDataDto {

    private float currentWeight;
    private float minWeight;
    private float maxWeight;
    private float bmi;
    private Date currentWeightDate;
    private Date minWeightDate;
    private Date maxWeightDate;

    public BasicWeightDataDto(float currentWeight, Date currentWeightDate, float minWeight,
                              Date minWeightDate, float maxWeight, Date maxWeightDate,
                              float bmi) {
        this.currentWeight = currentWeight;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.currentWeightDate = currentWeightDate;
        this.minWeightDate = minWeightDate;
        this.maxWeightDate = maxWeightDate;
        this.bmi = bmi;
    }

    public float getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(float currentWeight) {
        this.currentWeight = currentWeight;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public void setMinWeight(float minWeight) {
        this.minWeight = minWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(float maxWeight) {
        this.maxWeight = maxWeight;
    }

    public float getBmi() {
        return bmi;
    }

    public void setBmi(float bmi) {
        this.bmi = bmi;
    }

    public Date getCurrentWeightDate() {
        return currentWeightDate;
    }

    public void setCurrentWeightDate(Date currentWeightDate) {
        this.currentWeightDate = currentWeightDate;
    }

    public Date getMinWeightDate() {
        return minWeightDate;
    }

    public void setMinWeightDate(Date minWeightDate) {
        this.minWeightDate = minWeightDate;
    }

    public Date getMaxWeightDate() {
        return maxWeightDate;
    }

    public void setMaxWeightDate(Date maxWeightDate) {
        this.maxWeightDate = maxWeightDate;
    }
}
