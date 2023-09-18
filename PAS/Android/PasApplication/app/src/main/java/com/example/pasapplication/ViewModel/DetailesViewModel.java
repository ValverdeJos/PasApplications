package com.example.pasapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pasapplication.data.Product;
import com.example.pasapplication.data.Repertory.Repository;

import retrofit2.Callback;

public class DetailesViewModel extends AndroidViewModel {

    private Repository repository;


    public DetailesViewModel(@NonNull Application application) {
        super(application);
        this.repository = new Repository(application.getApplicationContext());
    }

    public LiveData<Product> getProductById(int productId){
        return repository.getProductFromId(productId);
    }

    public void updateProduct(Product product, Callback<Product> callback) {
        repository.updateProduct(product, callback);
    }

    public void refreshItems() {
        repository.ItemsRefresh();

    }



}
