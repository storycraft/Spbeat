package cf.kuiprux.spbeat.test;

import static org.junit.jupiter.api.Assertions.*;

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
		
		movingBox = new Square();
		
		movingBox.setLocation(100, 100);
		movingBox.setWidth(100);
		movingBox.setHeight(100);
		
		movingBox.setColor(Color.magenta);
		
		Shape box2 = new Triangle();
		
		box2.setLocation(100, 500);
		box2.setWidth(100);
		box2.setHeight(100);
		
		box2.setColor(Color.green);
		
		System.out.println(box2.getY());
		
		testContainer.setLocation(10, 100);
		
		testContainer.addChild(movingBox);
		testContainer.addChild(box2);
		
		addChild(testContainer);
		
		onLoaded();
		
		System.out.println(testContainer.getX() + " " + testContainer.getY());
		System.out.println(testContainer.getWidth() + " " + testContainer.getHeight());
	}

	//업데이트 함수
	@Override
	public void updateInternal(int delta) {
		movingBox.setLocation((float) Math.random() * 500, (float) Math.random() * 500);
	}

	//그리기 함수
	@Override
	public void drawInternal(Graphics graphics) {
		graphics.setColor(Color.white);
		for (Drawable child : getChildren()) {
			Rectangle box = child.getBoundingBox();
			graphics.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
		}
	}
}