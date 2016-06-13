package marconi.isti.gestioneorario;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import android.graphics.Matrix.ScaleToFit;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import parser.Orario;

public class DataUIActivity extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    private float scale = 10.0f;
    private ArrayList<Orario> lo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ui);

        RecyclerView rv = (RecyclerView)findViewById(R.id.cardList);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(llm);



        Bundle b = getIntent().getExtras();
        lo =  (ArrayList<Orario>)getIntent().getSerializableExtra("ListaOrari");

        String giorno = b.getString("giorno");

        TextView textgiorno = (TextView)findViewById(R.id.giornoricerca);
        textgiorno.setText(giorno);

        String tipo = b.getString("tipo");
        TextView tipo_ricerca = (TextView)findViewById(R.id.tipo_ricerca);
        tipo_ricerca.setText(tipo);

        ToggleButton prima = (ToggleButton)findViewById(R.id.buttonO1);
        ToggleButton seconda = (ToggleButton)findViewById(R.id.buttonO2);
        ToggleButton terza = (ToggleButton)findViewById(R.id.buttonO3);
        ToggleButton quarta = (ToggleButton) findViewById(R.id.buttonO4);
        ToggleButton quinta = (ToggleButton) findViewById(R.id.buttonO5);
        ToggleButton sesta = (ToggleButton) findViewById(R.id.buttonO6);

        ListenerTB ltb = new ListenerTB(lo);
        prima.setOnCheckedChangeListener(ltb);
        seconda.setOnCheckedChangeListener(ltb);
        terza.setOnCheckedChangeListener(ltb);
        quarta.setOnCheckedChangeListener(ltb);
        quinta.setOnCheckedChangeListener(ltb);
        sesta.setOnCheckedChangeListener(ltb);

        if(lo!=null) {
            OrarioAdapter adapter = new OrarioAdapter(lo);

            adapter.setMyClickListener(new MyClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    Activity t = (Activity) v.getContext();
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
                        if(position==s.getId()){
                            s.setChecked(true);
                        }else{
                            s.setChecked(false);
                        }
                    }
                }
            } );
           // rv.addOnItemTouchListener(adapter.getMyClickListener());
            rv.setAdapter(adapter);
        }


       /* ImageView imageView = (ImageView)findViewById(R.id.imageView2);

        matrix = imageView.getImageMatrix();
       long imageWidth = imageView.getDrawable().getIntrinsicWidth();
        long imageHeight = 250;
        RectF drawableRect = new RectF(0, 0, imageWidth, imageHeight);
        RectF viewRect = new RectF(0, 0, imageView.getWidth(), imageView.getHeight());
        matrix.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER);
        imageView.setImageMatrix(matrix);





        scaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());*/
    }




    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }

  /*  private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            //float scaleFactor = detector.getScaleFactor();

            scale *= detector.getScaleFactor();
            //scale = Math.max(10.0f, Math.min(scale, 55.0f));

            matrix.setScale(scale, scale);



            ImageView imageView = (ImageView)findViewById(R.id.imageView2);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }*/
}
