package pl.patryklubik.myweight.model.dto;


import java.util.Date;


/**
 * Create by Patryk Łubik on 30.09.2021.
 */

public class BasicWeightDataDto {

    private final float currentWeight;
    private final float minWeight;
    private final float maxWeight;
    private final float bmi;
    private final Date currentWeightDate;
    private final Date minWeightDate;
    private final Date maxWeightDate;

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

    public float getMinWeight() {
        return minWeight;
    }

    public float getMaxWeight() {
        return maxWeight;
    }

    public float getBmi() {
        return bmi;
    }

    public Date getCurrentWeightDate() {
        return currentWeightDate;
    }

    public Date getMinWeightDate() {
        return minWeightDate;
    }

    public Date getMaxWeightDate() {
        return maxWeightDate;
    }
}