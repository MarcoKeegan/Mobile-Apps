package id.ac.umn.week11_27555;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Services {
    @GET("posts")
    Call<ArrayList<Model>> getPost();
}
