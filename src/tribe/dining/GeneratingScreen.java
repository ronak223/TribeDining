package tribe.dining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
		
		String url = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		//sets URL according to params, using direct links
		String date = dateFormat.format(calendar.getTime());
		//String sadlerBrunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm";
		String sadlerLunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String sadlerBreakfastURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC&MealID=1&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String sadlerDinnerURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC&MealID=17&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String marketplaceBreakfastURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/Marketplace.htm?LocationName=Marketplace&MealID=1&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String marketplaceLunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/Marketplace.htm?LocationName=Marketplace&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String marketplaceDinnerURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/Marketplace.htm?LocationName=Marketplace&MealID=17&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		//String commonsBrunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm";
		String commonsLunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String commonsBreakfastURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company&MealID=1&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String commonsDinnerURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		
		if(location.equals("Sadler")){
			/**if(time.equals("Brunch")){
				url = sadlerBrunchURL;
			}*/
			if(time.equals("Lunch")){
				url = sadlerLunchURL;
			}
			else if(time.equals("Breakfast")){
				url = sadlerBreakfastURL;
			}
			else if(time.equals("Dinner")){
				url = sadlerDinnerURL;
			}
		}
		else if(location.equals("Commons")){
			/**if(time.equals("Brunch")){
				url = commonsBrunchURL;
			}*/
			if(time.equals("Lunch")){
				url = commonsLunchURL;
			}
			else if(time.equals("Breakfast")){
				url = commonsBreakfastURL;
			}
			else if(time.equals("Dinner")){
				url = commonsDinnerURL;
			}
		
		}
		else if(location.equals("Marketplace")){
			if(time.equals("Lunch")){
				url = marketplaceLunchURL;
			}
			else if(time.equals("Breakfast")){
				url = marketplaceBreakfastURL;
			}
			else if(time.equals("Dinner")){
				url = marketplaceDinnerURL;
			}
		
		}
		
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

}
