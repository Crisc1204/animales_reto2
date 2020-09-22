package co.com.animales.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.animales.MainActivity;
import co.com.animales.R;
import co.com.animales.entity.Animales;

public class AnimalesAdapter extends BaseAdapter implements Filterable {

    private List<Animales> animalesListIn;
    private List<Animales> animalesListOut;
    private final LayoutInflater inflater;

    public AnimalesAdapter(Context context, List<Animales> animalesList) {

        animalesListOut = animalesList;
        animalesListIn = animalesList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return animalesListOut.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                animalesListOut = (List<Animales>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Animales> FilteredArrList = new ArrayList<>();
                if (animalesListIn == null) {
                    animalesListIn = new ArrayList<>(animalesListOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = animalesListIn.size();
                    results.values = animalesListIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < animalesListIn.size(); i++) {
                        String data = animalesListIn.get(i).getNombre();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(animalesListIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    @Override
    public Animales getItem(int i) {
        return animalesListOut.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null){
            holder = (ViewHolder) view.getTag();
        }else {
            view = inflater.inflate(R.layout.animal_item_layout, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.imagen.setImageResource(animalesListOut.get(i).getImagen());
        holder.txtNombre.setText(animalesListOut.get(i).getNombre());
        holder.txtDescripcion.setText(animalesListOut.get(i).getDescripcion());
        holder.cardViewAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(view.getContext(), animalesListOut.get(i).getSonido());
                mediaPlayer.start();
            }
        });
        return view;
    }

    class ViewHolder {

        @BindView(R.id.imagen)
        ImageView imagen;
        @BindView(R.id.textNombre)
        TextView txtNombre;
        @BindView(R.id.textDescripcion)
        TextView txtDescripcion;
        @BindView(R.id.cardViewAnimal)
        CardView cardViewAnimal;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
