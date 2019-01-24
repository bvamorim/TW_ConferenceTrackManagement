package app.conference;

/**
* Talk - Class that keeps all data of its talk
* @author Bruno Amorim
*/
public class Talk {

    //private variables
    private int key;	
    private String title;
    private String titleNetworking;
    private String titleLunch;
    private String titleTrack;    
    private String timeSession;
    private int timeMinutes;    
    private boolean FlagLunch = false;
    private boolean FlagNetworking = false;        
    
    //constructor
    Talk(int pMinutes, String pTtitle, int pIid)
    {
        this.timeMinutes = pMinutes;
        this.title = pTtitle;
        this.key = pIid;

    }

	/**
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(int key) {
		this.key = key;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the titleNetworking
	 */
	public String getTitleNetworking() {
		return titleNetworking;
	}

	/**
	 * @param titleNetworking the titleNetworking to set
	 */
	public void setTitleNetworking(String titleNetworking) {
		this.titleNetworking = titleNetworking;
	}

	/**
	 * @return the titleLunch
	 */
	public String getTitleLunch() {
		return titleLunch;
	}

	/**
	 * @param titleLunch the titleLunch to set
	 */
	public void setTitleLunch(String titleLunch) {
		this.titleLunch = titleLunch;
	}

	/**
	 * @return the titleTrack
	 */
	public String getTitleTrack() {
		return titleTrack;
	}

	/**
	 * @param titleTrack the titleTrack to set
	 */
	public void setTitleTrack(String titleTrack) {
		this.titleTrack = titleTrack;
	}

	/**
	 * @return the timeSession
	 */
	public String getTimeSession() {
		return timeSession;
	}

	/**
	 * @param timeSession the timeSession to set
	 */
	public void setTimeSession(String timeSession) {
		this.timeSession = timeSession;
	}

	/**
	 * @return the timeMinutes
	 */
	public int getTimeMinutes() {
		return timeMinutes;
	}

	/**
	 * @param timeMinutes the timeMinutes to set
	 */
	public void setTimeMinutes(int timeMinutes) {
		this.timeMinutes = timeMinutes;
	}

	/**
	 * @return the flagLunch
	 */
	public boolean isFlagLunch() {
		return FlagLunch;
	}

	/**
	 * @param flaglunch the flagLunch to set
	 */
	public void setFlagLunch(boolean flagLunch) {
		FlagLunch = flagLunch;
	}

	/**
	 * @return the flagNetworking
	 */
	public boolean isFlagNetworking() {
		return FlagNetworking;
	}

	/**
	 * @param flagnetworking the flagNetworking to set
	 */
	public void setFlagNetworking(boolean flagNetworking) {
		FlagNetworking = flagNetworking;
	}    
    
}
