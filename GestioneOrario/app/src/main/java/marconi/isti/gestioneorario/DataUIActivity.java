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
import android.widget.Toast;
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


        TouchImageView tiv = (TouchImageView)findViewById(R.id.imageView2);
        tiv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TouchImageView tiv = (TouchImageView)findViewById(R.id.imageView2);
                RectF zoom = tiv.getZoomedRect();
                String x = String.valueOf(zoom.centerX());
                String y = String.valueOf(zoom.centerY());
                Toast.makeText(getApplicationContext(),
                        "X: "+x+"Y: "+y, Toast.LENGTH_LONG)
                        .show();
            }
        });


        Bundle b = getIntent().getExtras();
        lo =  (ArrayList<Orario>)getIntent().getSerializableExtra("ListaOrari");

        String giorno = b.getString("giorno");

        TextView textgiorno = (TextView)findViewById(R.id.giornoricerca);
        textgiorno.setText(giorno);

        String tipo = b.getString("tipo");
        TextView tipo_ricerca = (TextView)findViewById(R.id.tipo_ricerca);
        tipo_ricerca.setText(tipo);




      /*  terza.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    buttonView.getId();


                    RecyclerView rv = (RecyclerView)findViewById(R.id.cardList);
                    LinearLayoutManager llm = ((LinearLayoutManager) rv.getLayoutManager());
                    ((LinearLayoutManager) rv.getLayoutManager()).smoothScrollToPosition(rv, null, 2);

                }
            }
            });*/


        if(lo!=null) {
            OrarioAdapter adapter = new OrarioAdapter(lo);


            rv.addOnScrollListener(new RecyclerView.OnScrollListener()
            {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy)
                {
                    super.onScrolled(recyclerView, dx, dy);
                    int firstVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                    if(firstVisibleItem >= 0) //check for scroll down
                    {
                        Activity t = (Activity) recyclerView.getContext();


                    }
                }
            });

            adapter.setMyClickListener(new MyClickListener() {
                @Override
                public void onItemClick(int position, Orario o, View v) {
                    Activity t = (Activity) v.getContext();

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




  /*  @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }*/

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
