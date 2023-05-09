package com.example.pr5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.pr5.Models.Words;

import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<WordsAdapter.ViewHolder>{

    public OnNoteListener mOnNoteListener;
    public interface OnNoteListener {
        void onNoteClick(int position);
    }

    public final LayoutInflater inflater;
    public static List<Words> words;
    public static final String TAG = "MyApp";

    public WordsAdapter(Context context, List<Words> words, OnNoteListener mOnNoteListener) {
        this.words = words;
        this.mOnNoteListener = mOnNoteListener;
        this.inflater = LayoutInflater.from(context);
    }
    public static void updateWordsList(final List<Words> words2) {
        words = words2;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view, mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Words W = words.get(position);
        holder.imgView.setImageResource(W.getImg());
        holder.themeView.setText(W.getTheme());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView imgView;
        final TextView themeView;
        OnNoteListener onNoteListener;
        ViewHolder(View view, OnNoteListener onNoteListener){
            super(view);
            imgView = view.findViewById(R.id.img);
            themeView = view.findViewById(R.id.theme);
            this.onNoteListener = onNoteListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
}
