package sv.edu.udb.dentalapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.Models.Appointment;
import sv.edu.udb.dentalapp.Models.Service;
import sv.edu.udb.dentalapp.R;

public class AdapterAppointment extends RecyclerView.Adapter<AdapterAppointment.ViewHolderAppointment> {
    ArrayList<Appointment> listAppointments;

    public AdapterAppointment(ArrayList<Appointment> listAppointments){this.listAppointments = listAppointments;}
    @Override
    public ViewHolderAppointment onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_appointments,null,false);
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


    public class ViewHolderAppointment extends RecyclerView.ViewHolder{
        TextView usuario, descripcion,fecha, servicio;

        public ViewHolderAppointment(View itemView){
            super(itemView);

            usuario = (TextView) itemView.findViewById(R.id.item_userAppointment);
            descripcion = (TextView) itemView.findViewById(R.id.item_descriptionAppointment);
            fecha = (TextView) itemView.findViewById(R.id.item_dateAppointment);
            servicio = (TextView) itemView.findViewById(R.id.item_serviceAppointment);
        }

        public void asignarDatos(Appointment a){

            usuario.setText("Usuario: "+a.getUser());
            descripcion.setText("Nota: "+a.getDescription());
            fecha.setText("Fecha: "+a.getDate());
            servicio.setText("Servicio: " +a.getService());

        }
    }
}
