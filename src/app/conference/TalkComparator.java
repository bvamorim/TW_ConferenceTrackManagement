package app.conference;

import java.util.Comparator;

/**
* TalkComparator - Sort the talk in (descending) order based on the talktime in minutes.
* @author Bruno Amorim
*/
public class TalkComparator implements Comparator<Talk>{

    @Override
    public int compare(Talk pTalks1, Talk pTalks2) {
        if(pTalks1.getTimeMinutes() < pTalks2.getTimeMinutes()){
            return 1;
        } else {
            return -1;
        }
    }
    
}
