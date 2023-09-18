package com.example.pasapplication.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pasapplication.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ProductViewHolder>{

    private final AdapterProduct.ProductAdapterEventListener eventListener;

    private List<Product> productList;


    public AdapterProduct(ProductAdapterEventListener eventListener) {
        this.productList = new ArrayList<>();
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items,parent, false);
        return new AdapterProduct.ProductViewHolder(layout, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = this.productList.get(position);

        holder.setN_Compra(product.getIdCompra());
        holder.setNameServer(product.getNameServe());
        holder.setDescProduct(product.getDescription());

        holder.rootView.setOnClickListener(view -> {
            eventListener.onProductClicked(product.getId());
        });

        holder.rootView.setOnLongClickListener(v -> {
            eventListener.onProductLongClicked(product.getId());
            return true;
        });


    }

    @Override
    public int getItemCount() {
        return this.productList.size();
    }

    public void updateProductList(List<Product> allProduct){
        this.productList = allProduct;
        notifyDataSetChanged();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        private View rootView;

        private TextView n_Compra;

        private TextView nameServer;

        private TextView descProduct;
        private Context context;

        public ProductViewHolder(@NonNull View root, Context context) {
            super(root);
            this.context= context;
            this.rootView = root;
            this.n_Compra = itemView.findViewById(R.id.IdCompraTextView);
            this.nameServer= itemView.findViewById(R.id.NameServerTextView);
            this.descProduct= itemView.findViewById(R.id.descriptionProductTextView);
        }

        public void setN_Compra(String n_Compra) {
            this.n_Compra.setText(n_Compra);
        }

        public void setNameServer(String nameServer) {
            this.nameServer.setText(nameServer);
        }

        public void setDescProduct(String descProduct) {
            this.descProduct.setText(descProduct);
        }
    }

    public interface ProductAdapterEventListener{
        void onProductClicked(int productId);
        void onProductLongClicked(int productId);
    }
}
