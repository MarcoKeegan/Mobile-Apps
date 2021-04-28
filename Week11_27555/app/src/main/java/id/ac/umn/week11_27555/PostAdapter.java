package id.ac.umn.week11_27555;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter  extends RecyclerView.Adapter<PostAdapter.MyViewHolder>{
    ArrayList<PostModel> postModels;
    Context context;

    public PostAdapter(ArrayList<PostModel> posts, Context context){
        this.postModels = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.MyViewHolder holder, int position) {
        holder.rvTitle.setText(postModels.get(position).getTitle());
        holder.rvBody.setText(postModels.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rvTitle, rvBody;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            rvTitle = itemView.findViewById(R.id.rvTitle);
            rvBody = itemView.findViewById(R.id.rvBody);
        }
    }
}
