package com.example.pasapplication.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.util.Date;


@Entity

public class Product {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private int nameProduct;

    @ColumnInfo(name = "id_compra")
    @SerializedName("idCompra")
    public String idCompra;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "created")
    public String created;
    @ColumnInfo(name = "mes_premium")
    public int mesPremium;
    @ColumnInfo(name = "id_serve")
    @SerializedName("idServe")
    public String idServe;
    @ColumnInfo(name = "name_serve")
    @SerializedName("nameServe")
    public String nameServe;
    @ColumnInfo(name = "id_user_serve")
    @SerializedName("idUserServe")
    public String idUserServe;
    @ColumnInfo(name = "name_user_discord")
    @SerializedName("nameUserDiscord")
    public String nameUserDiscord;
    private int priority;


    public Product(int id, int nameProduct, String idCompra, String description, String created, int mesPremium, String idServe, String nameServe, String idUserServe, String nameUserDiscord,int priority) {
        this.id = id;
        this.nameProduct = nameProduct;
        this.idCompra = idCompra;
        this.description = description;
        this.created = created;
        this.mesPremium = mesPremium;
        this.idServe = idServe;
        this.nameServe = nameServe;
        this.idUserServe = idUserServe;
        this.nameUserDiscord = nameUserDiscord;
        this.priority=priority;

    }

    public int getId() {
        return id;
    }



    public String getIdCompra() {
        return idCompra;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated() {
        return created;
    }

    public int getMesPremium() {
        return mesPremium;
    }

    public String getIdServe() {
        return idServe;
    }

    public String getNameServe() {
        return nameServe;
    }

    public String getIdUserServe() {
        return idUserServe;
    }

    public String getNameUserDiscord() {
        return nameUserDiscord;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNameProduct() {
        return nameProduct;
    }

    public int getPriority() {
        return priority;
    }
}
