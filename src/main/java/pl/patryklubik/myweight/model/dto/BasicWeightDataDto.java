package pl.patryklubik.myweight.model.dto;


/**
 * Create by Patryk Łubik on 30.09.2021.
 */

public class BasicWeightDataDto {

    private float currentWeight;
    private float minWeight;
    private float maxWeight;
    private float bmi;
    private String currentWeightDate;
    private String minWeightDate;
    private String maxWeightDate;

    public BasicWeightDataDto(float currentWeight, String currentWeightDate, float minWeight,
                              String minWeightDate, float maxWeight, String maxWeightDate,
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

    public String getCurrentWeightDate() {
        return currentWeightDate;
    }

    public void setCurrentWeightDate(String currentWeightDate) {
        this.currentWeightDate = currentWeightDate;
    }

    public String getMinWeightDate() {
        return minWeightDate;
    }

    public void setMinWeightDate(String minWeightDate) {
        this.minWeightDate = minWeightDate;
    }

    public String getMaxWeightDate() {
        return maxWeightDate;
    }

    public void setMaxWeightDate(String maxWeightDate) {
        this.maxWeightDate = maxWeightDate;
    }
}
