package fi.jokni.nocomfix;

import io.github.retrooper.packetevents.event.PacketListenerAbstract;
import io.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.event.impl.PacketPlayReceiveEvent;
import io.github.retrooper.packetevents.packettype.PacketType;
import io.github.retrooper.packetevents.packetwrappers.play.in.setcreativeslot.WrappedPacketInSetCreativeSlot;
import org.bukkit.craftbukkit.v1_18_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

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
                    e.setCancelled(true);
                }
                break;

        }
    }
}
