package tribe.dining;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SadlerDinner extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadler_dinner);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sadler_dinner, menu);
        return true;
    }
}
