package cf.kuiprux.spbeat.gui.effect;

/*
 * drawable effect 체인 호출 클래스
 * 가장 마지막인 effect가 끝날때 체인 호출됨
 */
public interface IEffectResult<T extends IEffectResult<?>> extends IAnimatable<T> {
	
	//체인 무시하고 시작
	void start();
}
