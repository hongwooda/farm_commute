package com.example.farm_commute.custom;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.farm_commute.R;
import com.example.farm_commute.common.OnSingleClickListener;
import com.example.farm_commute.utils.GLUtils;


public class CustomActionBarView extends FrameLayout {

    Mapper mMapper;

    private TitleEventListener mTitleEventListener = null;
    private LeftEventListener mLeftEventListener = null;
    private RightEventListener mRightEventListener = null;
    private RightEventListener2 mRightEventListener2 = null;

    static CustomActionBarView mCustomActionBarView;

    public CustomActionBarView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public static View getInstance(Context context) {
        return mCustomActionBarView = new CustomActionBarView(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.view_custom_actionbar, this);
        mMapper = new Mapper(this);
    }

    @Deprecated
    public void setTextHeaderType() {
//        mMapper.textHeaderView.setVisibility(View.VISIBLE);
//        mMapper.imageHeaderView.setVisibility(View.GONE);
    }

    @Deprecated
    public void setImageHeaderType() {
//        mMapper.textHeaderView.setVisibility(View.GONE);
//        mMapper.imageHeaderView.setVisibility(View.VISIBLE);
    }

    public void setTitleLayoutColor(int color) {
        mMapper.constraintLayout.setBackgroundColor(color);
    }

    public void setTitleHeader(String text) {
        mMapper.titleTextView.setVisibility(View.VISIBLE);
        mMapper.titleTextView.setText(text);
    }

    public void setTitleDrawable(@DrawableRes int left, @DrawableRes int top, @DrawableRes int right, @DrawableRes int bottom) {
        mMapper.titleTextView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        mMapper.titleTextView.setCompoundDrawablePadding(GLUtils.convertDpToPixels(6));
    }

    public void clearTitleDrawable() {
        mMapper.titleTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
    }

    public void setOnTitleClickEvent(TitleEventListener titleClickEventListener) {
        mTitleEventListener = titleClickEventListener;

        mMapper.titleTextView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                mTitleEventListener.onTitleClickEvent();
            }
        });
    }

    public void setLeftText(String text) {
        mMapper.leftTextView.setVisibility(VISIBLE);
        mMapper.leftTextView.setText(text);
    }

    public void setRightText(String text) {
        mMapper.rightTextView.setVisibility(VISIBLE);
        mMapper.rightTextView.setText(text);
    }

    public void setLeftTextColor(int cid) {
        mMapper.leftTextView.setVisibility(VISIBLE);
        mMapper.leftTextView.setTextColor(cid);
    }

    public void setRightTextColor(int cid) {
        mMapper.rightTextView.setVisibility(VISIBLE);
        mMapper.rightTextView.setTextColor(cid);
    }

    public void setLeftImage(int rid) {
        mMapper.leftImageView.setVisibility(VISIBLE);
        mMapper.leftImageView.setImageResource(rid);
    }

    public void setRightImage(int rid) {
        mMapper.rightImageView.setVisibility(VISIBLE);
        mMapper.rightImageView.setImageResource(rid);
    }

    public void setRightImage2(int rid) {
        mMapper.rightImageView2.setVisibility(VISIBLE);
        mMapper.rightImageView2.setImageResource(rid);
    }

    public void setRightImageVisibility(boolean mBoolean) {
        if (mBoolean) {
            mMapper.rightImageView.setVisibility(VISIBLE);
        } else {
            mMapper.rightImageView.setVisibility(GONE);
        }
    }

    public void setBackGroundVisible(boolean isVisibleYn) {
        if (isVisibleYn) {
            mMapper.backgroundView.setVisibility(VISIBLE);
            mMapper.backgroundView.setOnClickListener(new OnSingleClickListener() {
                @Override
                public void onSingleClick(View v) {

                }
            });
        } else {
            mMapper.backgroundView.setVisibility(GONE);
        }
    }

    @Deprecated
    public void setHeaderTextType() {
    }

    public void setOnLeftButtonEvent(LeftEventListener listener) {
        mLeftEventListener = listener;

        mMapper.leftButton.setOnClickListener(new OnSingleClickListener() {
            public void onSingleClick(View v) {
                mLeftEventListener.onLeftButtonEvent();

            }
        });

        mMapper.leftTextView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                mMapper.leftButton.callOnClick();
            }
        });

        mMapper.leftImageView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                mMapper.leftButton.callOnClick();
            }
        });
    }

    public void setOnRightButtonEvent(RightEventListener listener) {
        mRightEventListener = listener;

        mMapper.rightButton.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                mRightEventListener.onRightButtonEvent();
            }
        });

        mMapper.rightTextView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                mMapper.rightButton.callOnClick();
            }
        });

        mMapper.rightImageView.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                mMapper.rightButton.callOnClick();
            }
        });
    }

    public void setOnRightButtonEvent2(RightEventListener2 listener) {
        mRightEventListener2 = listener;

        mMapper.rightButton2.setOnClickListener(new OnSingleClickListener(){
            @Override
            public void onSingleClick(View v) {
                mRightEventListener2.onRightButtonEvent2();
            }
        });

        mMapper.rightImageView2.setOnClickListener(new OnSingleClickListener(){
            @Override
            public void onSingleClick(View v) {
                mMapper.rightButton2.callOnClick();
            }
        });

    }

    public interface TitleEventListener {
        void onTitleClickEvent();
    }

    public interface LeftEventListener {
        void onLeftButtonEvent();
    }

    public interface RightEventListener {
        void onRightButtonEvent();
    }

    public interface RightEventListener2 {
        void onRightButtonEvent2();
    }


    /**
     * 오타 수정
     *
     * @author Minseo Parker
     * <p>
     * ImageView rifhgtImageView, rifhgtImageView2;
     * Button legtButton;
     * <p>
     * rifhgtImageView = viewGroup.findViewById(R.id.imageView_right);
     * rifhgtImageView2 = viewGroup.findViewById(R.id.imageView_right2);
     * <p>
     * legtButton = viewGroup.findViewById(R.id.button_left);
     */
    class Mapper {

        TextView testTextView, titleTextView;
        ImageView leftImageView, rightImageView, rightImageView2;
        Button leftButton, rightButton, rightButton2;
        TextView leftTextView, rightTextView;
        View backgroundView;
        ConstraintLayout constraintLayout;
//        ViewGroup textHeaderView, imageHeaderView;

        public Mapper(ViewGroup viewGroup) {
            constraintLayout = viewGroup.findViewById(R.id.title_layout);
            leftImageView = viewGroup.findViewById(R.id.imageView_left);
            rightImageView = viewGroup.findViewById(R.id.imageView_right);
            rightImageView2 = viewGroup.findViewById(R.id.imageView_right2);
            leftButton = viewGroup.findViewById(R.id.button_left);
            rightButton = viewGroup.findViewById(R.id.button_right);
            rightButton2 = viewGroup.findViewById(R.id.button_right2);
            leftTextView = viewGroup.findViewById(R.id.textView_left);
            rightTextView = viewGroup.findViewById(R.id.textView_right);
//            textHeaderView = viewGroup.findViewById(R.id.layout_textheader);
//            imageHeaderView = viewGroup.findViewById(R.id.layout_imageheader);
            titleTextView = viewGroup.findViewById(R.id.textview_title);
            backgroundView = viewGroup.findViewById(R.id.view_actionBarBlackBackground);
        }
    }
}
