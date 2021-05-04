package com.example.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.foodapp.R;
import com.example.foodapp.models.Main_slider;

import java.util.ArrayList;

public class Main_slider_adapter extends PagerAdapter {
    private Context context;
    private final ArrayList<Main_slider> main_sliderArrayList;

    public Main_slider_adapter(Context context, ArrayList<Main_slider> main_sliderArrayList) {
        this.context = context;
        this.main_sliderArrayList = main_sliderArrayList;
    }


    @Override
    public int getCount() {
        return main_sliderArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //inflate
        View view = LayoutInflater.from(context).inflate(R.layout.main_card_item,container,false);
        ImageView slider_home_fragment = view.findViewById(R.id.slider_home_fragment);
        //TextView slider_title = view.findViewById(R.id.slider_title);
        TextView slider_desc = view.findViewById(R.id.slider_desc);

        Main_slider main_slider = main_sliderArrayList.get(position);
        String title = main_slider.getTitle();
        String description = main_slider.getDescription();
        int image = main_slider.getImage();

        slider_home_fragment.setImageResource(image);
        //slider_title.setText(title);
        slider_desc.setText(description);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do something when press card
                Toast.makeText(context,title,Toast.LENGTH_SHORT).show();

            }
        });

        //add view to the container
        container.addView(view,position);



        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }
}
