package m.tech.mvvmexample.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import m.tech.mvvmexample.api.responses.SearchRecipeResponse;
import m.tech.mvvmexample.models.Recipe;
import m.tech.mvvmexample.util.Resource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeApiClient {
    private static RecipeApiClient instance;

    private MutableLiveData<Resource<List<Recipe>>> recipes = new MutableLiveData<>();
    private List<Recipe> oldQueryResults;

    public static RecipeApiClient getInstance() {
        if (instance == null) {
            instance = new RecipeApiClient();
        }
        return instance;
    }

    public void searchRecipes(String query, int page) {
        recipes.setValue(Resource.loading(null));

        ServiceGenerator.getRecipeApi().searchRecipe(query, String.valueOf(page)).enqueue(new Callback<SearchRecipeResponse>() {
            @Override
            public void onResponse(Call<SearchRecipeResponse> call, Response<SearchRecipeResponse> response) {
                if (response.isSuccessful()) {
                    if (page == 1) {
                        recipes.setValue(Resource.success(response.body().getRecipes()));
                        oldQueryResults = response.body().getRecipes();
                    } else {
                        oldQueryResults.addAll(response.body().getRecipes());
                        recipes.setValue(Resource.success(oldQueryResults));
                    }
                } else {
                    recipes.setValue(Resource.error(null, response.message()));
                }
            }

            @Override
            public void onFailure(Call<SearchRecipeResponse> call, Throwable t) {
                recipes.setValue(Resource.error(null, t.getMessage()));
            }
        });
    }

    public LiveData<Resource<List<Recipe>>> getRecipes() {
        return recipes;
    }
}













