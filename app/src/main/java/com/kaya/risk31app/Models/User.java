package com.kaya.risk31app.Models;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

public class User {
    @Json(name = "id")
    int id;
    @Json(name = "name")
    String name;
    @Json(name = "email")
    String email;
    @Json(name = "lingua")
    String lingua;
    @Json(name = "telefone")
    String telefone;
    @Json(name = "celular")
    String celular;
    @Json(name = "endereco")
    String endereco;
    @Json(name = "biografia")
    String biografia;
    @Json(name = "foto")
    String foto;
    @Json(name = "nivel")
    String nivel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
