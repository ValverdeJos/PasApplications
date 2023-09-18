package com.example.pasapplication.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pasapplication.R;
import com.example.pasapplication.ViewModel.DetailesViewModel;
import com.example.pasapplication.ViewModel.ProductViewModel;
import com.example.pasapplication.data.Product;
import com.example.pasapplication.data.Repertory.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailesFragments extends Fragment {

    private View root;
    private DetailesViewModel viewModel;

    private EditText compra,nameServe,idServe,dateCreated,mesPremium,detailesDesc,idUser,nameUser;

    private Date dateApi;
    private Button cancelBtn,saveBtn;

    private NavController navController;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_detailes_fragments, container, false);

        this.viewModel = new ViewModelProvider(this).get(DetailesViewModel.class);
        navController = NavHostFragment.findNavController(DetailesFragments.this);

        this.compra = root.findViewById(R.id.DetailesCompra);
        this.nameServe =root.findViewById(R.id.nameServer);
        this.dateCreated =root.findViewById(R.id.dateCreated);
        this.mesPremium =root.findViewById(R.id.mesPremium);

        this.idServe =root.findViewById(R.id.idServer);
        this.detailesDesc =root.findViewById(R.id.detailesDesc);
        this.idUser =root.findViewById(R.id.idUserServer);
        this.nameUser =root.findViewById(R.id.nameUser);

        this.cancelBtn =root.findViewById(R.id.cancelDetailesBtn);
        this.saveBtn =root.findViewById(R.id.saveDetailesBtn);



        Bundle args = getArguments();

        System.out.println(args);

        if (args != null) {
            if (args.containsKey("IdProduct")) {
                int productId = args.getInt("IdProduct");

                this.viewModel.getProductById(productId).observe(getViewLifecycleOwner(),product -> {

                    System.out.println(product);
                    compra.setText(product.getIdCompra());
                    compra.setKeyListener(null);

                    nameServe.setText(product.getNameServe());
                    dateCreated.setText(String.valueOf(product.getCreated()));;
                    mesPremium.setText(String.valueOf(product.getMesPremium()));

                    idServe.setText(product.getIdServe());
                    detailesDesc.setText(product.getDescription());
                    idUser.setText(product.getIdUserServe());
                    nameUser.setText(product.getNameUserDiscord());



                });



                this.saveBtn.setOnClickListener(v -> {
                    Integer mesPremiumNumber = Integer.valueOf(String.valueOf(mesPremium.getText()));
                    Product updateProduct = new Product(
                            productId,
                            1,
                            String.valueOf(this.compra.getText()),
                            String.valueOf(this.detailesDesc.getText()),
                            String.valueOf(this.dateCreated.getText()),
                            mesPremiumNumber,
                            String.valueOf(this.idServe.getText()),
                            String.valueOf(this.nameServe.getText()),
                            String.valueOf(this.idUser.getText()),
                            String.valueOf(this.nameUser.getText()),
                            1);


                    viewModel.updateProduct(updateProduct, new Callback<Product>() {
                        @Override
                        public void onResponse(Call<Product> call, Response<Product> response) {
                            if (response.isSuccessful()) {

                                Product updatedProduct = response.body();

                                viewModel.refreshItems();
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<Product> call, Throwable t) {

                            t.printStackTrace();

                        }
                    });
                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    NavDirections Actions = DetailesFragmentsDirections.actionDetailesFragmentsToHomeFragment();
                    navController.navigate(Actions);
                });

            }
        }




        this.cancelBtn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
            NavDirections Actions = DetailesFragmentsDirections.actionDetailesFragmentsToHomeFragment();
            navController.navigate(Actions);
        });



        return root;
    }
}