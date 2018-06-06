package cf.kuiprux.spbeat.gui;

//진행도 그래프
public abstract class EasingType {
	
	public static final EasingType LINEAR = new EasingType() {
		@Override
		public float convertProgress(float progress) {
			return progress;
		}
	};
	
	//progress = 0 ~ 1 변환된 progress 값 반환
	public abstract float convertProgress(float progress);
}
