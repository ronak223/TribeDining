package tribe.dining;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.os.Bundle;
import android.os.Message;



/**
 * Class that will scrape the html for a given page, and format a menu based on that page. Will only work
 * for College of William and Mary campusdish dining site.
 *
 *
 */
public class MenuScraper {
	
	//intialize with document var and String var
	Document doc = null;
	
	public MenuScraper(){
	}
	public ArrayList<String> getMenu(String location, String time, int day) throws IOException {
		
		
		String url = "";
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		
		String date = dateFormat.format(calendar.getTime());
		String sadlerBrunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm";
		String sadlerLunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String sadlerBreakfastURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC&MealID=1&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String sadlerDinnerURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/SadlerCenterRFoC.htm?LocationName=Sadler%20Center%20RFoC&MealID=17&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String marketplaceBreakfastURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/Marketplace.htm";
		String marketplaceLunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/Marketplace.htm?LocationName=Marketplace&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String marketplaceDinnerURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/Marketplace.htm?LocationName=Marketplace&MealID=17&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String commonsBrunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm";
		String commonsLunchURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String commonsBreakfastURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company&MealID=1&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		String commonsDinnerURL = "http://www.campusdish.com/en-US/CSMA/WilliamMary/Menus/CommonsFreshFoodCompany.htm?LocationName=Commons%20Fresh%20Food%20Company&MealID=16&OrgID=231624&Date=" + date + "&ShowPrice=False&ShowNutrition=True";
		
		if(location.equals("Sadler")){
			if(time.equals("Brunch")){
				url = sadlerBrunchURL;
			}
			else if(time.equals("Lunch")){
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
			if(time.equals("Brunch")){
				url = commonsBrunchURL;
			}
			else if(time.equals("Lunch")){
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
		
        //Downloads and reads (using a buffered reader) through an html document in a separate thread for http connection
		
		final String tempurl = url;
		Thread downloadThread = new Thread(){
			public void run() {
				Message msg = Message.obtain();
				msg.what = 1;
				try{
				 Bundle b = new Bundle();	
				 doc = Jsoup.connect(tempurl).get();
				 msg.setData(b);
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		};
		
		downloadThread.start();
		
        /** Document doc = Jsoup.connect(url).get(); */
        String output = doc.outerHtml(); 
        
		
		while(downloadThread.isAlive()){
			int useless = 0;
		}
			
		
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
        //htmlout.close();
        /** For debugging purposes. Outputs menus in a document*/
        // printToFile(days, location, time);
        return(days.get(day));
        
	
	}
	
    /** For debugging purposes. Outputs menus as strings to given 'file'
     * @throws IOException */
	public static void printToFile(ArrayList<ArrayList<String>> days, String location, String time) throws IOException{
		String file = ("src/us/fabianism/MenuScraper/" + location+ "/" + time +".txt");
		FileWriter fstream = new FileWriter(file);
		BufferedWriter out = new BufferedWriter(fstream);
		for(int i = 0; i < 7; i++){
        	out.write("\n\n");
        	if (i == 0){out.write("Sunday:");}
        	else if (i == 1){out.write("Monday:");}
        	else if (i == 2){out.write("Tuesday:");}
        	else if (i == 3){out.write("Wednesday:");}
        	else if (i == 4){out.write("Thursday:");}
        	else if (i == 5){out.write("Friday:");}
        	else if (i == 6){out.write("Saturday:");}
        	if(days.get(i) != null){
        		for(int j = 0; j < days.get(i).size(); j++){
        			out.write(days.get(i).get(j));
        			out.write("\n");
        		}
        	}
		}
		out.close();
	}

}