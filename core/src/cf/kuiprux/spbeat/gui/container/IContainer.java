package cf.kuiprux.spbeat.gui.container;

import java.util.List;

public interface IContainer<T> {

    //복사본
    //concurrent 발생 가능한 작업에 사용
    List<T> getChildren();

    void update(int delta);

    //자식 추가가 되었을시 true 반환
    boolean addChild(T drawable);

    //자식 제거 되었을시 true 반환
    boolean removeChild(T drawable);

    boolean containsChild(T drawable);
}
