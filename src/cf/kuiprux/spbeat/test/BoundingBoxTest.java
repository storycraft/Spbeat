package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

import cf.kuiprux.spbeat.gui.IDrawable;
import cf.kuiprux.spbeat.gui.containers.FixedContainer;
import org.junit.jupiter.api.Test;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.SimpleGame;
import cf.kuiprux.spbeat.SpBeAt;
import cf.kuiprux.spbeat.WindowHost;
import cf.kuiprux.spbeat.gui.AlignMode;
import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.containers.SimpleContainer;
import cf.kuiprux.spbeat.gui.element.Shape;
import cf.kuiprux.spbeat.gui.element.Square;
import cf.kuiprux.spbeat.gui.element.Triangle;

public class BoundingBoxTest extends SimpleGame {
	
	private Square movingBox;

	public BoundingBoxTest() {
		super("boundingbox test");
	}
	

	@Test
	void test() {
		
		BoundingBoxTest game = new BoundingBoxTest();
		
		try (WindowHost window = new WindowHost(game)){
			window.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		super.init(container);

		Container testContainer = new SimpleContainer();
		Container fixed = new FixedContainer(0, 0, 200, 200);
		
		movingBox = new Square();
		
		movingBox.setLocation(0, 0);
		movingBox.setWidth(100);
		movingBox.setHeight(100);

		Square staticBox = new Square();

		staticBox.setLocation(0, 0);
		staticBox.setWidth(100);
		staticBox.setHeight(100);

		staticBox.setColor(Color.gray);

		movingBox.setColor(Color.magenta);
		movingBox.setOrigin(AlignMode.CENTRE);
		movingBox.setRotation(50f);
		movingBox.setScale(2, 2);
		
		Shape box2 = new Triangle();
		
		box2.setLocation(0, 0);
		box2.setWidth(100);
		box2.setHeight(100);
		
		box2.setColor(Color.green);
		box2.setAnchor(AlignMode.CENTRE);

		testContainer.setLocation(100, 100);
		fixed.setLocation(300, 300);
		
		testContainer.addChild(movingBox);
		testContainer.addChild(box2);

		fixed.addChild(staticBox);

		addChild(fixed);
		addChild(testContainer);
		
		onLoaded();

		System.out.println(testContainer.getX() + " " + testContainer.getY());
		System.out.println(testContainer.getWidth() + " " + testContainer.getHeight());

		System.out.println(fixed.getX() + " " + fixed.getY());
		System.out.println(fixed.getWidth() + " " + fixed.getHeight());
	}

	//업데이트 함수
	@Override
	public void updateInternal(int delta) {
		//movingBox.setRotation((movingBox.getRotation() + 0.25f) % 360);
		
		//movingBox.setLocation((float) Math.random() * 500, (float) Math.random() * 500);
	}

	//그리기 함수
	@Override
	public void drawInternal(Graphics graphics) {
		graphics.setColor(Color.white);
		for (IDrawable child : getChildren()) {
			Rectangle box = child.getBoundingBox();
			graphics.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
		}
	}
}