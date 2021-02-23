package testmod.testmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import testmod.testmod.config.SimpleConfig;
import testmod.testmod.gui.OptionGui;
import testmod.testmod.gui.OptionScreen;


@Environment(EnvType.CLIENT)
public class TestmodClient implements ClientModInitializer {
    public static boolean isOn = true;
    int tick = 0;
    int interval;
    int StayTime;
    public static boolean drawString;
    public static String StringToDraw;
    private static KeyBinding keyBinding;
    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.examplemod.spook", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_P, // The keycode of the key
                "category.examplemod.test" // The translation key of the keybinding's category.
        ));
        // Load config 'config.properties', if it isn't present create one
        // using the lambda specified as the provider.
        SimpleConfig CONFIG = SimpleConfig.of( "config" ).provider( this::provider ).request();

        interval = CONFIG.getOrDefault("Interval",1000);
        StayTime = CONFIG.getOrDefault("staytime",200);
        StringToDraw = CONFIG.getOrDefault("string","trink mal was");

        // Custom config provider, returnes the default config content
        // if the custom provider is not specified SimpleConfig will create an empty file instead

        ClientTickEvents.START_WORLD_TICK.register((serverWorld)->{
            if(isOn){
                tick++;
                if(tick > interval && tick < interval + StayTime){


                    drawString =true;

                }else if(tick > interval + StayTime){
                    tick = 0;
                    drawString =false;
                }
            }
            if(keyBinding.isPressed()){
                MinecraftClient.getInstance().openScreen(new OptionScreen(new OptionGui()));
            }

        });

    }
    private String provider(String filename) {
        return "#My default config content\n" +
                "#Interval=500\n" +
                "#staytime=200\n" +
                "#string=Du musst jetzt was trinken\n";
    }
}
