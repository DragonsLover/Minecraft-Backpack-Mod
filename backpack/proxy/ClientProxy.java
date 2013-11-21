package backpack.proxy;

import java.lang.reflect.Method;

import net.minecraftforge.common.MinecraftForge;
import backpack.gui.GuiWorkbenchBackpack;
import backpack.handler.EventHandlerRenderPlayer;
import backpack.handler.KeyHandlerBackpack;
import backpack.nei.OverlayHandlerBackpack;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.FMLLog;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerHandler() {
        super.registerHandler();
        KeyBindingRegistry.registerKeyBinding(new KeyHandlerBackpack());
        // register event handler
        MinecraftForge.EVENT_BUS.register(new EventHandlerRenderPlayer());
    }

    @Override
    public void addNeiSupport() {
        try {
            Class API = Class.forName("codechicken.nei.api.API");
            Class IOverlayHandler = Class.forName("codechicken.nei.api.IOverlayHandler");
            Method registerGuiOverlay = API.getDeclaredMethod("registerGuiOverlay", new Class[] { Class.class, String.class });
            Method registerGuiOverlayHandler = API.getDeclaredMethod("registerGuiOverlayHandler", new Class[] { Class.class, IOverlayHandler, String.class });

            registerGuiOverlay.invoke(API, new Object[] { GuiWorkbenchBackpack.class, "crafting" });
            registerGuiOverlayHandler.invoke(API, new Object[] { GuiWorkbenchBackpack.class, new OverlayHandlerBackpack(), "crafting" });

            FMLLog.info("[Backpacks] NEI Support enabled");
        }
        catch (Exception e) {
            FMLLog.info("[Backpacks] NEI Support couldn't be enabled");
        }
    }
}