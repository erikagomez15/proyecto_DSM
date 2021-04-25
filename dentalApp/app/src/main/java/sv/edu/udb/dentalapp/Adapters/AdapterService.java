package sv.edu.udb.dentalapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

import sv.edu.udb.dentalapp.Models.Service;
import sv.edu.udb.dentalapp.R;
import sv.edu.udb.dentalapp.addAppointment;

public class AdapterService extends RecyclerView.Adapter<AdapterService.ViewHolderService> implements View.OnClickListener{
    ArrayList<Service> listServices;
    private View.OnClickListener listener;
    public AdapterService(ArrayList<Service> listservices){
        this.listServices = listservices;
    }

    @Override
    public ViewHolderService onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_services,null,false);
        view.setOnClickListener(this);
        return new ViewHolderService(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderService holder, int position){
        holder.asignarDatos(listServices.get(position));
    }


    @Override
    public int getItemCount(){
        return listServices.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
     this.listener = listener;
    }

    @Override
    public void onClick(View v) {
    if(listener != null)
    {
        listener.onClick(v);
                    }
    }

    public class ViewHolderService extends RecyclerView.ViewHolder{
        TextView nombre, descripcion,precio, tiempo;

        public ViewHolderService(View itemView){
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.item_name);
            descripcion = (TextView) itemView.findViewById(R.id.item_description);
            precio = (TextView) itemView.findViewById(R.id.itemPrice);
            tiempo = (TextView) itemView.findViewById(R.id.item_time);
        }

        public void asignarDatos(Service s){

            nombre.setText(s.getName());
            descripcion.setText(s.getDescription());
            precio.setText("Precio: $"+s.getPrice());
            tiempo.setText("Duracion: " +s.getTime() +" minutos");

        }
    }
}

