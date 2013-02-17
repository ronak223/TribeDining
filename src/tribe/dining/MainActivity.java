package tribe.dining;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	
	//TODO: Create prettier splash page and icon. Don't need a button just to enter app.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void diningHalls(View view){
    	Intent intent = new Intent(this, DisplayDiningHalls.class);
    	startActivity(intent);
    }
}
