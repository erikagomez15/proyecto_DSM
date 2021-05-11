package sv.edu.udb.dentalapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Models.Appointment;
import sv.edu.udb.dentalapp.R;

public class AdapterAppointmentDashboard extends RecyclerView.Adapter<AdapterAppointmentDashboard.ViewHolderAppointment> implements View.OnClickListener{

    ArrayList<Appointment> listAppointments;
    private View.OnClickListener listener;
    public AdapterAppointmentDashboard(ArrayList<Appointment> listAppointments){
        this.listAppointments = listAppointments;
    }
    @Override
    public ViewHolderAppointment onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_appointments_dasboard,null,false);
        view.setOnClickListener(this);
        return new ViewHolderAppointment(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderAppointment holder, int position){
        holder.asignarDatos(listAppointments.get(position));
    }


    @Override
    public int getItemCount(){
        return listAppointments.size();
    }

    public void setOnclickListener(View.OnClickListener listener){this.listener = listener;}

    @Override
    public void onClick(View v) {
        if(listener != null)
        {
            listener.onClick(v);
        }
    }
    public class ViewHolderAppointment extends RecyclerView.ViewHolder{
        TextView usuario, descripcion,fecha, servicio;

        public ViewHolderAppointment(View itemView){
            super(itemView);

            usuario = (TextView) itemView.findViewById(R.id.userAppointmentDashboard);
            descripcion = (TextView) itemView.findViewById(R.id.descriptionAppointmentDashboard);
            fecha = (TextView) itemView.findViewById(R.id.dateAppointmentDashboard);
            servicio = (TextView) itemView.findViewById(R.id.hourAppointmentDashboard);
        }

        public void asignarDatos(Appointment a){

            usuario.setText("Usuario: "+a.getUser());
            descripcion.setText("Nota: "+a.getDescription());
            fecha.setText("Fecha: "+a.getDate());
            servicio.setText("Servicio: " +a.getService());

        }
    }

}
