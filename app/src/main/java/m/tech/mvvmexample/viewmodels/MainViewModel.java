package m.tech.mvvmexample.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import m.tech.mvvmexample.models.Recipe;
import m.tech.mvvmexample.repositories.RecipeRepository;
import m.tech.mvvmexample.util.Resource;

public class MainViewModel extends ViewModel {

    private RecipeRepository repository;
    private int page;
    private String query;

    public MainViewModel() {
        this.repository = RecipeRepository.getInstance();
    }

    public void searchRecipes(String query, int page) {
        this.query = query;
        if (page == 0)
            page = 1;
        this.page = page;

        repository.searchRecipes(query, page);
    }

    public void searchNextPage() {
        page++;
        searchRecipes(query, page);
    }

    public LiveData<Resource<List<Recipe>>> getRecipes() {
        return repository.getRecipes();
    }

}
