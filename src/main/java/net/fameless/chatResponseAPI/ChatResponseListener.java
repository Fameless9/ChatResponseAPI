package net.fameless.chatResponseAPI;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatResponseListener implements Listener {

    public static final ChatResponseListener INSTANCE = new ChatResponseListener();

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (ChatResponseAPI.completeChatResponse(event.getPlayer(), event.getMessage())) event.setCancelled(true);
    }
}
