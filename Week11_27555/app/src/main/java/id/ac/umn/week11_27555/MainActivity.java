package id.ac.umn.week11_27555;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    RecyclerView rv;
    ArrayList<PostModel> arraymodel = new ArrayList<>();
    PostAdapter adapter;
    InterfaceApi mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPost();
        recycler();
    }

    private void getPost(){
        mApiService = UtilsApi.getApiSerivce();

        mApiService.getPost().enqueue(new Callback<ArrayList<PostModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PostModel>> call, Response<ArrayList<PostModel>> response) {
                if (response.isSuccessful()) {
                    try {
                        if (response == null) {
                            Toast.makeText(MainActivity.this, "Data Empty", Toast.LENGTH_LONG).show();
                        } else {
                            PostModel model = new PostModel();
                            List<PostModel> models = response.body();
                            arraymodel.addAll(models);
                            adapter.notifyDataSetChanged();
                        }
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Error : " + "\n" + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<PostModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagal Menghubungi Server :( ", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void recycler() {
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new PostAdapter(arraymodel, MainActivity.this);
        rv.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.invalidate();
    }
}