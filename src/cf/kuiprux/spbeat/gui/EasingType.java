package cf.kuiprux.spbeat.gui;

//진행도 그래프
public abstract class EasingType {
	
	//progress = 0 ~ 1 변환된 progress 값 반환
	public abstract float convertProgress(float progress);
}
