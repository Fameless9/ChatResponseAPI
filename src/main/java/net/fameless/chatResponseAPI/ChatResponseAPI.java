package net.fameless.chatResponseAPI;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeoutException;

public class ChatResponseAPI {

    private static final Map<UUID, CompletableFuture<String>> pendingResponses = new HashMap<>();

    /**
     * Waits for a chat response from the player.
     *
     * @param player the player to wait for a response from
     * @param plugin the plugin instance
     * @param timeoutMillis the timeout in milliseconds
     * @return a CompletableFuture that will complete with the player's response or an exception if timed out
     */
    public static @NotNull CompletableFuture<String> awaitChatResponse(@NotNull Player player, Plugin plugin, long timeoutMillis) {
        UUID uuid = player.getUniqueId();

        if (pendingResponses.containsKey(uuid)) {
            pendingResponses.get(uuid).completeExceptionally(new IllegalStateException("Already waiting"));
        }

        CompletableFuture<String> future = new CompletableFuture<>();
        pendingResponses.put(uuid, future);

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (!future.isDone()) {
                future.completeExceptionally(new TimeoutException("No response in time"));
                pendingResponses.remove(uuid);
            }
        }, timeoutMillis / 50); // ms → ticks

        return future;
    }

    /**
     * Cancels the chat response waiting for the player.
     *
     * @param player the player to cancel the response for
     */
    public static void cancelChatResponse(@NotNull Player player) {
        UUID uuid = player.getUniqueId();
        CompletableFuture<String> future = pendingResponses.remove(uuid);

        if (future != null) {
            future.cancel(true);
        }
    }

    /**
     * API will call this method when receiving a chat message from the player.
     * Can be called manually.
     *
     * @param player the player who sent the message
     * @param response the response message
     *
     * @return whether a response has been completed
     */
    public static boolean completeChatResponse(@NotNull Player player, String response) {
        UUID uuid = player.getUniqueId();
        CompletableFuture<String> future = pendingResponses.remove(uuid);

        if (future != null) {
            future.complete(response);
            return true;
        }
        return false;
    }
}
