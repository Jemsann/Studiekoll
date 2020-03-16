package com.example.studiekoll;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<CourseClass> mExampleList;
    private Adapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onAddClick(int position);
        void onRemoveClick(int positon);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
    public ImageView mImageView;
    public TextView mTextView1;
    public TextView mTextView2;
    public ImageView mAddImage;
    public ImageView mRemoveImage;

        //constructor
        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.imageView);
            mTextView1=itemView.findViewById(R.id.textView);
            mTextView2=itemView.findViewById(R.id.textView2);
            mAddImage = itemView.findViewById(R.id.image_add);
            mRemoveImage = itemView.findViewById(R.id.image_remove);

            mAddImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onAddClick(position);
                        }
                    }
                }
            });

            mRemoveImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onRemoveClick(position);
                        }
                    }
                }
            });
        }
    }

    public Adapter(ArrayList<CourseClass> exampleList){
        mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(v,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CourseClass currentCourseClass = mExampleList.get(position);

        holder.mImageView.setImageResource(currentCourseClass.getImageResource());
        holder.mTextView1.setText(currentCourseClass.getTitle());
        holder.mTextView2.setText(currentCourseClass.getHours().toString());
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
