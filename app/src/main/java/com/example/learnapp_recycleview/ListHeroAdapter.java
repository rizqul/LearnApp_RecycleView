package com.example.learnapp_recycleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListHeroAdapter extends RecyclerView.Adapter<ListHeroAdapter.ViewHolder>{
    private List<HeroModel> models;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public ListHeroAdapter(List<HeroModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HeroModel model = models.get(position);

        holder.ivHeroPhoto.setBackgroundResource(model.getPhoto());
        holder.tvHeroName.setText(model.getName());
        holder.tvHeroSummary.setText(model.getSummary());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivHeroPhoto;
        TextView tvHeroName;
        TextView tvHeroSummary;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHeroPhoto = itemView.findViewById(R.id.iv_hero_photo);
            tvHeroName = itemView.findViewById(R.id.tv_hero_name);
            tvHeroSummary = itemView.findViewById(R.id.tv_hero_summary);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
