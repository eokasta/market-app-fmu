package br.fmu.appentrega.Adaptador;

import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.fmu.appentrega.Activity.ShowDetailActivity;
import br.fmu.appentrega.Domain.ComidaDomain;
import br.fmu.appentrega.R;

public class AdaptadorPopular extends RecyclerView.Adapter<AdaptadorPopular.ViewHolder> {
    ArrayList<ComidaDomain> popularComida;

    public AdaptadorPopular(ArrayList<ComidaDomain> categoryDomains) {
        this.popularComida = categoryDomains;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate= LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorPopular.ViewHolder holder, int position) {
        holder.titulo.setText(popularComida.get(position).getTitle());
        holder.preco.setText(String.valueOf(popularComida.get(position).getPreco()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(popularComida.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object", popularComida.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularComida.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, preco;
        ImageView pic;
        TextView addBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.titulo);
            preco=itemView.findViewById(R.id.preco);
            pic=itemView.findViewById(R.id.pic);
            addBtn=itemView.findViewById(R.id.addBtn);
        }
    }
}
