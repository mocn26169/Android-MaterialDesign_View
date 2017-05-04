package com.materialdesigndemo.android_materialdesign_view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.materialdesigndemo.android_materialdesign_view.R;

import java.util.List;

/**
 * Created by m on 2016/11/1.
 */
public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.NormalViewHolder> {

    private Context mContext;
    private List<String> mList;

    public ContentAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void setDataAndRefreshUI(List<String> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public NormalViewHolder onCreateViewHolder(ViewGroup group, int i) {
        View view = View.inflate(mContext, R.layout.item_content2, null);
        return new NormalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NormalViewHolder holder, int i) {
        holder.setDataAndRefreshUI(mList.get(i));
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {

        TextView mTvContent;

        public NormalViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }

        private void setDataAndRefreshUI(String content) {
            mTvContent.setText(content);
        }
    }
}
