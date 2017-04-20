package cecyt9.ipn.edu.calculadora_ws_cliente;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    EditText txtNum1, txtNum2;
    TextView resultadoo;
    String num1, num2, resultado;
    int op;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNum1 = (EditText) findViewById(R.id.txtNum1);
        txtNum2 = (EditText) findViewById(R.id.txtNum2);
        resultadoo = (TextView) findViewById(R.id.txtResultado);
    }

    public void sumar(View v){
        op = 1;
        num1 = txtNum1.getText().toString();
        num2 = txtNum2.getText().toString();
        operacion ope = new operacion();
        ope.execute();
    }

    public void restar(View v){
        op = 2;
        num1 = txtNum1.getText().toString();
        num2 = txtNum2.getText().toString();
        operacion ope = new operacion();
        ope.execute();
    }
    public void multiplicar(View v){
        op = 3;
        num1 = txtNum1.getText().toString();
        num2 = txtNum2.getText().toString();
        operacion ope = new operacion();
        ope.execute();
    }
    public void dividir(View v){
        op = 4;
        num1 = txtNum1.getText().toString();
        num2 = txtNum2.getText().toString();
        operacion ope = new operacion();
        ope.execute();
    }

    private class operacion extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Void... params) {
// TODO: attempt authentication against a network service.
//WebService - Opciones
            String NAMESPACE = "http://ws/";
            String URL="http://192.168.0.14:8080/CalculatorWSApplication/CalculatorWS?WSDL";
            String METHOD_NAME = "";

            if(op == 1)
                METHOD_NAME = "suma";
            else{
                if(op == 2)
                    METHOD_NAME = "resta";
                else{
                    if(op == 3)
                        METHOD_NAME = "multiplicacion";
                    else{
                        if(op == 4)
                            METHOD_NAME = "division";
                    }
                }
            }



            String SOAP_ACTION = "http://ws/"+METHOD_NAME;
   //         Toast.makeText(getApplicationContext(), SOAP_ACTION, Toast.LENGTH_SHORT).show();

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("num1", num1);
            request.addProperty("num2", num2);



            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);


            HttpTransportSE ht = new HttpTransportSE(URL);
            try {
                resultado = "entro y fallo aquí"+" "+SOAP_ACTION+ " "+num1+" "+num2;
                ht.call(SOAP_ACTION, envelope);

                SoapPrimitive response = (SoapPrimitive)envelope.getResponse();

                resultado=response.toString();

                Log.i("Resultado: ",resultado);

            }
            catch (Exception e)
            {
                e.printStackTrace();


                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            if(success==false){
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                resultadoo.setText(resultado);
            }
            else{
                Toast.makeText(getApplicationContext(), "El resultado es: "+resultado, Toast.LENGTH_LONG).show();
                resultadoo.setText(resultado);
                ///es la interaccion

            }
        }

        @Override
        protected void onCancelled() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
        }
    }


}
