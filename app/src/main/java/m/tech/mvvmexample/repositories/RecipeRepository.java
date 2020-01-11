package m.tech.mvvmexample.repositories;

import androidx.lifecycle.LiveData;

import java.util.List;

import m.tech.mvvmexample.api.RecipeApiClient;
import m.tech.mvvmexample.models.Recipe;
import m.tech.mvvmexample.util.Resource;

public class RecipeRepository {
    private static RecipeRepository instance = null;

    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public void searchRecipes(String query, int page) {
        RecipeApiClient.getInstance().searchRecipes(query, page);
    }

    public LiveData<Resource<List<Recipe>>> getRecipes() {
        return RecipeApiClient.getInstance().getRecipes();
    }
}
