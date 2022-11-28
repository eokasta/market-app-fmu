package br.fmu.appentrega.util;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import br.fmu.appentrega.domain.ComidaDomain;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;


    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(ComidaDomain item) {
        ArrayList<ComidaDomain> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Adicionado a seu carrinho", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ComidaDomain> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<ComidaDomain> listFood, int position, Runnable changeNumberItemsListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.run();
    }

    public void minusNumberFood(ArrayList<ComidaDomain> listfood, int position, Runnable changeNumberItemsListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listfood);
        changeNumberItemsListener.run();
    }

    public Double getTotalPreco() {
        ArrayList<ComidaDomain> listfood = getListCart();
        double preco = 0;
        for (int i = 0; i < listfood.size(); i++) {
            preco = preco + (listfood.get(i).getPreco() * listfood.get(i).getNumberInCart());
        }
        return preco;
    }
}