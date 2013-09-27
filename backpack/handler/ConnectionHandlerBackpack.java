package backpack.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import backpack.util.Version;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;

public class ConnectionHandlerBackpack implements IConnectionHandler {

    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager) {
        if(!Version.seen && Version.isOutdated()) {
            Version.seen = true;
            ChatMessageComponent message = new ChatMessageComponent().addFormatted("text.backpack.update_available", Version.latestVersion);
            ((EntityPlayer) player).sendChatToPlayer(message);
        } else if(!Version.seen && !Version.isOutdated()) {
            Version.seen = true;
            ((EntityPlayer) player).addChatMessage("text.backpack.up_to_date");
        }
    }

    @Override
    public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager) {
        return null;
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager) {
    }

    @Override
    public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager) {
    }

    @Override
    public void connectionClosed(INetworkManager manager) {
    }

    @Override
    public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login) {
    }
}