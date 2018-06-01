package cf.kuiprux;

public class Reference {

	//Screen size
	public static final int ORIGINAL_WIDTH = 2681;
	public static final int ORIGINAL_HEIGHT = 4766;
	
	public static final int ORIGINAL_BUTTON_WIDTH = 55;
	public static final int ORIGINAL_BUTTON_HEIGHT = 55;
	
	public static final double ORIGINAL_BUTTON_GAP = 11.8;
	public static final double ORIGINAL_FRAME_GAP = 6.3;
	public static final int ORIGINAL_PANEL_GAP = 14;

	//Gui size
	public static final int WIDTH = 1080;
	public static final int HEIGHT = 1920;

	public static final double BUTTON_WIDTH = (double) ORIGINAL_BUTTON_WIDTH*WIDTH/ORIGINAL_WIDTH;
	public static final double BUTTON_HEIGHT = (double) ORIGINAL_BUTTON_HEIGHT*HEIGHT/ORIGINAL_HEIGHT;
	
	public static final double BUTTON_GAP_WIDTH = (double) ORIGINAL_BUTTON_GAP*WIDTH/ORIGINAL_WIDTH;
	public static final double BUTTON_GAP_HEIGHT = (double) ORIGINAL_BUTTON_GAP*HEIGHT/ORIGINAL_HEIGHT;
	public static final double FRAME_GAP_WIDTH = (double) ORIGINAL_FRAME_GAP*WIDTH/ORIGINAL_WIDTH;
	public static final double FRAME_GAP_HEIGHT = (double) ORIGINAL_FRAME_GAP*HEIGHT/ORIGINAL_HEIGHT;
	public static final double PANEL_GAP_HEIGHT = (double) ORIGINAL_PANEL_GAP*HEIGHT/ORIGINAL_HEIGHT;
	
	public static final int PANEL_BUTTON_ROW = 4;
	public static final int PANEL_BUTTON_COLUMN = 4;
	
	public static final double BUTTON_START = (double) HEIGHT-(BUTTON_HEIGHT*4)-(BUTTON_GAP_HEIGHT*3)-(PANEL_GAP_HEIGHT);
	public static final double PANEL_HEIGHT = (double) BUTTON_START-PANEL_GAP_HEIGHT;
	
}
