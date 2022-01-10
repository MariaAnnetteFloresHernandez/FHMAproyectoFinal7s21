package mx.edu.tesoem.isc.fhmaproyectofinal7s21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Principal extends AppCompatActivity {

    String idprocesa="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Animation titulo = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        TextView title = findViewById(R.id.tituloP);

        title.setAnimation(titulo);


    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == Activity.RESULT_OK){
                Intent Data = result.getData();
                String vistaregresa = Data.getStringExtra("vista");
                if(vistaregresa.equals("Listar")){
                    idprocesa = Data.getStringExtra("idregresa");
                }
                Toast.makeText(Principal.this, "Regresamos de la actividad " + Data.getStringExtra("vista") + " correctamente", Toast.LENGTH_SHORT).show();
            }else if(result.getResultCode()==Activity.RESULT_CANCELED){
                Toast.makeText(Principal.this, "Se cancelo operación", Toast.LENGTH_SHORT).show();
            }
        }
    });

    public void Acciones(View v){
        switch (v.getId()){
            case R.id.btnInsertar:
                lanzaInsertar();
                break;
            case R.id.btnListar:
                lanzaListar();
                break;
            case R.id.btnActualizar:
                lanzaActualizar();
                break;
            case R.id.btnEliminar:
                lanzaEliminar();
                break;
            case R.id.btnTerminar: finish();
                break;
        }
    }

    private void lanzaInsertar(){
        Intent intent = new Intent(this, RegistrarActivity.class);
        activityResultLauncher.launch(intent);
    }

    private void lanzaListar(){
        Intent intent = new Intent(this, ListarActivity.class);
        activityResultLauncher.launch(intent);
    }

    private void lanzaActualizar(){
        Intent intent = new Intent(this, ActualizaActivity.class);
        intent.putExtra("idbuscar",idprocesa);
        activityResultLauncher.launch(intent);
    }

    private void lanzaEliminar(){
        Intent intent = new Intent(this, mx.edu.tesoem.isc.fhmaproyectofinal7s21.EliminarActivity.class);
        intent.putExtra("idbuscar",idprocesa);
        activityResultLauncher.launch(intent);
    }
}