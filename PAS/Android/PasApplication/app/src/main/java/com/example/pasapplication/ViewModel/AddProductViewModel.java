package com.example.pasapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pasapplication.data.Product;
import com.example.pasapplication.data.Repertory.Repository;

import retrofit2.Callback;

public class AddProductViewModel extends AndroidViewModel {

    private Repository repository;

    public AddProductViewModel(@NonNull Application application) {
        super(application);
        this.repository=new Repository(application.getApplicationContext());
    }

    public void createProductAndInsert(Product product, Callback<Product> callback) {
        repository.createProductAndInsert(product, callback);
    }

    public void refreshProducts() {
        repository.ItemsRefresh();
    }
}
