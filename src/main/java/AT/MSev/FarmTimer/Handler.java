package AT.MSev.FarmTimer;

import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockGrowEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

import java.util.ArrayList;

import static AT.MSev.FarmTimer.FarmTimer.CropMaterials;

public class Handler implements Listener {
    @EventHandler
    public void OnGrow(BlockGrowEvent e)
    {
        e.setCancelled(true);
    }

    @EventHandler
    public void OnPlant(BlockPlaceEvent e)
    {
        if(CropMaterials.contains(e.getBlockPlaced().getType()))
        {
            FarmTimer.AllCrops.add(e.getBlockPlaced().getLocation());
            FarmTimer.SetState();
        }
    }

    @EventHandler
    public void OnUnPlant(BlockBreakEvent e)
    {
        Location location = e.getBlock().getLocation();
        if(CropMaterials.contains(e.getBlock().getType()) && FarmTimer.AllCrops.contains(e.getBlock().getLocation()
        ))
        {
            FarmTimer.AllCrops.remove(location);
            FarmTimer.SetState();
        }

        if(e.getBlock().getType().equals(Material.MELON_BLOCK))
        {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.MELON_SEEDS, 1 ));
        } else if(e.getBlock().getType().equals(Material.PUMPKIN))
        {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.PUMPKIN_SEEDS, 1 ));
        }
    }
}
