package cf.kuiprux.spbeat.gui.effect;

import cf.kuiprux.spbeat.gui.EasingType;

/*
 * drawable effect 체인 호출 클래스
 * 가장 마지막인 effect가 끝날때 체인 호출됨
 */
public interface IEffectResult<T extends IEffectResult<?>> {
	
	void expire();
	
	default T fadeIn(EasingType type, float duration) {
		return fadeTo(1, type, duration);
	}
	
	default T fadeOut(EasingType type, float duration) {
		return fadeTo(0, type, duration);
	}
	
	T fadeTo(float opacity, EasingType type, float duration);
	
	default T moveToRelativeX(float x, EasingType type, float duration) {
		return moveToRelative(x, 0, type, duration);
	}
	
	default T moveToRelativeY(float y, EasingType type, float duration) {
		return moveToRelative(0, y, type, duration);
	}
	
	T moveToRelative(float x, float y, EasingType type, float duration);
	
	T rotateTo(float rotation, EasingType type, float duration);

	//체인 여부 상관 없이 그냥 실행
	void start();
	
	//모든 effect가 끝났는지 확인
	boolean isAllEnd(float currentTime);
	
}
