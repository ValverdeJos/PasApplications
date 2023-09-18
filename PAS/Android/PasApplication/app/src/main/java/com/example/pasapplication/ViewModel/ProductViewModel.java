package com.example.pasapplication.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pasapplication.data.LocalDatabase.AppDatabase;
import com.example.pasapplication.data.LocalDatabase.ProductDAO;
import com.example.pasapplication.data.Product;
import com.example.pasapplication.data.Repertory.Repository;

import java.util.List;

import retrofit2.Callback;


public class ProductViewModel extends AndroidViewModel {

    private Repository repository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        this.repository=new Repository(application.getApplicationContext());
    }

    public LiveData<List<Product>> getProducts(){
        return repository.getProductsList();
    }

    public void deleteProduct(int productId, Callback<Product> callback) {
        repository.deleteProduct(productId, callback);
    }

    public LiveData<Product> getProductById(int productId){
        return repository.getProductFromId(productId);
    }

    public void refreshProducts() {
        repository.ItemsRefresh();
    }

}
