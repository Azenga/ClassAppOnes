package com.shadow.classappones.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shadow.classappones.R;
import com.shadow.classappones.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private Context context;
    private List<User> userList;

    public UserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_user_item, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = userList.get(position);

        holder.nameTV.setText(user.getName());
        holder.ageTV.setText(String.valueOf(user.getAge()));
        holder.locationTV.setText(user.getLocation());

        holder.mView.setOnClickListener(view -> Toast.makeText(context, "Perform action of " + user.getName(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public static class UserHolder extends RecyclerView.ViewHolder {

        View mView;
        TextView nameTV, ageTV, locationTV;

        public UserHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
            nameTV = itemView.findViewById(R.id.username_tv);
            locationTV = itemView.findViewById(R.id.location_tv);
            ageTV = itemView.findViewById(R.id.age_tv);
        }
    }
}
