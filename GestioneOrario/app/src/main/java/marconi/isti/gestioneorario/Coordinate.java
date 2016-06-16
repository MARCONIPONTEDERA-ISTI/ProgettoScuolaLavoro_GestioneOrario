package marconi.isti.gestioneorario;

import android.util.Pair;
import android.view.ViewDebug;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by winspa on 16/06/2016.
 */
public class Coordinate {

    private Map<String, Pair<Float,Float>> coordinate;
    private Map<String, Integer> AulaPiano;

    public Coordinate(){
        coordinate = new HashMap<String, Pair<Float,Float>>();
        AulaPiano = new HashMap<String, Integer>();
        init();
    }

    public Pair<Float,Float> getCoordiante(String aula){
        if(coordinate.containsKey(aula)){
            return  coordinate.get(aula);
        }

        return null;
    }

    public Integer getPiano(String aula){
        if(AulaPiano.containsKey(aula)){
            return  AulaPiano.get(aula);
        }

        return null;

    }

    private void init() {
        Pair<Float,Float> points = new Pair<Float, Float>(0.1701f,0.4082f);
        coordinate.put("07",points);
        AulaPiano.put("07",0);

        points = new Pair<Float, Float>(0.1840f,0.6219f);
        coordinate.put("06",points);
        AulaPiano.put("06",0);


        points = new Pair<Float, Float>(0.1775f,0.6201f);
        coordinate.put("05",points);
        AulaPiano.put("05",0);

        points = new Pair<Float, Float>(0.3929f,0.5924f);
        coordinate.put("0",points);
        AulaPiano.put("0",0);

        points = new Pair<Float, Float>(0.4382f,0.5862f);
        coordinate.put("09",points);
        AulaPiano.put("09",0);

        points = new Pair<Float, Float>(0.5843f,0.5523f);
        coordinate.put("08",points);
        AulaPiano.put("08",0);







    }


}
