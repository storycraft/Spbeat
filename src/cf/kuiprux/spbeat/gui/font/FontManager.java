package cf.kuiprux.spbeat.gui.font;

import org.newdawn.slick.UnicodeFont;

import java.util.HashMap;
import java.util.Map;

public class FontManager {

    private String defaultFont;
    private Map<String, UnicodeFont> fontMap;

    public FontManager(){
        this.fontMap = new HashMap<>();
    }

    public boolean addFont(UnicodeFont font){
        if (font == null)
            return false;

        String name = font.getFont().getName();

        if (contains(name))
            return false;

        fontMap.put(name, font);

        return true;
    }

    public UnicodeFont getFontByName(String name){
        return fontMap.get(name);
    }

    public void setDefault(String name){
        this.defaultFont = name;
    }

    public UnicodeFont getDefault(){
        return getFontByName(defaultFont);
    }

    public boolean contains(String fontName){
        return fontMap.containsKey(fontName);
    }
}
