package cf.kuiprux.spbeat.gui.effect;

/*
 * drawable effect 체인 호출 클래스
 * 가장 마지막인 effect가 끝날때 체인 호출됨
 * 
 * T: 연속될 자식 클래스 type
 */
public interface IEffectResult extends IAnimatable {
	
	//체인 무시하고 시작
	void start();
}
