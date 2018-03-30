package textfunction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ct.demo_custormviewstep1.R;

import java.util.List;

/**
 * Created by koudai_nick on 2018/2/8.
 */

public class MyAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mDatas;

    public MyAdapter(Context context, List<Integer> mDatas)
    {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount()
    {
        return mDatas.size();
    }

    public Object getItem(int position)
    {
        return mDatas.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.activity_view_function_item, parent, false);
            viewHolder.mImg = (ImageView) convertView
                    .findViewById(R.id.item_iv);
            viewHolder.mText = (TextView) convertView
                    .findViewById(R.id.item_tv);

            convertView.setTag(viewHolder);
        } else
        {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mImg.setImageResource(mDatas.get(position));
        viewHolder.mText.setText("some info " + position);

        return convertView;
    }

    private class ViewHolder
    {
        ImageView mImg;
        TextView mText;
    }
}
