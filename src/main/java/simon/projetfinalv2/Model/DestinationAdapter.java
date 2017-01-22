package simon.projetfinalv2.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import simon.projetfinalv2.R;

/**
 * Created by simon on 21/01/2017.
 */

public class DestinationAdapter extends BaseAdapter {
    List<Destination> list;
    LayoutInflater inflater;
    public DestinationAdapter(Context context, List<Destination> list)
    {
        inflater = LayoutInflater.from(context);
        this.list=list;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Destination getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.destination_item,null);
        TextView TvName = (TextView) convertView.findViewById(R.id.Tv2);
        TextView TvType = (TextView) convertView.findViewById(R.id.Tv1);
        ImageView imvMainPhot =(ImageView) convertView.findViewById(R.id.imageView3);

        Destination destination = list.get(position);
        TvName.setText(destination.getName());
        TvType.setText(destination.getType());
        imvMainPhot.setImageBitmap(destination.ConvertToBimap());
        imvMainPhot.setAdjustViewBounds(true);

        return convertView;
}

}
