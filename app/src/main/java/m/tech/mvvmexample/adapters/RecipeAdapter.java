package m.tech.mvvmexample.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import m.tech.mvvmexample.R;
import m.tech.mvvmexample.models.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int LOADING_TYPE = 1;
    private final static int VIEW_TYPE = 2;

    private List<Recipe> recipes = new ArrayList<>();

    public void setRecipes(List<Recipe> recipes) {
        this.recipes.clear();
        this.recipes.addAll(recipes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == LOADING_TYPE) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new EmptyHolder(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE) {
            ((RecipeHolder) holder).bind(recipes.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (recipes.get(position).getTitle().equals("LOADING...")) {
            return LOADING_TYPE;
        }
        return VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public void showLoading(boolean isOnlyLoading) {
        if (recipes == null) {
            recipes = new ArrayList<>();
        }

        if (isLoading()) return;

        if (isOnlyLoading)
            recipes.clear();

        recipes.add(new Recipe("LOADING..."));
        notifyDataSetChanged();
    }

    public void hideLoading() {
        if (isLoading()) {
            if (recipes.get(recipes.size() - 1).getTitle().equals("LOADING...")) {
                recipes.remove(recipes.size() - 1);
                notifyItemRemoved(recipes.size() - 1);
            }
        }
    }

    public boolean isLoading() {
        if (recipes != null && recipes.size() > 0) {
            return recipes.get(recipes.size() - 1).getTitle().equals("LOADING...");
        }
        return false;
    }

}
