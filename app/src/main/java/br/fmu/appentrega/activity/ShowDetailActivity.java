package br.fmu.appentrega.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import br.fmu.appentrega.domain.ComidaDomain;
import br.fmu.appentrega.util.ManagementCart;
import br.fmu.appentrega.R;

public class ShowDetailActivity extends AppCompatActivity {
    int numberOrder = 1;
    private TextView addtoCartBtn;
    private TextView titleTxt, precoTxt, descriptionTxt, numberOrdertxt;
    private ImageView plusBtn, minusBtn, picFood;
    private ComidaDomain object;
    private ManagementCart managementCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (ComidaDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this).load(drawableResourceId).into(picFood);

        titleTxt.setText(object.getTitle());
        precoTxt.setText("$" + object.getPreco());
        descriptionTxt.setText(object.getDescription());
        numberOrdertxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(view -> {
            numberOrder = numberOrder + 1;
            numberOrdertxt.setText(String.valueOf(numberOrder));
        });

        minusBtn.setOnClickListener(view -> {
            if (numberOrder > 1) {
                numberOrder = numberOrder - 1;
            }
            numberOrdertxt.setText(String.valueOf(numberOrder));
        });

        addtoCartBtn.setOnClickListener(view -> {
            object.setNumberInCart(numberOrder);
            managementCart.insertFood(object);
        });
    }

    private void initView() {
        addtoCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        precoTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrdertxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picfood);
    }
}