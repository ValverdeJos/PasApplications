package com.example.pasapplication.View.Fragments;

import static android.content.ContentValues.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pasapplication.R;
import com.example.pasapplication.ViewModel.AddProductViewModel;
import com.example.pasapplication.ViewModel.ProductViewModel;
import com.example.pasapplication.data.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


public class AddProductFragment extends Fragment {

    private View root;

    private AddProductViewModel viewModel;

    private NavController navController;

    private Button cancelBtn;
    private Button saveBtn;




    private EditText nameServer;
    private EditText nameUserDiscord;
    private EditText idServer;
    private EditText idUserDiscord;
    private EditText mesPremium;

    private String nameServerString,nameUserDiscordString,idServerString,idUserDiscordString,mesPremiumString;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_add_product, container, false);

        navController = NavHostFragment.findNavController(AddProductFragment.this);



        this.viewModel = new ViewModelProvider(this).get(AddProductViewModel.class);

        cancelBtn = root.findViewById(R.id.CancelBtn);
        saveBtn = root.findViewById(R.id.SaveBtn);

        nameServer = root.findViewById(R.id.EditNameServer);
        nameUserDiscord = root.findViewById(R.id.EditUserDiscord);
        idServer = root.findViewById(R.id.EditServerId);
        idUserDiscord = root.findViewById(R.id.EditIdUser);
        mesPremium = root.findViewById(R.id.EditMesPremium);


        int idLength = 6;
        String randomId = generateRandomNumberId(idLength);

        saveBtn.setOnClickListener(v -> {
            this.nameServerString = nameServer.getText().toString();
            this.nameUserDiscordString = nameUserDiscord.getText().toString();
            this.idServerString = idServer.getText().toString();
            this.idUserDiscordString = idUserDiscord.getText().toString();
            this.mesPremiumString = mesPremium.getText().toString();



            String Desc = "VerbosaBot";
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            String createdDate = dateFormat.format(new Date());



            Product newProduct = new Product(
                    0,
                    1,
                    randomId,
                    Desc,
                    createdDate,
                    Integer.parseInt(mesPremiumString),
                    idServerString,
                    nameServerString,
                    idUserDiscordString,
                    nameUserDiscordString,
                    1
            );

            Gson gson = new Gson();
            String json = gson.toJson(newProduct);
            Log.d("Debug", "JSON: " + json);




            viewModel.createProductAndInsert(newProduct, new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        viewModel.refreshProducts();
                    } else {
                        Toast.makeText(getContext(), "Failed to create product. Please try again.", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Failed to create product: " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Toast.makeText(getContext(), "Network error. Please check your connection and try again.", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Network error: " + t.getMessage());
                }
            });


            Toast.makeText(getContext(), "Frame Home", Toast.LENGTH_SHORT).show();
            NavDirections action = AddProductFragmentDirections.actionAddProductFragmentToHomeFragment();
            navController.navigate(action);

        });


        cancelBtn.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Frame Home", Toast.LENGTH_SHORT).show();
            NavDirections action = AddProductFragmentDirections.actionAddProductFragmentToHomeFragment();
            navController.navigate(action);
        });

        return root;
    }

    public static String generateRandomNumberId(int length) {
        String digits = "0123456789";
        StringBuilder randomNumberId = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(digits.length());
            char randomDigit = digits.charAt(randomIndex);
            randomNumberId.append(randomDigit);
        }

        return randomNumberId.toString();
    }

}