package com.buzzTouch;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by christophercoffee on 7/26/15.
 */
public class CM_sideMenu {

    BT_application app = new BT_application();
    BT_item theScreenData;
    List<String> titles;
    List<String> screenIds;
    List<String> listImages;
    View view;
    Context context;
    BT_fragment frag;
    FragmentManager fm;
    ViewGroup vg;
    LayoutInflater layoutInflater;
    String itemId;
    int textcolor;
    boolean isDrawerOpen = false;
    private RecyclerView recyclerView;

    
    final CustomDrawerLayout drawer;
    ;

    public CM_sideMenu(String screenId, Context theContext, View view1, BT_fragment frag1) {
        
        view = view1;
        layoutInflater = (LayoutInflater) theContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context = theContext;
        frag = frag1;
        itemId = screenId;

        drawer = new CustomDrawerLayout(context);

    }

  

    public void setView(View thisScreensView) {
        view = thisScreensView;
    }

    class CustomDrawerLayout extends DrawerLayout {

        public CustomDrawerLayout(Context context) {
            super(context);
        }

        public CustomDrawerLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public CustomDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(
                    MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(
                    MeasureSpec.getSize(heightMeasureSpec), MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void sidebarAction()
    {
        if(isDrawerOpen)
           {
               drawer.closeDrawer(GravityCompat.START);
            //   System.out.println("close drawer");
               isDrawerOpen = false;
          }
         else if(!isDrawerOpen) {
            drawer.openDrawer(GravityCompat.START);
          //  System.out.println("open drawer");
            isDrawerOpen = true;
        }
    }

    public void openSidebar()
    {
        if(!isDrawerOpen) {
            drawer.openDrawer(GravityCompat.START);
            isDrawerOpen = true;
        }

    }

    public void closeSidebar()
    {
        if(isDrawerOpen) {
            drawer.closeDrawer(GravityCompat.START);
            isDrawerOpen = false;
        }
    }

    public View init()
    {

        final FrameLayout fl = new FrameLayout(context);

        recyclerView = new RecyclerView(context);

        theScreenData = comptiaplusnewer_appDelegate.rootApp.getScreenDataByItemId(itemId);

        String navWidthString = BT_strings.getJsonPropertyValue(theScreenData.getJsonObject(), "navWidth", "400");
        int navWidth = Integer.parseInt(navWidthString);

        DrawerLayout.LayoutParams lp = new DrawerLayout.LayoutParams(
                navWidth , RelativeLayout.LayoutParams.MATCH_PARENT);

        lp.gravity=Gravity.START;
       

        recyclerView.setLayoutParams(lp);
        fl.addView(view);

        drawer.addView(fl, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        drawer.addView(recyclerView);


        screenIds = new ArrayList<>();
        titles = new ArrayList<>();
        listImages = new ArrayList<>();

        String listTxtColor = BT_strings.getJsonPropertyValue(theScreenData.getJsonObject(), "listTxtColor", "#FFFFFF");
        String listDividerColor = BT_strings.getJsonPropertyValue(theScreenData.getJsonObject(), "listDividerColor", "#000000");
        String listBackgroundColor = BT_strings.getJsonPropertyValue(theScreenData.getJsonObject(), "listBackgroundColor", "#FF0066");
        String rowBackgroundColor = BT_strings.getJsonPropertyValue(theScreenData.getJsonObject(), "rowBackgroundColor", "#FF0066");
        int listBackColor = BT_color.getColorFromHexString(listBackgroundColor);

        ColorDrawable dividerColor = new ColorDrawable(BT_color.getColorFromHexString(listDividerColor));
        textcolor = BT_color.getColorFromHexString(listTxtColor);
        int rowColor = Color.parseColor(rowBackgroundColor);

        try{
            JSONArray items = null;

            if(theScreenData.getJsonObject().has("childItems")){
                items =  theScreenData.getJsonObject().getJSONArray("childItems");
            }
            for (int i = 0; i < items.length(); i++){

                JSONObject tmpJson = items.getJSONObject(i);

                String id = tmpJson.getString("loadScreenWithItemId");
                String title = tmpJson.getString("titleText");
                String imageName = tmpJson.getString("imageName");


                listImages.add(imageName);
                screenIds.add(id);
                titles.add(title);


            }//for
        }catch(Exception e){
            BT_debugger.showIt(":parseScreenData EXCEPTION " + e.toString());
        }


        SideMenuAdapter adapter = new SideMenuAdapter(context,titles,textcolor,listImages,frag, rowColor);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setBackgroundColor(listBackColor);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(dividerColor));
        recyclerView.setAdapter(adapter);

        return drawer;
    }






    private class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
        private Drawable mDivider;

        public SimpleDividerItemDecoration(ColorDrawable dividerColor) {

            mDivider = dividerColor;

        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);

                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDivider.getIntrinsicHeight();

                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }
    }




}
