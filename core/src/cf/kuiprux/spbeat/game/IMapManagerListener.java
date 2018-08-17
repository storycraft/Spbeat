package cf.kuiprux.spbeat.game;

import cf.kuiprux.spbeat.game.beatmap.Beatmap;

public interface IMapManagerListener {
    void onAdded(Beatmap map);
    void onRemoved(Beatmap map);
}
