package tribe.dining;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressLint("NewApi")
public class CafBreakfast extends Activity {
	
	ArrayList<String> foodArray = null;
	private ListView lv; 
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caf_breakfast);
        getActionBar().setDisplayHomeAsUpEnabled(true);
          
        Intent intent = getIntent();
        foodArray = intent.getStringArrayListExtra("foodarray");
        lv = (ListView)findViewById(R.id.cafBreakfast);
        lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, foodArray));
        
    	//intialize ListView for food items, as well as populate arraylist
    	// final MenuScraper scraper = new MenuScraper();
    	//ListView lv;
    	
    	
    	//TestLoader menuloader = new TestLoader();
    	//ArrayAdapter<String> adapter = new ArrayAdapter<String>(CafBreakfast.this, android.R.layout.simple_list_item_1, foodArray);
    	//menuloader.execute("Commons","Breakfast",4);
    	//foodArray.toArray();
    	
    	//lv = (ListView)findViewById(R.id.cafMealTime);
        //lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, foodArray));
    
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_caf_breakfast, menu);
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
