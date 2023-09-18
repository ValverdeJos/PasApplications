package com.example.pasapplication.data.Repertory;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.pasapplication.data.LocalDatabase.AppDatabase;
import com.example.pasapplication.data.LocalDatabase.ProductDAO;
import com.example.pasapplication.data.Product;
import com.example.pasapplication.data.Service.ProductService;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Repository {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5018/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


    private ProductDAO productDAO;

    private ProductService productService;

    private Executor executor = Executors.newSingleThreadExecutor();


    public Repository(Context context) {
        this.productDAO = AppDatabase.getInstance(context).getProductDAO();
        this.productService = retrofit.create(ProductService.class);
    }



    public LiveData<List<Product>> getProductsList(){
        return this.productDAO.getAll();
    }

    public LiveData<Product> getProductFromId(int productId){
        return this.productDAO.getById(productId);
    }

    public void updateProduct(Product product, Callback<Product> callback) {

        productDAO.updateProduct(product);


        productService.updateProduct(product.getId(), product).enqueue(callback);
    }



    public void deleteProduct(int productId, Callback<Product> callback) {

        LiveData<Product> localProductLiveData = getProductFromId(productId);

        localProductLiveData.observeForever(new Observer<Product>() {
            @Override
            public void onChanged(Product localProduct) {
                localProductLiveData.removeObserver(this);

                if (localProduct != null) {

                    productDAO.delete(localProduct);


                    productService.deleteProduct(productId).enqueue(callback);
                }
            }
        });
    }


    public void createProductAndInsert(Product product, Callback<Product> callback) {
        productService.createProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.isSuccessful()) {

                    Product newProduct = response.body();


                    productDAO.insert2(newProduct);


                    callback.onResponse(call, response);
                } else {
                    // Tratamento de erro se a criação na API falhar
                    callback.onFailure(call, new Throwable("Failed to create product: " + response.message()));
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                // Tratamento de erro de falha na chamada da API
                callback.onFailure(call, t);
            }
        });
    }



    public void ItemsRefresh(){
        this.productService.getProductList().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()){
                    List<Product> productList = response.body();
                    executor.execute(() -> productDAO.createProduct(productList));



                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
