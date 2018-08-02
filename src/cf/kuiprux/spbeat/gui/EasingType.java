package cf.kuiprux.spbeat.gui;

//진행도 그래프
public abstract class EasingType {

	public static final EasingType LINEAR = new EasingType() {
		@Override
		public float convertProgress(float progress) {
			return progress;
		}
	};

	public static final EasingType EASE_IN_QUAD = new EasingType() {
		@Override
		public float convertProgress(float progress) {
			return progress * progress;
		}
	};

	public static final EasingType EASE_OUT_QUAD = new EasingType() {
		@Override
		public float convertProgress(float progress) {
			return progress * (2 - progress);
		}
	};
	
	//progress = 0 ~ 1 변환된 progress 값 반환
	public abstract float convertProgress(float progress);
}
