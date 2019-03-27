package com.mpapps.myapplication.Activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.mpapps.myapplication.Models.CardColor;
import com.mpapps.myapplication.Models.LightModel;
import com.mpapps.myapplication.R;
import com.mpapps.myapplication.ViewModels.MainActivityVM;

public class LightRecyclerviewAdapter extends RecyclerView.Adapter<LightRecyclerviewAdapter.ViewHolder>
{
    private LayoutInflater mInflater;
    private MainActivityVM viewModel;
    private ItemOnClickListener listener;

    public LightRecyclerviewAdapter(Context ctx, MainActivityVM viewModel)
    {
        this.mInflater = LayoutInflater.from(ctx);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = mInflater.inflate(R.layout.recyclerview_item_light, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i)
    {
        LightModel lightModel = viewModel.getLights().getValue().get(i);
        viewHolder.colorImage.setBackgroundColor(CardColor.valueOf(lightModel.getColor().toUpperCase()).getColor());
        viewHolder.lightName.setText(lightModel.getName());
        viewHolder.lightSwitch.setChecked(lightModel.isState());
    }

    @Override
    public int getItemCount()
    {
        return viewModel.getLights().getValue().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        Switch lightSwitch;
        TextView lightName;
        ImageView colorImage;
        private boolean isTouched = false;

        @SuppressLint("ClickableViewAccessibility")
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cardView = itemView.findViewById(R.id.recyclerview_item_card);
            lightSwitch = itemView.findViewById(R.id.recyclerview_item_state);
            lightName = itemView.findViewById(R.id.recyclerview_item_name);
            colorImage = itemView.findViewById(R.id.recyclerview_item_color_image);

            lightSwitch.setOnTouchListener((v, event) ->
            {
                isTouched = true;
                return false;
            });
            lightSwitch.setOnCheckedChangeListener((buttonView, isChecked) ->
            {
                if (isTouched) {
                    isTouched = false;
                    LightModel model = viewModel.getLights().getValue().get(getAdapterPosition());
                    viewModel.changeLightState(model.getId(), isChecked);
                }
            });

            cardView.setOnClickListener(view -> {
                listener.onItemClick();
            });
        }
    }

    public void setListener(ItemOnClickListener listener)
    {
        this.listener = listener;
    }

    interface ItemOnClickListener{
        void onItemClick();
    }
}
