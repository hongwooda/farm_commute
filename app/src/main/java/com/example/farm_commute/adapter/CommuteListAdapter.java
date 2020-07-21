package com.example.farm_commute.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.farm_commute.databinding.ItemWeeklistBinding;
import com.example.farm_commute.model.UserDataListResponse;

import java.util.ArrayList;

/**
 * Developer: Minseo
 * Generated date: 2019-09-09
 * Generated time: 오전 1:43
 */
public class CommuteListAdapter extends RecyclerView.Adapter<CommuteListAdapter.ItemCommuteListViewHolder> {

    private Context mContext;

    private ArrayList<UserDataListResponse.Result> mUserDataList;


    public CommuteListAdapter(Context context, ArrayList<UserDataListResponse.Result> UserDataList) {
        mContext = context;
        mUserDataList = UserDataList;
    }

    @NonNull
    @Override
    public ItemCommuteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemCommuteListViewHolder(ItemWeeklistBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCommuteListViewHolder holder, int position) {


      //  holder.ItemWeeklistBinding.textViewItemReactionListUserName.setText(mArrayList.get(position).getUserName());


        holder.ItemWeeklistBinding.dayTextview.setText((mUserDataList.get(position).date).substring(2));
        holder.ItemWeeklistBinding.onWorkTimeTextview.setText(mUserDataList.get(position).onworkTime);
        holder.ItemWeeklistBinding.offWorkTimeTextview.setText(mUserDataList.get(position).offworkTime);

        if(mUserDataList.get(position).holidayYn.equals("Y"))
        {
            holder.ItemWeeklistBinding.hoildayCheckbox.setChecked(true);
        }else if(mUserDataList.get(position).holidayYn.equals("N"))
        {
            holder.ItemWeeklistBinding.hoildayCheckbox.setChecked(false);
        }

        holder.ItemWeeklistBinding.workingTimeTextview.setText(mUserDataList.get(position).total);



    }

    @Override
    public int getItemCount() {
        return mUserDataList.size();
    }

    class ItemCommuteListViewHolder extends RecyclerView.ViewHolder {

        ItemWeeklistBinding ItemWeeklistBinding;


        public ItemCommuteListViewHolder(@NonNull ItemWeeklistBinding ItemWeeklistBinding) {
            super(ItemWeeklistBinding.getRoot());
            this.ItemWeeklistBinding = ItemWeeklistBinding;
        }
    }
}
