package com.time.cat.ui.base.baseCard;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class AbsCard extends CardView {
    protected Context mContext;

    public AbsCard(Context context) {
        super(context);
        mContext = context;
    }

    public AbsCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public AbsCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }


}