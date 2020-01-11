package m.tech.mvvmexample.models;

import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("title")
    private String title;

    @SerializedName("publisher")
    private String publisher;

    @SerializedName("recipe_id")
    private String recipeId;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("social_rank")
    private float socialRank;

    public String getTitle() {
        return title;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getSocialRank() {
        return socialRank;
    }
}
