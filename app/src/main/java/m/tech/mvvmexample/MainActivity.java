package m.tech.mvvmexample;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import m.tech.mvvmexample.adapters.RecipeAdapter;
import m.tech.mvvmexample.models.Recipe;
import m.tech.mvvmexample.util.Resource;
import m.tech.mvvmexample.viewmodels.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private MainViewModel viewModel;
    private RecyclerView rvFood;
    private RecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initRecyclerView();
        initSearchView();

        subscribeObserver();
    }

    private void initSearchView() {
        SearchView searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.showLoading(true);
                viewModel.searchRecipes(s, 1);
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    private void initRecyclerView() {
        rvFood = findViewById(R.id.rv_food);
        rvFood.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecipeAdapter();
        rvFood.setAdapter(adapter);

        //load more
        rvFood.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(!recyclerView.canScrollVertically(1)){
                    viewModel.searchNextPage();
                }
            }
        });
    }

    private void subscribeObserver() {
        viewModel.getRecipes().observe(this, new Observer<Resource<List<Recipe>>>() {
            @Override
            public void onChanged(Resource<List<Recipe>> listResource) {
                switch (listResource.status) {
                    case LOADING:
                        Log.d(TAG, "onChanged: loading...");
                        adapter.showLoading(false);
                        break;
                    case SUCCESS:
                        Log.d(TAG, "onChanged: success...");
                        adapter.hideLoading();
                        adapter.setRecipes(listResource.data);

                        break;
                    case ERROR:
                        Log.d(TAG, "onChanged: error..." + listResource.message);
                        adapter.hideLoading();
                        Toast.makeText(MainActivity.this, listResource.message, Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}
