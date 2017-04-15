package cecyt9.ipn.edu.calculadora_ws_cliente;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends AppCompatActivity {

    private static final String NAMESPACE = "http://192.168.0.4:8080/miProyectoWeb";
    private static String URL = "http://192.168.0.4:8080/miProyectoWeb/misWS?WSDL";

    EditText txtNum1, txtNum2;
    TextView resultadoo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNum1 = (EditText) findViewById(R.id.txtNum1);
        txtNum2 = (EditText) findViewById(R.id.txtNum2);
        resultadoo = (TextView) findViewById(R.id.txtResultado);
    }

    public void sumar(View v){

        String METHOD_NAME = "suma";
        String SOAP_ACTION = "http://192.168.0.4:8080/miProyectoWeb/suma";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("num1", txtNum1.getText().toString());
        request.addProperty("num2", txtNum2.getText().toString());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        /* Se captura la respuesta y se muestra en el TextView */
        //float respuesta;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapObject result= (SoapObject) envelope.getResponse();
            //respuesta = result.getProperty(0).toString();
            SoapPrimitive resultado = (SoapPrimitive) envelope.getResponse();
        //    respuesta = Float.parseFloat(resultado.toString());
            resultadoo.setText(resultado.toString());
        }
        catch(Exception e){
            e.printStackTrace();
            resultadoo.setText(e.toString());
        }
    }

    public void restar(View v){

        String METHOD_NAME = "resta";
        String SOAP_ACTION = "http://192.168.0.4:8080/miProyectoWeb/resta";


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("num1", txtNum1.getText().toString());
        request.addProperty("num2", txtNum2.getText().toString());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        /* Se captura la respuesta y se muestra en el TextView */
        //float respuesta;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapObject result= (SoapObject) envelope.getResponse();
            //respuesta = result.getProperty(0).toString();
            SoapPrimitive resultado = (SoapPrimitive) envelope.getResponse();
            //    respuesta = Float.parseFloat(resultado.toString());
            resultadoo.setText(resultado.toString());
        }
        catch(Exception e){
            e.printStackTrace();
            resultadoo.setText(e.toString());
        }
    }
    public void multiplicar(View v){

        String METHOD_NAME = "multiplicacion";
        String SOAP_ACTION = "http://192.168.0.4:8080/miProyectoWeb/multiplicacion";


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("num1", txtNum1.getText().toString());
        request.addProperty("num2", txtNum2.getText().toString());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        /* Se captura la respuesta y se muestra en el TextView */
        //float respuesta;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapObject result= (SoapObject) envelope.getResponse();
            //respuesta = result.getProperty(0).toString();
            SoapPrimitive resultado = (SoapPrimitive) envelope.getResponse();
            //    respuesta = Float.parseFloat(resultado.toString());
            resultadoo.setText(resultado.toString());
        }
        catch(Exception e){
                e.printStackTrace();
            resultadoo.setText(e.toString());
        }
    }
    public void dividir(View v){

        String METHOD_NAME = "division";
        String SOAP_ACTION = "http://192.168.0.4:8080/miProyectoWeb/division";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        request.addProperty("num1", txtNum1.getText().toString());
        request.addProperty("num2", txtNum2.getText().toString());

        SoapSerializationEnvelope envelope =
                new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        /* Se captura la respuesta y se muestra en el TextView */
        //float respuesta;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        try {
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapObject result= (SoapObject) envelope.getResponse();
            //respuesta = result.getProperty(0).toString();
            SoapPrimitive resultado = (SoapPrimitive) envelope.getResponse();
            //    respuesta = Float.parseFloat(resultado.toString());
            resultadoo.setText(resultado.toString());
        }
        catch(Exception e){
            e.printStackTrace();
            resultadoo.setText(e.toString());
        }
    }

}
