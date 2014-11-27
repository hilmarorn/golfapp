package cityboys.golfapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Busli on 22.11.2014.
 */
public class CustomGridAdapter extends BaseAdapter {

    // Context-ið sem er verið að vinna með
    private Context context;
    // Gildin sem adapter-inn á að halda utan um
    private String[] gridValues;

    /*
    Notkun: new CustomGridAdapter(context, values)
    Fyrir: context er context-ið hjá activity-inu og values er af taginu String[]
    Eftir: Búið er að tengja þennan adapter við GridLayout og setja inn gildi
     */
    public CustomGridAdapter(Context context, String[ ] gridValues) {
        this.context        = context;
        this.gridValues     = gridValues;
    }

    /*
    Notkun: CustomGridAdapter.getCount()
    Fyrir: CustomGridAdapter er adapter af réttu tagi
    Eftir: búið er að skila hversu mörg gildi eru í CustomGridAdapter
     */
    @Override
    public int getCount() {
        // Number of times getView method call depends upon gridValues.length
        return gridValues.length;
    }

    /*
    Yfirskrifað til að skila engu gildi þar sem ekki er þörf fyrir þetta
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /*
    Yfirskrifað til að skila engu gildi þar sem ekki er þörf fyrir þetta
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }


    // Kallað er á þetta fall jafn oft og gildið á gridValues.length
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        LayoutInflator til að geta kallað grid_item.xml sem er útlitið á hverju
        TextView sem búið er til fyrir gildin
        */
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {
            gridView = new View(context);

            // Inflate-a rétta útlitinu
            gridView = inflater.inflate( R.layout.grid_item , null);

            // Finna rétta TextView-ið
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);

            // Fylla inní það
            textView.setText(gridValues[position]);

            // Ekki notað eins og er, á að gefa okkur gildið sem verið er að vinna með
            String arrLabel = gridValues[ position ];
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}