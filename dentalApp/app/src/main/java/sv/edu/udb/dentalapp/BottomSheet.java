package sv.edu.udb.dentalapp;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import sv.edu.udb.dentalapp.Models.Appointment;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {
    FirebaseDatabase database;
    DatabaseReference refAppointment;
    String key, tipo = "", usuario = "", date,description,service,User;
    Button btnUpdate, btnDelete;

    public BottomSheet(Appointment appointment, String type, String user) {
        database = FirebaseDatabase.getInstance();
        refAppointment = database.getReference();
        key = appointment.getKey();
        date = appointment.getDate();
        description = appointment.getDescription();
        service = appointment.getService();
        User = appointment.getUser();
        tipo = type;
        usuario = user;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.row_add_item,container, false);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), editAppointment.class);
                intent.putExtra("date",date);
                intent.putExtra("description",description);
                intent.putExtra("key",key);
                intent.putExtra("service",service);
                intent.putExtra("user", User);
                intent.putExtra("type",tipo);
                intent.putExtra("usuario",usuario);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
                ad.setMessage("¿Está seguro de eliminar la cita?").setTitle("Confirmación");

                ad.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        refAppointment.child("Appointments").child(key).removeValue();
                        Toast.makeText(getContext(),"Cita eliminada",Toast.LENGTH_LONG).show();
                    }
                });

                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(),"Operacion cancelada", Toast.LENGTH_LONG).show();
                    }
                });
                ad.show();
            }
        });

        return view;
    }

    }

