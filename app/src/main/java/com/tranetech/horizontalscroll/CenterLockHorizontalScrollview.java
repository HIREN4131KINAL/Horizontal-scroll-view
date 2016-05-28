package com.tranetech.horizontalscroll;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class CenterLockHorizontalScrollview extends HorizontalScrollView {
    Context context;

    public CenterLockHorizontalScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setSmoothScrollingEnabled(true);

    }

    public void setAdapter(Context context, CustomListAdapter mAdapter) {

        try {
            fillViewWithAdapter(mAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillViewWithAdapter(CustomListAdapter mAdapter)
            throws Exception {
        if (getChildCount() == 0) {
            throw new Exception(
                    "CenterLockHorizontalScrollView must have one child");
        }
        if (getChildCount() == 0 || mAdapter == null)
            return;

        ViewGroup parent = (ViewGroup) getChildAt(0);

        parent.removeAllViews();

        for (int i = 0; i < mAdapter.getCount(); i++) {
            parent.addView(mAdapter.getView(i, null, parent));
        }
    }


}
