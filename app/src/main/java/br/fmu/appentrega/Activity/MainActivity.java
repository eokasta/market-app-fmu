package br.fmu.appentrega.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import br.fmu.appentrega.Adaptador.AdaptadorCategoria;
import br.fmu.appentrega.Adaptador.AdaptadorPopular;
import br.fmu.appentrega.Domain.CategoryDomain;
import br.fmu.appentrega.Domain.ComidaDomain;
import br.fmu.appentrega.R;

public class MainActivity extends AppCompatActivity {
private RecyclerView.Adapter adapter, adapter2;
private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }
    private void bottomNavigation(){
        FloatingActionButton floatingActionButton=findViewById(R.id.cartBtn);
        LinearLayout homeBtn=findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }
    private void recyclerViewCategory(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList=findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoryDomain> category=new ArrayList<>();
        category.add(new CategoryDomain("Pizza", "cat_1"));
        category.add(new CategoryDomain("Lanches", "cat_2"));
        category.add(new CategoryDomain("Hotdog", "cat_3"));
        category.add(new CategoryDomain("Bebida", "cat_4"));
        category.add(new CategoryDomain("Donut", "cat_5"));

        adapter = new AdaptadorCategoria(category);
        recyclerViewCategoryList.setAdapter(adapter);
    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<ComidaDomain> comidaList=new ArrayList<>();
        comidaList.add(new ComidaDomain("Pizza de Pepperoni", "pizza1", "fatias de pepperoni, muçarela, orégano, molho de tomate", 9.00));
        comidaList.add(new ComidaDomain("X Burguer", "burger", "Carne, queijo Gouda, Molho especial, alface, tomate", 8.50));
        comidaList.add(new ComidaDomain("Pizza vegetariana", "pizza2","Óleo de oliva, azeitonas pretas, tomate cereja, orégano, manjericão", 8.50));

        adapter2 = new AdaptadorPopular(comidaList);
        recyclerViewPopularList.setAdapter(adapter2);
    }
}