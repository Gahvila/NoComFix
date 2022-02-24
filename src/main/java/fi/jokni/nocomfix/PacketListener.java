package fi.jokni.nocomfix;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.setcreativeslot.WrappedPacketInSetCreativeSlot;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.io.DataInput;
import java.io.IOException;

public class PacketListener extends PacketListenerAbstract {

    public PacketListener() {
        super(PacketListenerPriority.LOW);
    }
    @Override
    public void onPacketPlayReceive(PacketPlayReceiveEvent e) {
        switch (e.getPacketId()){
            case PacketType.Play.Client.SET_CREATIVE_SLOT:
                ItemStack bStack = new WrappedPacketInSetCreativeSlot(e.getNMSPacket()).getClickedItem();
                net.minecraft.world.item.ItemStack itemStack = CraftItemStack.asNMSCopy(bStack);
                if (itemStack.getOrCreateTag().contains("BlockEntityTag")){
                    if (itemStack.getOrCreateTag().toString().contains("y:255")) {
                        e.setCancelled(true);
                        Bukkit.getScheduler().runTask(NoComFix.instance, new Runnable() {
                            public void run() {
                                e.getPlayer().getInventory().clear();
                                e.getPlayer().kick(Component.text(""));
                            }
                        });
                    }
                }
                break;

        }
    }
}
