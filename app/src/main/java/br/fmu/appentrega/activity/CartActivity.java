package br.fmu.appentrega.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.fmu.appentrega.adapter.AdaptadorCart;
import br.fmu.appentrega.util.ManagementCart;
import br.fmu.appentrega.R;

public class CartActivity extends AppCompatActivity {

    TextView totalprecoTxt, freteTxt, deliveryTxt, totalTxt, emptyTxt;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagementCart managementCart;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        initList();
        calculateCart();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        LinearLayout homeBtn = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(view ->
                startActivity(new Intent(CartActivity.this, CartActivity.class)));
        homeBtn.setOnClickListener(view ->
                startActivity(new Intent(CartActivity.this, MainActivity.class)));
    }

    private void initView() {
        recyclerViewList = findViewById(R.id.recyclerView);
        totalprecoTxt = findViewById(R.id.totalprecoTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        freteTxt = findViewById(R.id.freteTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView2);
        recyclerViewList = findViewById(R.id.cartView);
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new AdaptadorCart(managementCart.getListCart(), this, this::calculateCart);

        recyclerViewList.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double frete = 10;

        tax = Math.round((managementCart.getTotalPreco() + percentTax) * 100) / 100;
        double total = Math.round((managementCart.getTotalPreco() + tax + frete) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalPreco() * 100) / 100;

        totalprecoTxt.setText("$" + itemTotal);
        deliveryTxt.setText("$" + tax);
        freteTxt.setText("$" + frete);
        totalTxt.setText("$" + total);


    }
}