package com.example.criminalintent.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalintent.R;
import com.example.criminalintent.models.Crime;
import com.example.criminalintent.databinding.ListItemCrimeBinding;
import com.example.criminalintent.views.CrimeFragment;
import com.example.criminalintent.views.CrimeListFragment;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    List<Crime> crimes;
    Fragment fragment;
    CrimeListFragment.Callbacks callbacks;

    public CrimeAdapter(List<Crime> crimeList, Fragment fragment, CrimeListFragment.Callbacks callbacks) {
        this.crimes = crimeList;
        this.fragment = fragment;
        this.callbacks = callbacks;
    }

    /**
     * Bindingi - xml in bağlandığı yer.
     */
    @NonNull
    @Override
    public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemCrimeBinding listItemCrimeBinding = ListItemCrimeBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent,false);
        return new CrimeHolder(listItemCrimeBinding);
    }

    /**
     * Layout içerisinde hangi veriler görünecek.
     */
    @Override
    public void onBindViewHolder(@NonNull CrimeHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.crimeDate.setText(crimes.get(position).getDate().toString());
        holder.binding.crimeTitle.setText(crimes.get(position).getTitle());
        if (crimes.get(position).isSolved()) {
            holder.binding.crimeSolved.setVisibility(View.VISIBLE);
        } else {
            holder.binding.crimeSolved.setVisibility(View.INVISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.onCrimeSelected(crimes.get(position).getId());
            }
        });
    }

    // List xml kaç elemanlı olacak.
    @Override
    public int getItemCount() {
        return crimes.size();
    }

    /**
     * Parametre olarak gelen görünümü tutar.
     */
    public class CrimeHolder extends RecyclerView.ViewHolder{

        ListItemCrimeBinding binding;

        public CrimeHolder(@NonNull ListItemCrimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
