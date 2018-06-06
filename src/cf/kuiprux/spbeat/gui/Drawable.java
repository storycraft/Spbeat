package cf.kuiprux.spbeat.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import cf.kuiprux.spbeat.gui.effect.IDrawEffect;
import cf.kuiprux.spbeat.gui.effect.IEffectResult;

public abstract class Drawable implements IEffectResult<Drawable.DrawableEffectResult> {
	
	private float x;
	private float y;
	
	//위치 기준점
	private AlignMode anchor;
	
	//회전 / 확대시 기준점
	private AlignMode origin;
	
	private float rotation;
	private float scaleX;
	private float scaleY;
	
	//0 ~ 1
	private float opacity;
	private boolean visible;
	
	private Container parent;
	
	private TransformData transformData;
	
	//false 일시 transformData 객체 새로고침
	private boolean transformValid;
	
	private boolean loaded;
	
	private List<IDrawEffect> currentEffectList;
	private List<IEffectResult<?>> effectResultList;
	
	public Drawable() {
		this.loaded = false;
		
		this.x = 0;
		this.y = 0;
		
		this.opacity = 1;
		
		this.anchor = AlignMode.DEFAULT;
		this.origin = AlignMode.DEFAULT;
		
		this.rotation = 0;
		this.scaleX = 1;
		this.scaleY = 1;
		
		this.transformValid = false;
		this.visible = true;
		
		this.currentEffectList = new ArrayList<>();
		this.effectResultList = new ArrayList<>();
	}
	
	/*
	 * getter / setter 구역 시작
	 */
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	//그리기용 위치 (transform 속성 미 적용)
	public float getDrawX() {
		return getX() - getDrawAnchorX() + (getParent() != null ? getParent().getDrawX() : 0);
	}
	
	
	public float getDrawY() {
		return getY() - getDrawAnchorY() + (getParent() != null ? getParent().getDrawY() : 0);
	}
	
	public float getDrawOriginX() {
		return getWidth() * getOrigin().getXOffset();
	}
	
	public float getDrawOriginY() {
		return getHeight() * getOrigin().getYOffset();
	}
	
	public float getDrawAnchorX() {
		return getDrawWidth() * getAnchor().getXOffset();
	}
	
	public float getDrawAnchorY() {
		return getDrawHeight() * getAnchor().getYOffset();
	}
	
	public float getOpacity() {
		return opacity;
	}
	
	public TransformData getTransformData() {
		if (transformData == null || !transformValid)
			return transformData = computeTransform();
		
		return transformData;
	}
	
	public AlignMode getAnchor() {
		return anchor;
	}
	
	public AlignMode getOrigin() {
		return origin;
	}
	
	public float getRotation() {
		return rotation;
	}
	
	public float getScaleX() {
		return scaleX;
	}
	
	public float getScaleY() {
		return scaleY;
	}
	
	public void setX(float x) {
		if (this.x == x)
			return;
		this.x = x;
		
		sendParentUpdate();
	}
	
	public void setY(float y) {
		if (this.y == y)
			return;
		this.y = y;
		
		sendParentUpdate();
	}
	
	public void setLocation(float x, float y) {
		if (this.x == x && this.y == y)
			return;
		
		this.x = x;
		this.y = y;
		
		sendParentUpdate();
	}
	
	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
	
	public void setOrigin(AlignMode mode) {
		if (mode == this.origin)
			return;
		
		if (mode == null)
			mode = AlignMode.DEFAULT;
		
		this.origin = mode;
	}
	
	public void setAnchor(AlignMode mode) {
		if (mode == this.anchor)
			return;
		
		if (mode == null)
			mode = AlignMode.DEFAULT;
		
		this.anchor = mode;
		sendParentUpdate();
	}
	
	public void setRotation(float angle) {
		if (angle == this.rotation)
			return;
		
		this.rotation = angle;
		sendParentUpdate();
		transformValid = false;
	}
	
	public void setScaleX(float scaleX) {
		if (scaleX == this.scaleX)
			return;
		
		this.scaleX = scaleX;
		sendParentUpdate();
		transformValid = false;
	}
	
	public void setScaleY(float scaleY) {
		if (scaleY == this.scaleY)
			return;
		
		this.scaleY = scaleY;
		transformValid = false;
	}
	
	public void setScale(float scaleX, float scaleY) {
		if (scaleX == this.scaleX && scaleY == this.scaleY)
			return;
		
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		sendParentUpdate();
		transformValid = false;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean flag) {
		this.visible = flag;
	}
	
	public abstract float getWidth();
	public abstract float getHeight();
	/*
	 * 이므로 width / height setter는 따로 선언할 필요가 없게함
	 * 필요 할 경우 따로 선언
	 */
	
	
	//그리기용 크기 (transform 속성 미 적용)
	public float getDrawWidth() {
		return getWidth();
	}
	
	public float getDrawHeight() {
		return getHeight();
	}
	
	public abstract Rectangle getBoundingBox();
	
	public void sendParentUpdate() {
		if (isLoaded() && getParent() != null)
			getParent().onChildUpdate(this);
	}
	
	public Container getParent() {
		return parent;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	//Parent Container에서 제거
	public void expire() {
		if (getParent() == null)
			return;
		
		getParent().removeChild(this);
	}

	//Transform 행렬 계산
	public TransformData computeTransform() {
		float originX = getDrawX() + getDrawOriginX();
		float originY = getDrawY() + getDrawOriginY();
		
		TransformData transformData = TransformData.applyTransform(originX, originY, 0f, 0f, getScaleX(), getScaleY(), (float) Math.toRadians(getRotation()));

		return this.transformData = transformData;
	}
	
	/*
	 * getter 구역 끝
	 */
	
	/*
	 * 이벤트 구역 시작
	 */
	
	//container에 자식으로 넣어졌을시 호출
	protected void onAdded(Container container) {
		//다른 parent에 소속되어 있을시 제거
		if (parent != null)
			expire();
		
		parent = container;
	}
	
	//해당 Drawable이 로드(update와 draw가 호출 될 수 있는 상태) 될때
	protected void onLoaded() {
		loaded = true;
	}
	
	//해당 Drawable이 언 로드(update와 draw가 호출 될 수 없는 상태) 될때
	protected void onUnloaded() {
		loaded = false;
	}
	
	//container에서 제거 되었을 시 호출
	protected void onRemoved() {
		parent = null;
	}
	
	/*
	 * 이벤트 구역 끝
	 */
	
	//graphics에 TransformData 적용
	protected void applyTransform(Graphics graphics) {
		TransformData transformData = getTransformData();
		
		transformData.applyTransform(graphics);
	}
	
	//속성 적용
	protected void applyProperties(Graphics graphics) {
		
	}
	
	public abstract void update(int delta);
	
	public abstract void draw(Graphics graphics);
	
	//effect 적용 공간
	
	protected void addEffectResult(IEffectResult<?> result) {
		if (effectResultList.contains(result))
			return;
		
		effectResultList.add(result);
	}
	
	protected void removeEffectResult(IEffectResult<?> result) {
		if (!effectResultList.contains(result))
			return;
		
		effectResultList.remove(result);
		result.start();
	}
	
	protected void addEffect(IDrawEffect result) {
		if (currentEffectList.contains(result))
			return;
		
		currentEffectList.add(result);
	}
	
	protected void removeEffect(IDrawEffect result) {
		if (!currentEffectList.contains(result))
			return;
		
		currentEffectList.remove(result);
	}

	@Override
	public DrawableEffectResult fadeTo(float opacity, EasingType type, float duration) {
		return new DrawableEffectResult().fadeTo(opacity, type, duration);
	}

	@Override
	public DrawableEffectResult moveToRelative(float x, float y, EasingType type, float duration) {
		return new DrawableEffectResult().moveToRelative(x, y, type, duration);
	}

	@Override
	public DrawableEffectResult rotateTo(float rotation, EasingType type, float duration) {
		return new DrawableEffectResult().rotateTo(rotation, type, duration);
	}
	
	public class DrawableEffectResult implements IEffectResult<DrawableEffectResult> {
		
		private List<IDrawEffect> effectList;
		private float lastEndTime;
		
		public DrawableEffectResult() {
			this.effectList = new ArrayList<>();
			this.lastEndTime = 0;
		}

		@Override
		public void expire() {
			Drawable.this.expire();
		}

		@Override
		public DrawableEffectResult fadeTo(float opacity, EasingType type, float duration) {
			return null;
		}

		@Override
		public DrawableEffectResult moveToRelative(float x, float y, EasingType type, float duration) {
			return null;
		}

		@Override
		public DrawableEffectResult rotateTo(float rotation, EasingType type, float duration) {
			return null;
		}
		
		private void addEffectQueue(IDrawEffect effect) {
			if (effectList.contains(effect))
				return;
			
			effectList.add(effect);
			
			lastEndTime = Math.max(effect.getEndTime(), lastEndTime);
		}
		
		@Override
		public void start() {
			for (IDrawEffect effect : effectList) {
				addEffect(effect);
			}
		}

		@Override
		public boolean isAllEnd(float currentTime) {
			return currentTime > lastEndTime;
		}
		
	}
}
