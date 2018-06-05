package cf.kuiprux.spbeat.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Transform;

//origin, translate, scale, rotation 焊包
public class TransformData {
	
	private float originX;
	private float originY;
	
	private float translateX;
	private float translateY;
	
	private float scaleX;
	private float scaleY;
	
	private float rotation;
	
	private Transform slickTransform;
	
	//applyTransform 栏肺 积己
	private TransformData() {
		/*
		this.translateX = 0;
		this.translateY = 0;
		this.scaleX = 0;
		this.scaleY = 0;
		this.rotation = 0;
		this.slickTransform = null;
		*/
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getTranslateX() {
		return translateX;
	}

	public float getTranslateY() {
		return translateY;
	}

	public float getScaleX() {
		return scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public float getOriginX() {
		return originX;
	}
	
	public float getOriginY() {
		return originY;
	}
	
	public Transform getSlickTransform() {
		return slickTransform;
	}
	
	public void applyTransform(Graphics graphics) {
		if (getRotation() % 360 != 0)
			graphics.rotate(getOriginX(), getOriginY(), (float) Math.toDegrees(getRotation()));
		
		if (getScaleX() != 1 && getScaleY() != 1) {
			graphics.translate(getOriginX(), getOriginY());
			graphics.scale(getScaleX(), getScaleY());
			graphics.translate(-getOriginX(), -getOriginY());
		}
	}

	private Transform toSlickTransform() {
		Transform transform = new Transform();
		
		if (getScaleX() != 1 || getScaleY() != 1) {
			transform = transform.concatenate(Transform.createTranslateTransform(getOriginX(), getOriginY()));
			transform = transform.concatenate(Transform.createScaleTransform(getScaleX(), getScaleY()));
			transform = transform.concatenate(Transform.createTranslateTransform(-getOriginX(), -getOriginY()));
		}
		if (getRotation() % 360 != 0)
			transform = transform.concatenate(Transform.createRotateTransform(getRotation(), getOriginX(), getOriginY()));
		
		return transform;
	}
	
	
	// TransformData 积己
	public static TransformData applyTransform(float originX, float originY, float translateX, float translateY, float scaleX, float scaleY, float rotation) {
		TransformData data = new TransformData();
		
		data.originX = originX;
		data.originY = originY;
		
		data.translateX = translateX;
		data.translateY = translateY;
		
		data.scaleX = scaleX;
		data.scaleY = scaleY;
		
		data.rotation = rotation;
		
		data.slickTransform = data.toSlickTransform();
		
		return data;
	}
}
