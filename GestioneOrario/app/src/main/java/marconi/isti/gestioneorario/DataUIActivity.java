package marconi.isti.gestioneorario;

import android.graphics.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class DataUIActivity extends AppCompatActivity {

    private ScaleGestureDetector scaleGestureDetector;
    private Matrix matrix = new Matrix();
    private float scale = 5.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_ui);

        scaleGestureDetector = new ScaleGestureDetector(this,new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        scaleGestureDetector.onTouchEvent(ev);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.
            SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scaleFactor = detector.getScaleFactor();

            scale *= detector.getScaleFactor();
            scale = Math.max(5.0f, Math.min(scale, 25.0f));

            matrix.setScale(scale, scale);



            ImageView imageView = (ImageView)findViewById(R.id.imageView2);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }
}
