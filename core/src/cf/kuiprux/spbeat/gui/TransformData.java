package cf.kuiprux.spbeat.gui;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Transform;

//origin, translate, scale, rotation 보관
public class TransformData {
	
	private float originX;
	private float originY;
	
	private float translateX;
	private float translateY;
	
	private float scaleX;
	private float scaleY;
	
	private float rotation;
	
	//applyTransform 으로 생성
	protected TransformData() {
		/*
		this.translateX = 0;
		this.translateY = 0;
		this.scaleX = 0;
		this.scaleY = 0;
		this.rotation = 0;
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

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public void setTranslateX(float translateX) {
		this.translateX = translateX;
	}

	public void setTranslateY(float translateY) {
		this.translateY = translateY;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

	public void setOriginX(float originX) {
		this.originX = originX;
	}

	public void setOriginY(float originY) {
		this.originY = originY;
	}

	public Matrix4 toMatrix4(){
		return new Matrix4();
	}
	
	// TransformData 생성
	public static TransformData applyTransform(float originX, float originY, float translateX, float translateY, float scaleX, float scaleY, float rotation) {
		TransformData data = new TransformData();

		data.originX = originX;
		data.originY = originY;

		data.translateX = translateX;
		data.translateY = translateY;

		data.scaleX = scaleX;
		data.scaleY = scaleY;

		data.rotation = rotation;

		return data;
	}

	public TransformData clone() {
		TransformData transformData = new TransformData();

		transformData.originX = originX;
		transformData.originY = originY;

		transformData.translateX = translateX;
		transformData.translateY += translateY;

		transformData.scaleX = scaleX;
		transformData.scaleY = scaleY;

		transformData.rotation = rotation;

		return transformData;
	}

	public TransformData add(TransformData data) {
		TransformData transformData = new TransformData();

		originX += data.originX;
		originY += data.originY;

		translateX += data.translateX;
		translateY += data.translateY;

		scaleX += data.scaleX;
		scaleY += data.scaleY;

		rotation += data.rotation;

		return transformData;
	}
}
