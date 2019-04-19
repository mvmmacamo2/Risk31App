package com.kaya.risk31app.Models;

import com.google.gson.annotations.SerializedName;

public class Actions {
    int id;
    @SerializedName("action")
    String action;
    @SerializedName("tipo")
    String tipo;
    @SerializedName("prazo")
    String prazo;
    @SerializedName("estado")
    String estado;
    @SerializedName("admin")
    String admin;
    @SerializedName("name_risk")
    String name_risk;
    @SerializedName("color")
    String color;
    @SerializedName("descricao")
    String descricao;
    @SerializedName("created_at")
    String created_at;
    @SerializedName("updated_at")
    String updated_at;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrazo() {
        return prazo;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_risk() {
        return name_risk;
    }

    public void setName_risk(String name_risk) {
        this.name_risk = name_risk;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
