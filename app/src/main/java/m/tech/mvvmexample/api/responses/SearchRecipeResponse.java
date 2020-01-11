package m.tech.mvvmexample.api.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import m.tech.mvvmexample.models.Recipe;

public class SearchRecipeResponse {

    @SerializedName("recipes")
    List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
