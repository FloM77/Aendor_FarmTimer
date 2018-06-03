package AT.MSev.FarmTimer;

import AT.MSev.Mango_Core.Utils.MangoUtils;
import AT.MSev.Mango_Core.Utils.TimedEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.Period;

import java.util.ArrayList;

public class FarmTimer extends JavaPlugin {
    public static NamespacedKey key;
    static FarmTimer config;
    public static ArrayList<Location> AllCrops = new ArrayList<Location>();
    DateTime GrowDateTime;
    @Override
    public void onEnable() {
        key = new NamespacedKey(this, this.getDescription().getName());
        config = this;
        GetState();
        this.getCommand("GrowCrops").setExecutor(new CommandGrowCrops());

        if(!TimedEvent.IsLoaded(GrowCall.class)) {
            new TimedEvent(DateTime.now(), new Period(0, 0, 1, 0, 0, 0, 0, 0), GrowCall.class);
        }
        //int years, int months, int weeks, int days, int hours, int minutes, int seconds, int millis
        MangoUtils.Log(TimedEvent.All.size()+"");
        getServer().getPluginManager().registerEvents(new Handler(), this);
    }
    @Override
    public void onDisable() {
    }

    static void GetState()
    {
        if((ArrayList<Location>)config.getConfig().get("FarmTimer.Plants")!=null)
        AllCrops = (ArrayList<Location>)config.getConfig().get("FarmTimer.Plants");
    }

    static void SetState()
    {
        config.getConfig().set("FarmTimer.Plants", AllCrops);
        config.saveConfig();
    }

    static ArrayList<Material> CropMaterials = new ArrayList<Material>() {{
        add(Material.CROPS);
        add(Material.WHEAT);
        add(Material.MELON_STEM);
        add(Material.CARROT);
        add(Material.POTATO);
        add(Material.BEETROOT_BLOCK);
        add(Material.PUMPKIN_STEM);}};

    public static void GrowAllStated()
    {
        for(Location l : AllCrops)
        {
            Block b = l.getWorld().getBlockAt(l);
            if(CropMaterials.contains(b.getType()))
            {
                if(b.getType().equals(Material.BEETROOT_BLOCK)) {b.setData((byte)3); continue;}
                b.setData((byte)7);
                if(b.getType().equals(Material.MELON_STEM))
                {
                    b.setType(Material.MELON_BLOCK);

                }
                if(b.getType().equals(Material.PUMPKIN_STEM))
                {
                    b.setType(Material.PUMPKIN);
                }
            }
        }
        AllCrops = new ArrayList<Location>();
        SetState();
    }
}
