package fi.jokni.nocomfix;

import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.settings.PacketEventsSettings;
import io.github.retrooper.packetevents.utils.server.ServerVersion;
import org.bukkit.plugin.java.JavaPlugin;

public final class NoComFix extends JavaPlugin {
    @Override
    public void onLoad() {
        PacketEvents.create(this);
        PacketEventsSettings settings = PacketEvents.get().getSettings();
        settings
                .fallbackServerVersion(ServerVersion.v_1_18_1)
                .compatInjector(false)
                .checkForUpdates(false)
                .bStats(true);
        PacketEvents.get().loadAsyncNewThread();
    }
    @Override
    public void onEnable() {
        PacketEvents.get().registerListener(new PacketListener());
        PacketEvents.get().init();

    }

    @Override
    public void onDisable() {
        PacketEvents.get().terminate();
    }
}
