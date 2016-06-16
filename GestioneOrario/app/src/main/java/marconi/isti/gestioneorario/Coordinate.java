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
        if(aula.contains(System.getProperty("line.separator"))){
           aula = aula.replace(System.getProperty("line.separator")," ");
        }
        if(coordinate.containsKey(aula)){
            return  coordinate.get(aula);
        }

        return null;
    }

    public Integer getPiano(String aula){
        if(aula.contains(System.getProperty("line.separator"))){
            aula = aula.replace(System.getProperty("line.separator")," ");
        }
        if(AulaPiano.containsKey(aula)){
            return  AulaPiano.get(aula);
        }

        return null;

    }

    private void init() {

        //piano terra
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

        //piano primo

        points = new Pair<Float, Float>(0.3114f,0.6326f);
        coordinate.put("17",points);
        AulaPiano.put("17",1);


        points = new Pair<Float, Float>(0.1666f,0.6708f);
        coordinate.put("INF.4",points);
        AulaPiano.put("INF.4",1);


        points = new Pair<Float, Float>(0.1666f,05846.f);
        coordinate.put("15",points);
        AulaPiano.put("15",1);


        points = new Pair<Float, Float>(0.1692f,0.4433f);
        coordinate.put("INF.2",points);
        AulaPiano.put("INF.2",1);

        points = new Pair<Float, Float>(0.1717f,0.1964f);
        coordinate.put("L.SIST.",points);
        AulaPiano.put("L.SIST",1);


        points = new Pair<Float, Float>(0.3246f,0.2625f);
        coordinate.put("L.E.ONICA",points);
        AulaPiano.put("L.E.ONICA",1);


        points = new Pair<Float, Float>(0.5688f,0.2971f);
        coordinate.put("14",points);
        AulaPiano.put("14",1);


        points = new Pair<Float, Float>(0.8318f,0.3928f);
        coordinate.put("13",points);
        AulaPiano.put("13",1);


        points = new Pair<Float, Float>(0.8285f,0.5112f);
        coordinate.put("12",points);
        AulaPiano.put("12",1);


        points = new Pair<Float, Float>(0.8328f,0.7808f);
        coordinate.put("INF.1",points);
        AulaPiano.put("INF.1",1);


        points = new Pair<Float, Float>(0.7899f,0.7808f);
        coordinate.put("11",points);
        AulaPiano.put("11",1);

        points = new Pair<Float, Float>(0.6710f,0.6009f);
        coordinate.put("INF.3",points);
        AulaPiano.put("INF.3",1);


        //secondo piano


        points = new Pair<Float, Float>(0.1673f,0.2312f);
        coordinate.put("28",points);
        AulaPiano.put("28",2);


        points = new Pair<Float, Float>(0.2655f,0.2401f);
        coordinate.put("29",points);
        AulaPiano.put("29",2);


        points = new Pair<Float, Float>(0.8333f,0.3223f);
        coordinate.put("20",points);
        AulaPiano.put("20",2);



        points = new Pair<Float, Float>(0.6933f,0.7131f);
        coordinate.put("21",points);
        AulaPiano.put("21",2);



        points = new Pair<Float, Float>(0.5519f,0.6723f);
        coordinate.put("22",points);
        AulaPiano.put("22",2);




        points = new Pair<Float, Float>(0.4323f,0.6521f);
        coordinate.put("23",points);
        AulaPiano.put("23",2);




        points = new Pair<Float, Float>(0.2762f,0.6207f);
        coordinate.put("24",points);
        AulaPiano.put("24",2);



        points = new Pair<Float, Float>(0.2520f,0.6089f);
        coordinate.put("25",points);
        AulaPiano.put("25",2);



        points = new Pair<Float, Float>(0.1691f,0.5430f);
        coordinate.put("26",points);
        AulaPiano.put("26",2);


        points = new Pair<Float, Float>(0.1683f,0.3728f);
        coordinate.put("27",points);
        AulaPiano.put("27",2);


        points = new Pair<Float, Float>(0.3011f,0.2307f);
        coordinate.put("DIS.3",points);
        AulaPiano.put("DIS.3",2);


        points = new Pair<Float, Float>(0.1707f,0.6275f);
        coordinate.put("DIS.2",points);
        AulaPiano.put("DIS.2",2);


        points = new Pair<Float, Float>(0.8333f,0.7509f);
        coordinate.put("L.CHIM.",points);
        AulaPiano.put("L.CHIM.",2);


        points = new Pair<Float, Float>(0.5644f,0.2286f);
        coordinate.put("L.FIS.",points);
        AulaPiano.put("L.FIS.",2);



        points = new Pair<Float, Float>(0.8333f,0.5166f);
        coordinate.put("L.SC.",points);
        AulaPiano.put("L.SC",2);



        points = new Pair<Float, Float>(0.4465f,0.2335f);
        coordinate.put("AULA FISICA",points);
        AulaPiano.put("AULA FISICA",2);



        points = new Pair<Float, Float>(0.8113f,0.7732f);
        coordinate.put("AULA CHIMICA",points);
        AulaPiano.put("AULA CHIMICA",2);




    }


}
