package cf.kuiprux.spbeat.gui.font;

import org.newdawn.slick.Font;
import org.newdawn.slick.UnicodeFont;

import java.util.HashMap;
import java.util.Map;

public class FontManager {

    private String defaultFont;
    private Map<String, Font> fontMap;

    public FontManager(){
        this.fontMap = new HashMap<>();
    }

    public boolean addFont(String name, Font font){
        if (font == null)
            return false;

        if (contains(name))
            return false;

        fontMap.put(name, font);

        return true;
    }

    public Font getFontByName(String name){
        return fontMap.get(name);
    }

    public void setDefault(String name){
        this.defaultFont = name;
    }

    public Font getDefault(){
        return getFontByName(defaultFont);
    }

    public boolean contains(String fontName){
        return fontMap.containsKey(fontName);
    }
}
