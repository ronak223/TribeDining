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
public class DisplayTimes extends Activity {

	//initialize ListView to pass through to onCreate, along with a non-variable array for meal times
	private ListView timesListView;
	private String mealTimes[]={"Breakfast", "Lunch", "Dinner"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_times);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		final String location = (String) bundle.get("place");
        
        timesListView = (ListView)findViewById(R.id.timesList);
        timesListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mealTimes));
        
        //allows individual items in ListView to be selected to start new activity to populate food list
        timesListView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		Intent intent = new Intent(DisplayTimes.this, GeneratingScreen.class);
		        Bundle bundle = new Bundle();
		        bundle.putString("place", location);
				switch(position){
				case 0:
				        bundle.putString("time","Breakfast");
				        //need to input day here automatically
				        bundle.putInt("day", 4);
				        intent.putExtras(bundle);
						startActivity(intent);
						break;
				case 1:
				        bundle.putString("time","Lunch");
				        //need to input day here automatically
				        bundle.putInt("day", 4);
				        intent.putExtras(bundle);
						startActivity(intent);
						break;
				case 2:
				        bundle.putString("time","Dinner");
				        //need to input day here automatically
				        bundle.putInt("day", 4);
				        intent.putExtras(bundle);
						startActivity(intent);
						break;
				}
     	    }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_times, menu);
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