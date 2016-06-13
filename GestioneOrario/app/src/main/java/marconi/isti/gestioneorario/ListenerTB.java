package marconi.isti.gestioneorario;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import parser.Orario;

/**
 * Created by winspa on 13/06/2016.
 */
public class ListenerTB  implements CompoundButton.OnCheckedChangeListener {

    private ArrayList<Orario> lo;
    ListenerTB( ArrayList<Orario> lo) {
        this.lo=lo;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        Activity t = (Activity) buttonView.getContext();
        TextView tv = (TextView) t.findViewById(R.id.info_text);
        if(isChecked)
        {
            List<ToggleButton> ltb  = new ArrayList<ToggleButton>();
        ToggleButton prima = (ToggleButton)t.findViewById(R.id.buttonO1);
            ltb.add(prima);
        ToggleButton seconda = (ToggleButton)t.findViewById(R.id.buttonO2);
            ltb.add(seconda);
        ToggleButton terza = (ToggleButton) t.findViewById(R.id.buttonO3);
            ltb.add(terza);
        ToggleButton quarta = (ToggleButton) t.findViewById(R.id.buttonO4);
            ltb.add(quarta);
        ToggleButton quinta = (ToggleButton) t.findViewById(R.id.buttonO5);
            ltb.add(quinta);
        ToggleButton sesta = (ToggleButton) t.findViewById(R.id.buttonO6);
            ltb.add(sesta);

        int ora = 0;
        for( ToggleButton s: ltb){
           if(buttonView.getId()==s.getId()){
              ora =  Integer.getInteger(s.getText().toString());
           }else{
               s.setChecked(false);
           }
        }
      /*  switch (buttonView.getId()){
            case R.id.buttonO1 : {ora = 1; break;}
            case R.id.buttonO2 : {ora = 2; break;}
            case R.id.buttonO3 : {ora = 3; break;}
            case R.id.buttonO4 :  {ora = 4; break;}
            case R.id.buttonO5 :  {ora = 5; break;}
            case R.id.buttonO6 :  {ora = 6; break;}
            default :  {ora = 0; break;}
        }*/


            Orario o = new Orario(null,null,"1","1","1","1");
            if(ora>0 & ora-1<lo.size()) {

                o = lo.get(ora-1);
                tv.setText("Classe " + o.getClasse() + " Aula " + o.getAula());
            }else{
                tv.setText("Non hai lezione nell'Ora Selezionata");
            }
        }
        else
        {
            tv.setText("");
        }

    }
}



