package app.conference;

/**
* Main - Test the functions of the conference class, who receive the talks input and then process the input
* to schedule talks into a track, after output the tracks and talks.
* @author Bruno Amorim
*/
public class Solution {

    public static void main(String[] args) {
    	
        //Create a ConferenceMeeting Object.
        ConferenceMeeting conferenceMeeting = new ConferenceMeeting();

        //Process the input test file into the Object.
        conferenceMeeting.readTalksFile(TrackConstants.TALKS_INPUT_TEST_FILE);

        //Get the total numbers of Tracks that is needed to schedule all of the talks.
        int numberOfTracks = conferenceMeeting.getCountTracks();
        int iStartTalk = 0;

        //Import the Talks to Tracks.
        for(int countTracks = 0; countTracks <numberOfTracks; countTracks++) {
            iStartTalk = conferenceMeeting.importTalksToTracks(countTracks, conferenceMeeting.getTrackTalks(), conferenceMeeting.getCountTracks(), iStartTalk, conferenceMeeting.getCountTalks());
        }

        //Print the scheduled tracks and talks.
        conferenceMeeting.printTalksAndTracks(conferenceMeeting.getTrackTalks());

    }
}
