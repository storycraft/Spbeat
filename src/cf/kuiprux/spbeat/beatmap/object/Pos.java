package cf.kuiprux.spbeat.beatmap.object;

public class Pos {
	public int x;
	public int y;
	
	public Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Pos)
			return x == ((Pos)object).x && y == ((Pos)object).y;
		return false;
	}
}
