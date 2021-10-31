package pl.patryklubik.myweight.model;

/**
 * Create by Patryk Łubik on 31.10.2021.
 */
public enum ThymeleafAttributes {
    DESCRIPTION_OF_BMI_LEVEL("descriptionOfBmiLevel"),
    BMI_LEVEL_CORRECT("bmiLevelCorrect"),
    PERSONAL_DATA("personalData"),
    BASIC_WEIGHT_DATA("basicWeightData"),
    MESSAGE_ERROR("messageError"),
    WEIGHT("weight"),
    EDITED_WEIGHT("editedWeight"),
    MESSAGE_SUCCESS("messageSuccess"),
    WEIGHT_HISTORY_DATA("weightHistoryData");

    private final String name;


    ThymeleafAttributes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

