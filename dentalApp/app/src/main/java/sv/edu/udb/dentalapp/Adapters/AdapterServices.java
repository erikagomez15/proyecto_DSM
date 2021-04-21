package sv.edu.udb.dentalapp.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sv.edu.udb.dentalapp.R;
import sv.edu.udb.dentalapp.models.Services;

public class AdapterServices extends RecyclerView.Adapter<AdapterServices.ViewHolderService>{
    ArrayList<Services> listServices;
    public AdapterServices(ArrayList<Services> listservices){
        this.listServices = listservices;
    }

    @Override
    public ViewHolderService onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_services,null,false);
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

    public class ViewHolderService extends RecyclerView.ViewHolder{
        TextView nombre, descripcion,precio, tiempo;

        public ViewHolderService(View itemView){
            super(itemView);

            nombre = (TextView) itemView.findViewById(R.id.item_name);
            descripcion = (TextView) itemView.findViewById(R.id.item_description);
            precio = (TextView) itemView.findViewById(R.id.itemPrice);
            tiempo = (TextView) itemView.findViewById(R.id.item_time);
        }

        public void asignarDatos(Services s){

            nombre.setText(s.getNombre());
            descripcion.setText(s.getDescripcion());
            precio.setText("Precio: $"+s.getPrecio());
            tiempo.setText("Duracion: " +s.getTiempo() +" minutos");

        }
    }
}
