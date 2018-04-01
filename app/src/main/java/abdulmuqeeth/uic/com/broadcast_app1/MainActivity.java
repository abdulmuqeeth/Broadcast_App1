package abdulmuqeeth.uic.com.broadcast_app1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button attractions_button;
    private Button restaurants_button;
    private static final String MYPERM = "edu.uic.cs478.sp18.project3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attractions_button = (Button) findViewById(R.id.button1);
        restaurants_button = (Button) findViewById(R.id.button2);

        attractions_button.setOnClickListener(attractionListener);
        restaurants_button.setOnClickListener(restaurantsListener);

    }

    private View.OnClickListener attractionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            meth1();
        }
    };

    private void meth1(){
        if (ContextCompat.checkSelfPermission(this, MYPERM)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Attractions" , Toast.LENGTH_SHORT).show();
            Intent attraction = new Intent("abdulmuqeeth.uic.com.receiver_app.ATTRACTIONS");
            sendBroadcast(attraction);
        }
        else {
            Toast.makeText(this, "requesting permission", Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this, new String[]{MYPERM}, 100) ;
        }

    }

    private View.OnClickListener restaurantsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        meth2();
        }
    };

    private void meth2(){
        Toast.makeText(this, "restListen", Toast.LENGTH_SHORT).show();
        if (ContextCompat.checkSelfPermission(this, MYPERM)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Restaurants" , Toast.LENGTH_SHORT).show();
            Intent restaurant = new Intent("abdulmuqeeth.uic.com.receiver_app.RESTAURANTS");
            sendBroadcast(restaurant);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{MYPERM}, 101) ;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Toast.makeText(this, "ReqCode"+requestCode, Toast.LENGTH_SHORT).show();

        switch(requestCode){
            case 100 : {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Granted", Toast.LENGTH_SHORT).show();
                    Intent aIntent = new Intent("abdulmuqeeth.uic.com.receiver_app.ATTRACTIONS");
                    sendBroadcast(aIntent);
                }
            }

            case 101: if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent aIntent = new Intent("abdulmuqeeth.uic.com.receiver_app.RESTAURANTS");
                    sendBroadcast(aIntent);
                }
            }
            default:
        }
    }
}


