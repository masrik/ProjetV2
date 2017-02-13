package simon.projetfinalv2.Model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import simon.projetfinalv2.R;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.ViewHolder> {

    private final List<String> items;
    private final OnItemClickListener listener;
    private  Context context;

    public interface OnItemClickListener {

        void onItemClick(List<String> items, int position);
    }



    public HorizontalAdapter(Context context, List<String> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
        this.context = context;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_adapter, parent, false);
        return new ViewHolder(v, context);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(items.get(position), listener, position, items);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.ImgView);
            this.context= context;
        }

        public void bind(final String item, final OnItemClickListener listener, final int position, final List<String> items) {

            Glide.with(context).load(item).override(500,500).centerCrop().into(image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {

                    listener.onItemClick(items, position);
                }
            });
        }
    }



}

 