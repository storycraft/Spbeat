package cf.kuiprux.spbeat.gui.effect;

import cf.kuiprux.spbeat.gui.Drawable;
import cf.kuiprux.spbeat.gui.EasingType;

public interface IAnimatable {
    Drawable.DrawableEffectResult fadeTo(float opacity, EasingType type, int duration);

    Drawable.DrawableEffectResult fadeIn(EasingType type, int duration);

    Drawable.DrawableEffectResult fadeOut(EasingType type, int duration);

    Drawable.DrawableEffectResult moveToRelative(float x, float y, EasingType type, int duration);

    Drawable.DrawableEffectResult moveToRelativeX(float x, EasingType type, int duration);

    Drawable.DrawableEffectResult moveToRelativeY(float y, EasingType type, int duration);

    Drawable.DrawableEffectResult moveTo(float x, float y, EasingType type, int duration);

    Drawable.DrawableEffectResult moveToX(float x, EasingType type, int duration);

    Drawable.DrawableEffectResult moveToY(float y, EasingType type, int duration);

    Drawable.DrawableEffectResult rotateTo(float rotation, EasingType type, int duration);

    Drawable.DrawableEffectResult wait(int time);
}
