package testmod.testmod.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;

public class OptionGui extends LightweightGuiDescription {
    public OptionGui(){
        WGridPanel root = new WGridPanel();
        setFullscreen(true);
        setRootPanel(root);
        root.setBackgroundPainter(BackgroundPainter.VANILLA);
    }
}
