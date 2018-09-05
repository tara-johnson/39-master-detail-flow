package com.example.mapstaskapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.mapstaskapp.Models.Errand;

import java.util.ArrayList;
import java.util.List;

class ErrandAdapter extends RecyclerView.Adapter<ErrandAdapter.MyViewHolder> {
    public List<Errand> errands;
    private boolean isTwoPane;
    private FragmentManager fragmentManager;

    public ErrandAdapter(boolean isTwoPane, FragmentManager fragmentManager) {
        errands = new ArrayList<>();
        this.isTwoPane = isTwoPane;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.errand_item, parent, false);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.bind(errands.get(i));
    }

    @Override
    public int getItemCount() {
        return errands.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        View mView;
        TextView description;
        Errand errand;
        CheckBox checkbox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;
            mView.setOnClickListener(this);

            description = itemView.findViewById(R.id.description);
            checkbox = itemView.findViewById(R.id.isComplete);
        }

        public void bind(Errand errand) {
            this.errand = errand;
            description.setText(errand.description);
            checkbox.setChecked(errand.isComplete);
        }

        @Override
        public void onClick(View v) {
            if (isTwoPane) {
                Fragment fragment = new MapsFragment();

                Bundle bundle = new Bundle();
                bundle.putString("key", errand.id);
                fragment.setArguments(bundle);

                fragmentManager.beginTransaction()
                        .replace(R.id.detail_fragment_container, fragment)
                        .commit();

            } else {
                Intent intent = new Intent(mView.getContext(), MapsActivity.class);
                intent.putExtra("key", errand.id);
                mView.getContext().startActivity(intent);
            }
        }
    }
}
