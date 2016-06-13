package marconi.isti.gestioneorario;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import parser.Orario;

/**
 * Created by winspa on 13/06/2016.
 */
public class OrarioAdapter extends RecyclerView.Adapter<OrarioAdapter.OrarioViewHolder>{

    private List<Orario> lorario;
    private static MyClickListener myClickListener;



    public OrarioAdapter(List<Orario> persons){
        super();
        this.lorario = persons;
    }

    public static void setMyClickListener(MyClickListener myClickListener) {
        OrarioAdapter.myClickListener = myClickListener;
    }

    public static MyClickListener getMyClickListener() {
        return myClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public OrarioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_classe, parent, false);
        OrarioViewHolder pvh = new OrarioViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(OrarioViewHolder holder, int position) {
        holder.classe.setText(lorario.get(position).getClasse());
        holder.profcard.setText(lorario.get(position).getProfessore());
        holder.materiacard.setText(lorario.get(position).getMateria());
        holder.inizio.setText(lorario.get(position).getSOrainizio());
        holder.fine.setText(lorario.get(position).getSOrafine());
        holder.aula.setText(lorario.get(position).getAula());


    }

    @Override
    public int getItemCount() {
        return lorario.size();
    }

    public static class OrarioViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        CardView cv;
        TextView profcard;
        TextView materiacard;
        TextView inizio;
        TextView fine;
        TextView classe;
        TextView aula;

        List<Orario> lorario;



        OrarioViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.card_view);
            profcard = (TextView)itemView.findViewById(R.id.profcard);
            materiacard = (TextView)itemView.findViewById(R.id.materiacard);
            inizio = (TextView)itemView.findViewById(R.id.ora_inizio);
            fine = (TextView)itemView.findViewById(R.id.ora_fine);
            classe = (TextView)itemView.findViewById(R.id.classecard);
            aula = (TextView)itemView.findViewById(R.id.aula_card);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myClickListener.onItemClick(getAdapterPosition(), view);
        }
    }


}