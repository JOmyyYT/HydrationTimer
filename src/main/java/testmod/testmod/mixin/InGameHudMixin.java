package testmod.testmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.Font;
import net.minecraft.client.font.FontLoader;
import net.minecraft.client.font.FontStorage;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.BackgroundHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.network.MessageType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.Matrix3f;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import testmod.testmod.client.TestmodClient;

import java.awt.*;
import java.util.UUID;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin extends DrawableHelper {


    @Shadow public abstract void addChatMessage(MessageType type, Text text, UUID senderUuid);

    @Shadow @Final private MinecraftClient client;

    @Inject(at  = @At("HEAD"),method = "render")
    public void render(MatrixStack matrices, float tickDelta, CallbackInfo ci){

        if(TestmodClient.drawString){

            TranslatableText text = new TranslatableText(TestmodClient.StringToDraw);
            addChatMessage(MessageType.GAME_INFO,text,client.player.getUuid());
            //drawStringWithShadow(mat,renderer,"Du musst jetzt was trinken",this.scaledWidth /2 - renderer.getWidth("Du musst jetzt was trinken") /2, this.scaledHeight /2 + 70,Color.RED.getRGB());


        }


    }

}
