package com.kaya.risk31app.Models;

import com.google.gson.annotations.SerializedName;

public class Risk {
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("sigla")
    String sigla;
    @SerializedName("strategic_objective")
    String estrategiaObjective;
    @SerializedName("area_of_business")
    String areaBusiness;
    @SerializedName("causes")
    String causes;
    @SerializedName("estado")
    String estado;
    @SerializedName("consequence")
    String consequence;
    @SerializedName("action")
    String action;
    @SerializedName("estrategia_mitigacao")
    String estrategiaMitigacao;
    @SerializedName("cor_avaliacao")
    String corAvaliacao;
    @SerializedName("risk_score")
    String score;
    @SerializedName("likelihood_name")
    String likelihoodName;
    @SerializedName("impact_name")
    String impactName;
    @SerializedName("classification")
    String classification;
    @SerializedName("username")
    String username;

    @SerializedName("color")
    String color;
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEstrategiaObjective() {
        return estrategiaObjective;
    }

    public void setEstrategiaObjective(String estrategiaObjective) {
        this.estrategiaObjective = estrategiaObjective;
    }

    public String getAreaBusiness() {
        return areaBusiness;
    }

    public void setAreaBusiness(String areaBusiness) {
        this.areaBusiness = areaBusiness;
    }

    public String getCauses() {
        return causes;
    }

    public void setCauses(String causes) {
        this.causes = causes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getConsequence() {
        return consequence;
    }

    public void setConsequence(String consequence) {
        this.consequence = consequence;
    }

    public String getEstrategiaMitigacao() {
        return estrategiaMitigacao;
    }

    public void setEstrategiaMitigacao(String estrategiaMitigacao) {
        this.estrategiaMitigacao = estrategiaMitigacao;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCorAvaliacao() {
        return corAvaliacao;
    }

    public void setCorAvaliacao(String corAvaliacao) {
        this.corAvaliacao = corAvaliacao;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getLikelihoodName() {
        return likelihoodName;
    }

    public void setLikelihoodName(String likelihoodName) {
        this.likelihoodName = likelihoodName;
    }

    public String getImpactName() {
        return impactName;
    }

    public void setImpactName(String impactName) {
        this.impactName = impactName;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
