package cf.kuiprux.spbeat.game.loader;

import cf.kuiprux.spbeat.game.MainThreadExecutor;
import cf.kuiprux.spbeat.gui.font.FontManager;
import cf.kuiprux.spbeat.util.AsyncTask;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.nio.file.Path;

public class FontLoader implements Loader {

    private FontManager fontManager;

    public FontLoader(FontManager fontManager){
        this.fontManager = fontManager;
    }

    @Override
    public AsyncTask<Void> loadAll(Path path) {
        return new AsyncTask<>(new AsyncTask.AsyncCallable<Void>() {
            @Override
            public Void get() {
                Font defaultFont = new Font("Arial", Font.PLAIN, 32);
                UnicodeFont font = new UnicodeFont(defaultFont);
                font.getEffects().add(new ColorEffect(java.awt.Color.white));
                font.addAsciiGlyphs();
                MainThreadExecutor.addTask(() -> {
                    try {
                        font.loadGlyphs();
                    } catch (SlickException e) {
                        System.out.println("폰트 로드가 실패 했습니다. " + e.getLocalizedMessage());
                    }
                });

                fontManager.addFont(font);

                return null;
            }
        });
    }
}
