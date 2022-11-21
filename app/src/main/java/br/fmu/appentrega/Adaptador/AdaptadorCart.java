package br.fmu.appentrega.Adaptador;

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

import br.fmu.appentrega.Domain.ComidaDomain;
import br.fmu.appentrega.Helper.ManagementCart;
import br.fmu.appentrega.Interface.ChangeNumberItemsListener;
import br.fmu.appentrega.R;

public class AdaptadorCart extends RecyclerView.Adapter<AdaptadorCart.ViewHolder> {
    private ArrayList<ComidaDomain> comidaDomains;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public AdaptadorCart(ArrayList<ComidaDomain> comidaDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.comidaDomains = comidaDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCart.ViewHolder holder, int position) {
    holder.title.setText(comidaDomains.get(position).getTitle());
    holder.precoEachItem.setText(String.valueOf(comidaDomains.get(position).getPreco()));
    holder.totalEachItem.setText(String.valueOf(Math.round((comidaDomains.get(position).getNumberInCart()*comidaDomains.get(position).getPreco())*100)/100));
    holder.num.setText(String.valueOf(comidaDomains.get(position).getNumberInCart()));

    int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(comidaDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(comidaDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(comidaDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return comidaDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, precoEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleTxt);
            precoEachItem=itemView.findViewById(R.id.precoEachItem);
            pic=itemView.findViewById(R.id.picCart);
            totalEachItem=itemView.findViewById(R.id.totalEachItem);
            num=itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCartBtn);
            minusItem=itemView.findViewById(R.id.minusCartBtn);
        }
    }
}
