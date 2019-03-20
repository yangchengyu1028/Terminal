package com.ycy.entity;


import java.io.Serializable;

/**
 * 症状
 */
public class SymptomEntity implements Serializable {

    private static final long serialVersionUID = 8619902102922122203L;
    private int symptom_id;
    private String symptom_name;

    public int getSymptom_id() {
        return symptom_id;
    }

    public void setSymptom_id(int symptom_id) {
        this.symptom_id = symptom_id;
    }

    public String getSymptom_name() {
        return symptom_name;
    }

    public void setSymptom_name(String symptom_name) {
        this.symptom_name = symptom_name;
    }
}
