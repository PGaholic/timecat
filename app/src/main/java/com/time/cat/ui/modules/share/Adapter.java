package com.time.cat.ui.modules.share;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.time.cat.R;

import java.util.List;

public interface Adapter {


    int VIEW_TYPE_EMPTY = 2;
    int VIEW_TYPE_DATA = 1;
    int VIEW_TYE_LOAD = 0;

    enum Status {
        LOADING, LOAD
    }

    class AppInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

        private List<ShareAppInfo> mData;
        private Status status = Status.LOADING;

        public AppInfoAdapter(List<ShareAppInfo> data) {
            this.mData = data;
        }

        @Override
        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case VIEW_TYPE_EMPTY:
                    return new EmptyViewHolder(parent);
                case VIEW_TYPE_DATA:
                    return new AppInfoViewHolder(parent);
                case VIEW_TYE_LOAD:
                    return new LoadingViewHolder(parent);
            }
            return null;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position) {

        }

        public void loadData() {
            status = Status.LOAD;
            notifyDataSetChanged();
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return mData.size() == 0 ? (status == Status.LOADING ? VIEW_TYE_LOAD : VIEW_TYPE_EMPTY) : VIEW_TYPE_DATA;
            }
            return VIEW_TYPE_DATA;
        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
            ShareAppInfo appInfo = null;
            if (mData.size() > position) {
                appInfo = mData.get(position);
            }
            holder.onBindViewHolder(appInfo);
        }

        @Override
        public int getItemCount() {
            return mData.size() == 0 ? 1 : mData.size();
        }

    }

    class BaseViewHolder<T extends Object> extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        void onBindViewHolder(T object) {

        }
    }


    class LoadingViewHolder extends BaseViewHolder {

        LoadingViewHolder(ViewGroup vp) {
            super(LayoutInflater.from(vp.getContext()).inflate(R.layout.item_loading, vp, false));
        }
    }

    class EmptyViewHolder extends BaseViewHolder {

        EmptyViewHolder(ViewGroup vp) {
            super(LayoutInflater.from(vp.getContext()).inflate(R.layout.item_empty_info, vp, false));
        }
    }

    class AppInfoViewHolder extends BaseViewHolder<ShareAppInfo> implements CompoundButton.OnCheckedChangeListener {

        ImageView mIcon;
        TextView mAppNameTv;
        SwitchCompat mSwitchCompat;
        ShareAppInfo appInfo;

        AppInfoViewHolder(ViewGroup vp) {
            super(LayoutInflater.from(vp.getContext()).inflate(R.layout.item_app_info, vp, false));
            mIcon = itemView.findViewById(R.id.icon);
            mAppNameTv = itemView.findViewById(R.id.app_name);
            mSwitchCompat = itemView.findViewById(R.id.sc);
        }

        void onBindViewHolder(ShareAppInfo appInfo) {
            this.appInfo = appInfo;
            mIcon.setImageDrawable(appInfo.applicationInfo.loadIcon(itemView.getContext().getPackageManager()));
            mAppNameTv.setText(appInfo.appName);
            mSwitchCompat.setChecked(appInfo.enable);
            mSwitchCompat.setOnCheckedChangeListener(this);
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            this.appInfo.enable = isChecked;
        }
    }
}