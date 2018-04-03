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
    private static final String ATTRACTIONS_INTENT_ACTION = "abdulmuqeeth.uic.com.receiver_app.ATTRACTIONS";
    private static final String RESTAURANTS_INTENT_ACTION = "abdulmuqeeth.uic.com.receiver_app.RESTAURANTS";

    private final static int ATTRACTIONS_REQ_CODE = 100;
    private final static int RESTAURANTS_REQ_CODE = 101;

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
            goToAttractions();
        }
    };

    private void goToAttractions(){
        if (ContextCompat.checkSelfPermission(this, MYPERM)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Navigating to Attractions" , Toast.LENGTH_SHORT).show();
            Intent attraction = new Intent(ATTRACTIONS_INTENT_ACTION);
            sendBroadcast(attraction);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{MYPERM}, ATTRACTIONS_REQ_CODE) ;
        }

    }

    private View.OnClickListener restaurantsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        goToRestaurants();
        }
    };

    private void goToRestaurants(){
        if (ContextCompat.checkSelfPermission(this, MYPERM)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Navigating to Restaurants" , Toast.LENGTH_SHORT).show();
            Intent restaurant = new Intent(RESTAURANTS_INTENT_ACTION);
            sendBroadcast(restaurant);
        }
        else {
            ActivityCompat.requestPermissions(this, new String[]{MYPERM}, RESTAURANTS_REQ_CODE) ;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch(requestCode){
            case 100 : {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Navigating to Attractions" , Toast.LENGTH_SHORT).show();
                    Intent aIntent = new Intent(ATTRACTIONS_INTENT_ACTION);
                    sendBroadcast(aIntent);
                }
                break;
            }

            case 101: if (grantResults.length > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Navigating to Restaurants" , Toast.LENGTH_SHORT).show();
                    Intent aIntent = new Intent(RESTAURANTS_INTENT_ACTION);
                    sendBroadcast(aIntent);
                }
                break;
            }
            default:
        }
    }
}


