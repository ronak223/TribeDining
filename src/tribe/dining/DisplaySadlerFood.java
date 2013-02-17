package tribe.dining;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class DisplaySadlerFood extends Activity {

	//initialize ListView to pass through to onCreate, along with a non-variable array for meal times
	private ListView sadlerMealTimeView;
	private String sadMTarray[]={"Breakfast", "Lunch", "Dinner"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sadler_food);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        sadlerMealTimeView = (ListView)findViewById(R.id.sadlerMealTime);
        sadlerMealTimeView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, sadMTarray));
        
        //allows individual items in ListView to be selected to start new activity to populate food list
        sadlerMealTimeView.setOnItemClickListener(new OnItemClickListener() {
     	   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     	    	switch(position){
     	    	case 0: //Intent intent = new Intent(DisplaySadlerFood.this, CafBreakfast.class);
	     	    		Intent intent = new Intent(DisplaySadlerFood.this, GeneratingScreen.class);
		    	        Bundle bundle = new Bundle();
		    	        bundle.putString("place", "Sadler");
		    	        bundle.putString("time","Breakfast");
		    	        //need to input day here automatically
		    	        bundle.putInt("day", 4);
		    	        intent.putExtras(bundle);
		    			startActivity(intent);
		    			break;
     	    	case 1: //Intent intent2 = new Intent(DisplaySadlerFood.this, CafLunch.class);
	     	    		Intent intent2 = new Intent(DisplaySadlerFood.this, GeneratingScreen.class);
		    	        Bundle bundle2 = new Bundle();
		    	        bundle2.putString("place", "Sadler");
		    	        bundle2.putString("time","Lunch");
		    	        //need to input day here automatically
		    	        bundle2.putInt("day", 4);
		    	        intent2.putExtras(bundle2);
		    			startActivity(intent2);
		    			break;
     	    	case 2: //Intent intent3 = new Intent(DisplaySadlerFood.this, CafDinner.class);
	     	    		Intent intent3 = new Intent(DisplaySadlerFood.this, GeneratingScreen.class);
		    	        Bundle bundle3 = new Bundle();
		    	        bundle3.putString("place", "Sadler");
		    	        bundle3.putString("time","Dinner");
		    	        //need to input day here automatically
		    	        bundle3.putInt("day", 4);
		    	        intent3.putExtras(bundle3);
		    			startActivity(intent3);
		    			break;
     	    	}
     	    }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_sadler_food, menu);
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

  
}
