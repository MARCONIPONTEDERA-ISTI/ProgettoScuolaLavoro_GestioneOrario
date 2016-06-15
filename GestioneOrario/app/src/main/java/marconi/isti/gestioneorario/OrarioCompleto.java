package marconi.isti.gestioneorario;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import parser.GiornoSettimana;
import parser.Orario;
import parser.Professore;

public class OrarioCompleto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orario_completo);


        RecyclerView rv = (RecyclerView)findViewById(R.id.cardList);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);


        Bundle b = getIntent().getExtras();
        ArrayList<GiornoSettimana> orariosettimana =  (ArrayList<GiornoSettimana>)getIntent().getSerializableExtra("OrarioCompleto");
        List<Orario> lo = new ArrayList<Orario>();
        for (GiornoSettimana g:orariosettimana) {
            for (Professore p:g.getListaProfessori().values()) {
                lo.addAll(p.getListaOrari());
            }
        }

        OrarioAdapter adapter = new OrarioAdapter(lo);
        rv.setAdapter(adapter);

    }
}
