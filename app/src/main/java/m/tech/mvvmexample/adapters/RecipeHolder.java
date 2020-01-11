package m.tech.mvvmexample.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import m.tech.mvvmexample.R;
import m.tech.mvvmexample.models.Recipe;

public class RecipeHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvPublisher;
        private TextView tvSocialRank;
        private ImageView ivThumbnail;

        public RecipeHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvPublisher = itemView.findViewById(R.id.tv_publisher);
            tvSocialRank = itemView.findViewById(R.id.tv_score);
            ivThumbnail = itemView.findViewById(R.id.iv_thumbnail);
        }

        public void bind(Recipe recipe) {
            tvTitle.setText(recipe.getTitle());
            tvSocialRank.setText((int) recipe.getSocialRank() + "");
            tvPublisher.setText(recipe.getPublisher());
            Glide.with(itemView).load(recipe.getImageUrl()).into(ivThumbnail);
        }
    }