package com.example.a2018076_tugas4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {
    String data1[], data2[], data3[];
    int images[];
    Context context;

    public MenuAdapter(Context ct, String s1[], String s2[], String s3[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        images = img;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        holder.itemNama.setText(data1[position]);
        holder.itemRating.setText(data2[position]);
        holder.itemImg.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView itemNama, itemRating;
        ImageView itemImg;

        public MenuViewHolder(View itemView) {
            super(itemView);
            itemNama = itemView.findViewById(R.id.itemNama);
            itemRating = itemView.findViewById(R.id.itemRating);
            itemImg = itemView.findViewById(R.id.itemImg);
        }
    }
}

