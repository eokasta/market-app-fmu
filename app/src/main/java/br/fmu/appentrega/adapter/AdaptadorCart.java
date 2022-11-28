package br.fmu.appentrega.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.fmu.appentrega.R;
import br.fmu.appentrega.domain.ComidaDomain;
import br.fmu.appentrega.util.ManagementCart;

public class AdaptadorCart extends RecyclerView.Adapter<AdaptadorCart.ViewHolder> {
    private ArrayList<ComidaDomain> comidaDomains;
    private ManagementCart managementCart;
    private Runnable changeNumberItemsListener;

    public AdaptadorCart(ArrayList<ComidaDomain> comidaDomains, Context context, Runnable changeNumberItemsListener) {
        this.comidaDomains = comidaDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCart.ViewHolder holder, int position) {
        holder.title.setText(comidaDomains.get(position).getTitle());
        holder.precoEachItem.setText(String.valueOf(comidaDomains.get(position).getPreco()));
        int numberInCart = comidaDomains.get(position).getNumberInCart();
        Double preco = comidaDomains.get(position).getPreco();
        String totalEachPriceText = String.valueOf(numberInCart * preco);
        System.out.println("preco = " + preco);
        System.out.println("numberInCart = " + numberInCart);
        System.out.println("totalEachPriceText = " + totalEachPriceText);
        holder.totalEachItem.setText(totalEachPriceText);
        holder.num.setText(String.valueOf(comidaDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(comidaDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener(view ->
                managementCart.plusNumberFood(comidaDomains, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.run();
                }));

        holder.minusItem.setOnClickListener(view ->
                managementCart.minusNumberFood(comidaDomains, position, () -> {
                    notifyDataSetChanged();
                    changeNumberItemsListener.run();
                }));
    }

    @Override
    public int getItemCount() {
        return comidaDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, precoEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleCart);
            precoEachItem = itemView.findViewById(R.id.precoEachItem);
            pic = itemView.findViewById(R.id.picCart);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
