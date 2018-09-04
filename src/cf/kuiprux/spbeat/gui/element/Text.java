package cf.kuiprux.spbeat.gui.element;

import cf.kuiprux.spbeat.gui.Container;
import cf.kuiprux.spbeat.gui.TransformData;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Text extends Sprite {

    private Font font;
    private String text;

    private float textWidth;
    private float textHeight;

    private float fontSize;

    public Text(Font font, String text){
        this.font = font;
        this.text = text;

        this.fontSize = 16;
    }

    public Text(){
        this(null, "");
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public String getText() {
        return text;
    }

    public float getFontSize() {
        return fontSize;
    }

    public void setFontSize(float fontSize) {
        this.fontSize = fontSize;
    }

    public void setText(String text) {
        this.text = text;

        if (getFont() != null) {
            this.textWidth = getFont().getWidth(text);
            this.textHeight = getFont().getHeight(text);
        }
    }

    @Override
    public float getWidth() {
        return textWidth;
    }

    @Override
    public float getHeight() {
        return textHeight;
    }

    @Override
    public Rectangle getBoundingBox() {
        Shape shape = new Rectangle(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight()).transform(getTransformData().getSlickTransform());
        return new Rectangle(shape.getMinX(), shape.getMinY(), shape.getWidth(), shape.getHeight());
    }

    @Override
    public void onAdded(Container container) {
        super.onAdded(container);

        if (getFont() == null){

        }
    }

    @Override
    public void update(int delta) {

    }

    public float getOriginalScale(){
        return getFontSize() / getFont().getLineHeight();
    }

    @Override
    public float getDrawScaleX() {
        return super.getDrawScaleX() * getOriginalScale();
    }

    @Override
    public float getDrawScaleY() {
        return super.getDrawScaleY() * getOriginalScale();
    }

    @Override
    public void draw(Graphics graphics) {
        if (getText() != "") {
            applyProperties(graphics);
            applyTransform(graphics);

            graphics.setDrawMode(getDrawMode().getIntMode());

            getFont().drawString(getDrawX(), getDrawY(), getText(), getColor());
        }
    }
}
