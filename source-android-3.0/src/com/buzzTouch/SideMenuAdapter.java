package com.comptiaplusnewer;

/**
 * Created by christophercoffee on 12/16/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class SideMenuAdapter extends RecyclerView.Adapter<SideMenuAdapter.MyViewHolder> {

    private List<String> menuList;
    private List<String> imageList;
    private BT_fragment frag;
    private int txtColor;
    private int rowColor;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textView);
            title.setTextColor(txtColor);

            RelativeLayout layout = (RelativeLayout) view.findViewById(R.id.sideListView);
            layout.setBackgroundColor(rowColor);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuList.get(getAdapterPosition());
                    frag.loadScreenWithItemId(menuList.get(getAdapterPosition()));
                }
            });

            image = (ImageView) view.findViewById(R.id.imageView2);



        }
    }


    public SideMenuAdapter(Context context, List<String> menuList, int colorTxt, List<String> images, BT_fragment frag, int rowColor) {
        this.menuList = menuList;
        this.frag = frag;
        imageList = images;
        txtColor = colorTxt;
        this.rowColor = rowColor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.side_list_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(menuList.get(position));
        holder.image.setImageDrawable(BT_fileManager.getDrawableByName(imageList.get(position)));

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }
}

