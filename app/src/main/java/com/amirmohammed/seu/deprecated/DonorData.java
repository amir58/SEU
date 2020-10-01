package com.amirmohammed.seu.deprecated;

import com.amirmohammed.seu.Questionnaire;

import java.util.List;

public class DonorData {
    private List<Questionnaire> questionnaires;

    public DonorData(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public DonorData() {
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }
}
