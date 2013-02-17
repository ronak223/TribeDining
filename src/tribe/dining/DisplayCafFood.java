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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;


@SuppressLint("NewApi")
public class DisplayCafFood extends Activity {
	
	//initialize ListView to pass through to onCreate, along with a non-variable array for meal times
	private ListView cafMealTimeView;
	private String cafMTarray[]={"Breakfast", "Lunch", "Dinner"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_caf_food);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        //uses ListView methods to display on page, using given xml ID
        cafMealTimeView = (ListView)findViewById(R.id.cafMealTime);
        cafMealTimeView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, cafMTarray));
        
        //allows individual items in ListView to be selected to start new activity to populate food list
        cafMealTimeView.setOnItemClickListener(new OnItemClickListener() {
    	   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	    	switch(position){
    	    	case 0: //Intent intent = new Intent(DisplayCafFood.this, CafBreakfast.class);
		    	    	Intent intent = new Intent(DisplayCafFood.this, GeneratingScreen.class);
		    	        Bundle bundle = new Bundle();
		    	        bundle.putString("place", "Commons");
		    	        bundle.putString("time","Breakfast");
		    	        //need to input day here automatically
		    	        bundle.putInt("day", 4);
		    	        intent.putExtras(bundle);
    	    			startActivity(intent);
    	    			break;
    	    	case 1: //Intent intent2 = new Intent(DisplayCafFood.this, CafLunch.class);
	    	    		Intent intent2 = new Intent(DisplayCafFood.this, GeneratingScreen.class);
		    	        Bundle bundle2 = new Bundle();
		    	        bundle2.putString("place", "Commons");
		    	        bundle2.putString("time","Lunch");
		    	        //need to input day here automatically
		    	        bundle2.putInt("day", 4);
		    	        intent2.putExtras(bundle2);
    	    			startActivity(intent2);
    	    			break;
    	    	case 2: //Intent intent3 = new Intent(DisplayCafFood.this, CafDinner.class);
	    	    		Intent intent3 = new Intent(DisplayCafFood.this, GeneratingScreen.class);
		    	        Bundle bundle3 = new Bundle();
		    	        bundle3.putString("place", "Commons");
		    	        bundle3.putString("time","Breakfast");
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
        getMenuInflater().inflate(R.menu.activity_display_caf_food, menu);
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