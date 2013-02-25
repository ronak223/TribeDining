package tribe.dining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint({ "NewApi", "SimpleDateFormat" })
public class GeneratingScreen extends Activity implements Runnable {
	
	//TODO: Need to implement storage of menu by the week, so internet connection isn't necessary every time student wants menu.
	String location;
	String time;
	int day;
	Thread generatethread;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generating_screen);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		
		location = (String) bundle.get("place");
		time = (String) bundle.get("time");
		/* TODO: create day spinner with default set to today using calendar .get method */
		day = (Integer) bundle.get("day");
		
		//Pass in status variable
		generatethread = new Thread(this);
		generatethread.start();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_generating_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void run() {
		/*TODO: Create progress bar to show something is happening while app is filling array*/		
		ArrayList<String> foodArray = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String date = dateFormat.format(calendar.getTime());
		
		String url = buildURL(new String[]{location, date, time});
		
		//Document object to retreive to
		Document doc = null;
		//Number of attempts to connect to page
		int count = 5;
		
		while (count > 0) {
			count--;
			try {
				doc = Jsoup.connect(url).get();
				break;
			} catch (Exception e) {
				Log.w("debug", "Failed to get document. Retrying...");
			}
		}
		
		if (doc == null) {
			Log.e("debug", "Error retreiving document.");
			return; //TODO: Exit with message
		}
		
	    String output = doc.outerHtml(); 
	    
	    //System.out.println(output);
        BufferedReader bufReader = new BufferedReader(new StringReader(output));
        String line=null;
        
        // array with 7 spaces that will store the daily menus
        ArrayList<ArrayList<String>> days = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < 7; i++){
        	days.add(new ArrayList<String>());
        }
        //Represents the current day (0 = Sunday, 1 = Monday, ... , 6 = Saturday)
        int current = -1;
        //FileWriter fstreamOrig = new FileWriter("src/us/fabianism/MenuScraper/htmlout.txt");
		//BufferedWriter htmlout = new BufferedWriter(fstreamOrig);
    	String substring;
        // Continues until there are no more lines to read
        try {
			while( (line=bufReader.readLine()) != null )
			{
				// Removes spaces and quotations, for ease of use.
				line = line.replaceAll("\"","&");
				line = line.replaceAll("\\s","");
				
   
				
				if(line.length() >= 43){
					substring = line.substring(0, 42);
				}
				// iterates through the days so that items are added to the correct menu
			
				if (line.equals("<tdvalign=&top&class=&menuBorder&>") || (line.equals("<tdvalign=&top&class=&menuBorder&><imgsrc=&../../images/spacer.gif&width=&1&height=&1&alt=&&border=&0&/></td>"))){
					if(current == 7){
						current = 0;
					}
					else{
						current++;
					}
				}
				// Finds the menu item and appends it to the string for the given day
				else if (line.equals("<divclass=&menuTxt&>")){
					bufReader.readLine();
					bufReader.readLine();
					bufReader.readLine();
					line = bufReader.readLine();
					
					Document tempdoc = Jsoup.parse(line);
					days.get(current).add(tempdoc.body().text());
					
				}
				// checks to see if a new table is beginning. Sets the day back to sunday if one is
				else if(line.length() >= 44){
					substring = line.substring(0, 43);
					if(substring.equals("<tdbgcolor=&#cccccc&class=&ConceptTabText&>")){
						current = -1;
					}
				}
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        //TODO: On the off chance that the array doesn't fill up with menu items, need to restart thread until it is filled.
        foodArray = days.get(day);
    	Intent intent = new Intent(this, FoodListDisplay.class);
    	intent.putStringArrayListExtra("foodarray", foodArray);
    	startActivity(intent); 
        
        finish();
		
	}
	
	/**
	 * Abstracts building the request URL out of main execution
	 * @param components Extensible array, expecting first three values to be location, date, time
	 * @return constructed URL
	 */
	private String buildURL(String[] components) {
		final String BASE_URL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/";
		final String ORG_ID = "&OrgID=231624";
		final String STATIC_PARAMS = "&ShowPrice=False&ShowNutrition=True";
		
		HashMap<String, String> location_map = new HashMap<String, String>();
		HashMap<String, Integer> time_map = new HashMap<String, Integer>();
		
		location_map.put("Sadler", "SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC");
		location_map.put("Commons", "CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company");
		location_map.put("Marketplace", "Marketplace.htm?LocationName=Marketplace");
		time_map.put("Breakfast", 1);
		time_map.put("Lunch", 16);
		time_map.put("Dinner", 17);
			
		return BASE_URL + location_map.get(components[0]) + ORG_ID + "&Date=" + components[1] + "&MealID=" + time_map.get(components[2]) + STATIC_PARAMS;
	}

}
