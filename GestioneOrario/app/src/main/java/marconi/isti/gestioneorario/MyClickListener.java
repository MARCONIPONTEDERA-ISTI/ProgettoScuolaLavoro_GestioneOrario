package marconi.isti.gestioneorario;

import android.view.View;

import parser.Orario;

/**
 * Created by winspa on 13/06/2016.
 */
public interface MyClickListener {
    public void onItemClick(int position, Orario o, View v);
}