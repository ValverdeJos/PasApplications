package com.example.pasapplication.data.Service;

import com.example.pasapplication.data.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {
    @GET("Product")
    Call<List<Product>> getProductList();

    @POST("ProductPost")
    Call<Product> createProduct(@Body Product newProduct);

    @PUT("ProductPut/{idProduct}")
    Call<Product> updateProduct(@Path("idProduct") int idProduct, @Body Product product);

    @DELETE("ProductDelete/{id}")
    Call<Product> deleteProduct(@Path("id") int productId);

}
