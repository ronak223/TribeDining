package tribe.dining;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

@SuppressLint("NewApi")
public class DisplayDiningHalls extends Activity{
	
	//TODO: Allow user to choose a day for retrieving menu, in current week.

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dining_halls);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_dining_halls, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void getSadlerFood(View view){
    	Intent intent = new Intent(this, DisplayTimes.class);
    	Bundle bundle = new Bundle();
    	bundle.putString("place", "Sadler");
    	intent.putExtras(bundle);
    	startActivity(intent);
    }
    
    public void getCafFood(View view){
    	Intent intent = new Intent(this, DisplayTimes.class);
    	Bundle bundle = new Bundle();
    	bundle.putString("place", "Commons");
    	intent.putExtras(bundle);
    	startActivity(intent);
    }
    
    public void getMarketplaceFood(View view){
    	Intent intent = new Intent(this, DisplayTimes.class);
    	Bundle bundle = new Bundle();
    	bundle.putString("place", "Marketplace");
    	intent.putExtras(bundle);
    	startActivity(intent);
    }

}
