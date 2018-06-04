package cf.kuiprux.spbeat.gui;

public class AlignMode {
	
	//왼쪽 / 오른쪽 위
	public static final AlignMode LEFT_TOP = new AlignMode(0, 0);
	public static final AlignMode RIGHT_TOP = new AlignMode(1f, 0);
	
	//왼쪽 / 오른쪽서 중앙
	public static final AlignMode LEFT_MIDDLE = new AlignMode(0, 0.5f);
	public static final AlignMode RIGHT_MIDDLE = new AlignMode(1, 0.5f);
	
	//정 중앙
	public static final AlignMode CENTRE = new AlignMode(0.5f, 0.5f);
	
	//위 / 아래서 중앙
	public static final AlignMode TOP_MIDDLE = new AlignMode(0.5f, 0);
	public static final AlignMode BOTTOM_MIDDLE = new AlignMode(0.5f, 1);
	
	//왼쪽 / 오른쪽 아래
	public static final AlignMode LEFT_BOTTOM = new AlignMode(0, 1);
	public static final AlignMode RIGHT_BOTTOM = new AlignMode(1, 1);
	
	public static final AlignMode DEFAULT = LEFT_TOP;
	
	private float xOffset;
	private float yOffset;
	
	public AlignMode(float xOffset, float yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public float getXOffset() {
		return xOffset;
	}
	
	public float getYOffset() {
		return yOffset;
	}
}
