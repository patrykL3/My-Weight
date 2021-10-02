package pl.patryklubik.myweight.model;

/**
 * Create by Patryk Łubik on 30.09.2021.
 */

public class BasicWeightDataDto {

    private float currentWeight;
    private float minWeight;
    private float maxWeight;
    private float bmi;

    public BasicWeightDataDto(float currentWeight, float minWeight, float maxWeight, float bmi) {
        this.currentWeight = currentWeight;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
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
}
