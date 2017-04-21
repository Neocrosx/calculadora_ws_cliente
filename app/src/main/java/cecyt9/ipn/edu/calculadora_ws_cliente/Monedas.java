package cecyt9.ipn.edu.calculadora_ws_cliente;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Monedas extends AppCompatActivity {

    public String selec="";
    EditText cuanto;
    Spinner tipoMon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monedas);
        cuanto=(EditText)findViewById(R.id.txtCuanto);
        tipoMon=(Spinner)findViewById(R.id.spTipoMon);
        initListener();
    }


    public void Convertir(View vw)
    {
        String aConvertir=cuanto.getText().toString();
        if(selec.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Elige un tipo de moneda",Toast.LENGTH_LONG).show();
        }
        else
        if(aConvertir.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Ingresa una cantidad",Toast.LENGTH_LONG).show();
        }
        else
            new wsDiv().execute(selec,aConvertir);
    }



    public void initListener()
    {
        try {
            tipoMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                    if(i!=0) {
                        selec = ""+i;
                        Toast.makeText(adapterView.getContext(), adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_LONG).show();
                    }else
                        selec="";
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    selec = "";
                }
            });
        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private class wsDiv extends AsyncTask<String,Void,String> {

        @Override
        protected String doInBackground(String... strings) {
            String result="";
            HttpClient cliente=new DefaultHttpClient();
            HttpGet peticion=new HttpGet("http://192.168.0.14:8080/CalculatorWSApplication/webresources/divisas/Convertidor/" +
                    "selec="+strings[0]+"&ent="+strings[1]+"");
            peticion.setHeader("content-type","text/plain");
            try {
                HttpResponse res=cliente.execute(peticion);
                result= EntityUtils.toString(res.getEntity());

            } catch (IOException e) {

            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getApplicationContext(),"Resultado: "+s,Toast.LENGTH_LONG).show();
        }
    }

    public void reg(View v){
        Intent envia = new Intent(this, MainActivity.class);
        finish();
        startActivity(envia);
    }

}
