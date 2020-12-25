package com.example.foodapp;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    FoodListingAdapter adapter;
    ArrayList<FoodDonationHelperClass> filterList; //shifting F6

    public CustomFilter(ArrayList<FoodDonationHelperClass> filterList, FoodListingAdapter adapter) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //check constraint validity
        if (constraint != null && constraint.length()>0){
            //change  to uppercase
            constraint = constraint.toString().toUpperCase();
            //store our filtered models
            ArrayList<FoodDonationHelperClass> filteredModels = new ArrayList<>();
            for (int i = 0; i< filterList.size(); i++){
                //check
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)){
                    //add Model to filtered models
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.Donations = (ArrayList<FoodDonationHelperClass>) results.values;
        //refresh
        adapter.notifyDataSetChanged();
    }
}
