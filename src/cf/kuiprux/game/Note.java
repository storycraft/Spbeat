package cf.kuiprux.game;

public class Note {
	//노트가 보일 버튼 위치
	private int buttonIndex;
	//클릭해야 되는 시간
	private int exactTime;
	//클릭한 시간
	private int pressedTime;
	
	public Note(int buttonIndex, int exactTime) {
		this.buttonIndex = buttonIndex;
		this.exactTime = exactTime;
	}

	public int getPressedTime() {
		return pressedTime;
	}

	public int getExactTime() {
		return exactTime;
	}

	public int getButtonIndex() {
		return buttonIndex;
	}
}
