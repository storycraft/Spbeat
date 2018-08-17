package cf.kuiprux.spbeat.game.beatmap;

public interface INote {
    int getNoteIndex();

    float getExactTime();

    //노트가 나타나야하면 true 반환
    boolean isOnScreen(long time);
}
