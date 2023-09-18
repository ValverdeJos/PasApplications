package com.example.pasapplication.data.LocalDatabase;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.pasapplication.data.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Query("SELECT * FROM Product")
    LiveData<List<Product>> getAll();

    @Query("SELECT * FROM Product")
    List<Product> getAll2();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void createProduct(List<Product> product);

    @Query("SELECT * FROM Product  WHERE id = :productId")
    LiveData<Product> getById(int productId);

    @Query("SELECT * FROM Product  WHERE id_compra = :idCompra")
    Product getByIdCompra(String idCompra);

    @Query("SELECT * FROM Product  WHERE name_user_discord = :nameUserDiscord")
    Product getBynameUserDiscord(String nameUserDiscord);

    @Delete
    void delete(Product product);

    @Update
    void updateProduct(Product product);

    @Insert
    void insert2(Product product);


    @Insert
    void insertProduct(List<Product> product);

}
