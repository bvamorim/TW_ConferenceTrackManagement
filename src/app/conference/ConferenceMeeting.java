package app.conference;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

/**
* ConferenceMeeting - Class that has a list of methods to process the track/talks and print the result
* @author Bruno Amorim
*/
public class ConferenceMeeting {
    
    //private variables	
	private List<Talk> trackTalks;
	private int totalTrackInMinutes;
	private int countTracks;
	private int countTalks;
	
    // constructor
	ConferenceMeeting(){
        ArrayList<Talk> arrayList = new ArrayList<Talk>();
		this.trackTalks = arrayList;
    }	

    //getters and setters
	/**
	 * @return the trackTalks
	 */
	public List<Talk> getTrackTalks() {
		return trackTalks;
	}

	/**
	 * @param trackTalks the trackTalks to set
	 */
	public void setTrackTalks(List<Talk> trackTalks) {
		this.trackTalks = trackTalks;
	}

	/**
	 * @return the totalTrackInMinutes
	 */
	public int getTotalTrackInMinutes() {
		return totalTrackInMinutes;
	}

	/**
	 * @param totalTrackInMinutes the totalTrackInMinutes to set
	 */
	public void setTotalTrackInMinutes(int totalTrackInMinutes) {
		this.totalTrackInMinutes = totalTrackInMinutes;
	}

	/**
	 * @return the countTracks
	 */
	public int getCountTracks() {
		return countTracks;
	}

	/**
	 * @param countTracks the countTracks to set
	 */
	public void setCountTracks(int countTracks) {
		this.countTracks = countTracks;
	}

	/**
	 * @return the countTalks
	 */
	public int getCountTalks() {
		return countTalks;
	}

	/**
	 * @param countTalks the countTalks to set
	 */
	public void setCountTalks(int countTalks) {
		this.countTalks = countTalks;
	}


    //Methods
	/**
	 * @param pFileName the file and process into objects
	 */
    public void readTalksFile(String pFileName){
        int key =0;
        int numberOfTracks = 0;
        FileInputStream fileStream = null;

        //Open the test file
        try {
            fileStream = new FileInputStream(pFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));

        String stringLine;

        int intInMinutes;
        int totalInMinutes = 0;

        System.out.println("Test input :");
        System.out.println("");

        //Read all the lines of the File
        try {
            while ((stringLine = bufferedReader.readLine()) != null) {

                if(stringLine.contains("//") || stringLine.isEmpty()) {
                    continue;                	
                }

                key = key +1;
                System.out.println(stringLine);

                //Read the line and extract the informations to the variables.
                String title = stringLine.substring(0, stringLine.lastIndexOf(" "));
                String minutesString = stringLine.substring(stringLine.lastIndexOf(" ") + 1);
                String minutes = stringLine.replaceAll("\\D+", "");
                
                if("lightning".equals(minutesString)) {
                    intInMinutes = 5;
                    totalInMinutes = totalInMinutes + intInMinutes;
                } else {
                    intInMinutes = Integer.parseInt(minutes);
                    totalInMinutes = totalInMinutes + intInMinutes;
                }

                //Fill the talk obj with the input values
                Talk oneTalk = new Talk(intInMinutes, title, key);

                //Add this single talk to the list
                trackTalks.add(oneTalk);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Set the sum of talks.
        this.setCountTalks(key);

        //Set the total minutes of talks.
        this.setTotalTrackInMinutes(totalInMinutes);

        //Define the number of tracks
        Double totalInMinutesDouble =  totalInMinutes*1.0;
        Double numberOfTracksDouble =  totalInMinutesDouble/TrackConstants.TOTAL_TALKS_TRACK_MINUTES;

        double fracPart = numberOfTracksDouble % 1;
        double intPart = numberOfTracksDouble - fracPart;

        int leftMinutes = totalInMinutes - (int)intPart*TrackConstants.TOTAL_TALKS_TRACK_MINUTES.intValue();

        //if is 1.4, 1.5 or 1.8 will put the value of two tracks
        if (leftMinutes > 0) {
            numberOfTracks = (int) intPart + 1;
        } else {
            numberOfTracks = (int) intPart;
        }

        this.setCountTracks(numberOfTracks);

        // Sort all talks based on the talks-time in descending order.
        Collections.sort(trackTalks, new TalkComparator());

        //Close the test file
        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Write empty lines between the sections
        System.out.println("");
        System.out.println("");
    }

	/**
	 * @param pIndexTrackCount the index of the current track
	 * @param pTrackTalks the list of talks
	 * @param pCountTracks the total number o tracks
	 * @param pIndexStartTalk the initial talk
	 * @param pTotalTalkCount the total of talks
	 * @return iTalk Last converted talk into a track
	 */
    public int importTalksToTracks(int pIndexTrackCount, List<Talk> pTrackTalks, int pCountTracks, int pIndexStartTalk , int pTotalTalkCount){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm a");
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);

        int morningMinutes = TrackConstants.MORNING_TOTAL_MINUTES;
        int afternoonMinutes = TrackConstants.AFTERNOON_TOTAL_MINUTES;

        int iTalk;

        String trackTime;
        String trackTitle;

        for(iTalk=pIndexStartTalk; iTalk< pTotalTalkCount;iTalk++) {

            //Set the talks for Morning avaliable
            if (morningMinutes >= pTrackTalks.get(iTalk).getTimeMinutes()) {
                morningMinutes = morningMinutes - pTrackTalks.get(iTalk).getTimeMinutes();
                trackTime = simpleDateFormat.format(calendar.getTime()) + " " + pTrackTalks.get(iTalk).getTitle() + " " + pTrackTalks.get(iTalk).getTimeMinutes() + "min";
                pTrackTalks.get(iTalk).setTitle(trackTime);
                calendar.add(Calendar.MINUTE, pTrackTalks.get(iTalk).getTimeMinutes());
                trackTitle = "Track" + " " + (pIndexTrackCount + 1);
                pTrackTalks.get(iTalk).setTitleTrack(trackTitle);
            }
            
            if (morningMinutes < pTrackTalks.get(iTalk).getTimeMinutes()) {
                break;            	
            }

            if (morningMinutes > 0){
                continue;            	
            }

            if (morningMinutes <= 0) {
                break;
            }
            
        }

        pTrackTalks.get(iTalk).setFlagLunch(true);
        trackTime = "12:00 PM" + " " + " ** Lunch ** ";
        pTrackTalks.get(iTalk).setTitleLunch(trackTime);
        calendar.add(Calendar.MINUTE, 60);

        iTalk++;

        for(;iTalk< pTotalTalkCount;iTalk++) {
        	
        	//Set the talks for Afternoon avaliable
            if (afternoonMinutes >= pTrackTalks.get(iTalk).getTimeMinutes()) {
                afternoonMinutes = afternoonMinutes - pTrackTalks.get(iTalk).getTimeMinutes();
                trackTime = simpleDateFormat.format(calendar.getTime()) + " " + pTrackTalks.get(iTalk).getTitle() + " " + pTrackTalks.get(iTalk).getTimeMinutes() + "min";
                pTrackTalks.get(iTalk).setTitle(trackTime);
                calendar.add(Calendar.MINUTE, pTrackTalks.get(iTalk).getTimeMinutes());
                trackTitle = "Track" + " " + (pIndexTrackCount + 1);
                pTrackTalks.get(iTalk).setTitleTrack(trackTitle);
            }
            if (afternoonMinutes < pTrackTalks.get(iTalk).getTimeMinutes()) {
                break;
            }

            if (afternoonMinutes > 0) {
                continue;
            }

            if (afternoonMinutes <= 0) {
                break;            	
            }
            
        }

        if(pTotalTalkCount == (iTalk)) {
            --iTalk;
        }
        
        pTrackTalks.get(iTalk).setFlagNetworking(true);
        trackTime = "5:00 PM" + " " + " ** Networking ** ";
        pTrackTalks.get(iTalk).setTitleNetworking(trackTime);
        iTalk++;
        
        return iTalk;

    }


    /**
     * @param pTrackTalks the list of talks/tracks sorted
     * */
    public void printTalksAndTracks(List<Talk> pTrackTalks){

        System.out.println("Test output :");
        System.out.println("");
        String trackTitle = "";

        //Output the talk and tracks
        for(int iTrackCount=0; iTrackCount<pTrackTalks.size(); iTrackCount++)
        {

            //Print the Title of the track
            if(!trackTitle.equals(pTrackTalks.get(iTrackCount).getTitleTrack())) {
                System.out.println(pTrackTalks.get(iTrackCount).getTitleTrack() + ":");
                System.out.println("");
                trackTitle = pTrackTalks.get(iTrackCount).getTitleTrack();
            }

            //Print the talks title for this current Track
            System.out.println(pTrackTalks.get(iTrackCount).getTitle());

            //if the lunch flag is set output the Lunch title
            if(pTrackTalks.get(iTrackCount).isFlagLunch()) {
                System.out.println(pTrackTalks.get(iTrackCount).getTitleLunch());
            }

            //if the networking flag is set output the Lunch title
            if(pTrackTalks.get(iTrackCount).isFlagNetworking()) {
                System.out.println(pTrackTalks.get(iTrackCount).getTitleNetworking());
                
                // print extra empty lines.
                System.out.println("");
                System.out.println("");
            }

        }
    }

}


