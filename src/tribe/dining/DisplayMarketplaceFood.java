package tribe.dining;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.NavUtils;

@SuppressLint("NewApi")
public class DisplayMarketplaceFood extends Activity {
	
	//initialize ListView to pass through to onCreate, along with a non-variable array for meal times
	private ListView marketMealTimeView;
	private String marMTarray[]={"Breakfast", "Lunch", "Dinner"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_marketplace_food);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        //uses ListView methods to display on page, using given xml ID
        marketMealTimeView = (ListView)findViewById(R.id.marketplaceMealTime);
        marketMealTimeView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, marMTarray));
        
        //allows individual items in ListView to be selected to start new activity to populate food list
        marketMealTimeView.setOnItemClickListener(new OnItemClickListener() {
     	   public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     	    	switch(position){
     	    	case 0: Intent intent = new Intent(DisplayMarketplaceFood.this, GeneratingScreen.class);
		    	        Bundle bundle = new Bundle();
		    	        bundle.putString("place", "Marketplace");
		    	        bundle.putString("time","Breakfast");
		    	        //need to input day here automatically
		    	        bundle.putInt("day", 4);
		    	        intent.putExtras(bundle);
		    			startActivity(intent);
		    			break;
     	    	case 1: //Intent intent1 = new Intent(DisplayMarketplaceFood.this, CafBrunch.class);
	     	    		Intent intent1 = new Intent(DisplayMarketplaceFood.this, GeneratingScreen.class);
		    	        Bundle bundle1 = new Bundle();
		    	        bundle1.putString("place", "Marketplace");
		    	        bundle1.putString("time","Lunch");
		    	        //need to input day here automatically
		    	        bundle1.putInt("day", 4);
		    	        intent1.putExtras(bundle1);
		    			startActivity(intent1);
		    			break;
     	    	case 2: //Intent intent2 = new Intent(DisplayMarketplaceFood.this, CafLunch.class);
	     	    		Intent intent2 = new Intent(DisplayMarketplaceFood.this, GeneratingScreen.class);
		    	        Bundle bundle2 = new Bundle();
		    	        bundle2.putString("place", "Marketplace");
		    	        bundle2.putString("time","Dinner");
		    	        //need to input day here automatically
		    	        bundle2.putInt("day", 4);
		    	        intent2.putExtras(bundle2);
		    			startActivity(intent2);
		    			break;
     	    	}
     	    }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_marketplace_food, menu);
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
