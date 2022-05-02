package com.example.criminalintent.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.criminalintent.models.Crime;
import com.example.criminalintent.databinding.ListItemCrimeBinding;

import java.util.List;

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {

    List<Crime> crimes;

    public CrimeAdapter(List<Crime> crimeList) {
        this.crimes = crimeList;
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

        holder.itemView.setOnClickListener(view ->
                Toast.makeText(holder.itemView.getContext(),
                        crimes.get(position).getTitle(),
                        Toast.LENGTH_LONG)
                        .show());
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
