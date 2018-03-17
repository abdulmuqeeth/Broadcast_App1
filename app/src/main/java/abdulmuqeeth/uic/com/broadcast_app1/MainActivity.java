package abdulmuqeeth.uic.com.broadcast_app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button attractions_button = (Button) findViewById(R.id.button1);
        Button restaurants_button = (Button) findViewById(R.id.button2);

        attractions_button.setOnClickListener(attractionListener);
        restaurants_button.setOnClickListener(restaurantsListener);
    }

    private View.OnClickListener attractionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Attractions" , Toast.LENGTH_SHORT).show();

            Intent attraction = new Intent("abdulmuqeeth.uic.com.receiver_app.ATTRACTIONS");
            sendBroadcast(attraction);
        }
    };

    private View.OnClickListener restaurantsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Restaurants", Toast.LENGTH_SHORT).show();

            Intent restaurant = new Intent("abdulmuqeeth.uic.com.receiver_app.RESTAURANTS");
            sendBroadcast(restaurant);
        }
    };


}
