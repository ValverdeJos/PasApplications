package com.example.pasapplication.View.Fragments;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pasapplication.R;
import com.example.pasapplication.ViewModel.ProductViewModel;
import com.example.pasapplication.data.AdapterProduct;
import com.example.pasapplication.data.LocalDatabase.AppDatabase;
import com.example.pasapplication.data.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment implements AdapterProduct.ProductAdapterEventListener{


    private ProductViewModel productViewModel;


    private NavController navController;


    private AdapterProduct adapterProduct;



    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_home, container, false);

        navController = NavHostFragment.findNavController(HomeFragment.this);

        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        this.adapterProduct = new AdapterProduct(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setAdapter(this.adapterProduct);
        recyclerView.setLayoutManager(layoutManager);

        this.productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        this.productViewModel.getProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                adapterProduct.updateProductList(productList);
            }
        });

        productViewModel.refreshProducts();


        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onProductClicked(int productId) {
        System.out.println(productId);
        NavDirections detalihesActions = HomeFragmentDirections.actionHomeFragmentToDetailesFragments(productId);
        navController.navigate(detalihesActions);
    }

    @Override
    public void onProductLongClicked(int productId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Delete Product?");
        builder.setMessage("Do you really want to delete this Product?");

        builder.setNegativeButton("Cancel", (dialogInterface, i) -> {

        });

        builder.setPositiveButton("Delete", (dialogInterface, i) -> {
            productViewModel.deleteProduct(productId, new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {

                        productViewModel.refreshProducts();
                    } else {
                        Toast.makeText(getContext(), "Failed to delete product. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Failed to delete product: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Toast.makeText(getContext(), "Network error. Please check your connection and try again.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Network error: " + t.getMessage());
                }
            });
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}