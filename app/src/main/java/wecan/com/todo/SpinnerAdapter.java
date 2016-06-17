package wecan.com.todo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

/**
 * Created by owner on 5/30/2016.
 */

class SpinnerAdapter extends ArrayAdapter<TagEnum> {

    private Activity mContext;
    private List<TagEnum> mTagEnum;

    SpinnerAdapter(Activity context, int layout, List<TagEnum> tagEnumList) {
        super(context, layout, tagEnumList);
        mContext = context;
        mTagEnum = tagEnumList;
    }

    @Override
    public TagEnum getItem(int i) {
        return mTagEnum.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mTagEnum.hashCode();
    }

    @Override
    public int getCount() {
        return mTagEnum.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i,view,viewGroup);
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getCustomView(i, view, viewGroup);
    }

    private View getCustomView(int i, View view, ViewGroup viewGroup){
        ViewHolder viewHolder;

        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = mContext.getLayoutInflater();
            view = inflater.inflate(R.layout.spinner_item, viewGroup, false);
            viewHolder.ivColor = (ImageView) view.findViewById(R.id.priorityColor);
            viewHolder.tvColorName = (TextView) view.findViewById(R.id.priorityColorName);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.ivColor.setBackgroundColor(mTagEnum.get(i).getTagColor());
        viewHolder.tvColorName.setText(mTagEnum.get(i).getTagName());
        viewHolder.tvColorName.setTextColor(mTagEnum.get(i).getTagColor());

        return view;
    }

    private static class ViewHolder{
        ImageView ivColor;
        TextView tvColorName;
    }
}
