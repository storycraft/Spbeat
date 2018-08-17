package cf.kuiprux.spbeat.game.loader;

import cf.kuiprux.spbeat.game.MainThreadExecutor;
import cf.kuiprux.spbeat.gui.font.FontManager;
import cf.kuiprux.spbeat.util.AsyncTask;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FontLoader implements Loader {

    public static final Path FONT_RES_PATH;

    static {
        FONT_RES_PATH = Paths.get("res", "font");
    }

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
                UnicodeFont defaultUnicodeFont = new UnicodeFont(defaultFont);

                Font nanumFont = null;
                try {
                    Font.createFont(Font.TRUETYPE_FONT, path.resolve("NanumBarunGothic.ttf").toFile());
                    nanumFont = new Font("나눔바른고딕", Font.PLAIN, 32);
                } catch (Exception e) {
                    System.out.println("폰트 로드가 실패 했습니다. " + e.getLocalizedMessage());
                }
                UnicodeFont nanumUnicodeFont = new UnicodeFont(nanumFont);

                nanumUnicodeFont.getEffects().add(new ColorEffect(java.awt.Color.white));
                defaultUnicodeFont.getEffects().add(new ColorEffect(java.awt.Color.white));

                defaultUnicodeFont.addAsciiGlyphs();
                nanumUnicodeFont.addAsciiGlyphs();

                MainThreadExecutor.addTask(() -> {
                    try {
                        defaultUnicodeFont.loadGlyphs();
                        nanumUnicodeFont.loadGlyphs();
                    } catch (SlickException e) {
                        System.out.println("폰트 로드가 실패 했습니다. " + e.getLocalizedMessage());
                    }
                });

                fontManager.addFont(defaultUnicodeFont);
                fontManager.addFont(nanumUnicodeFont);

                return null;
            }
        });
    }
}
