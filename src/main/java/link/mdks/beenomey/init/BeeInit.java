package link.mdks.beenomey.init;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.items.ItemBee;
import link.mdks.beenomey.apiculture.items.ItemPrincess;
import link.mdks.beenomey.apiculture.items.ItemQueen;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class BeeInit {

	public BeeInit() {}
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BeenomeY.MODID);
	
	public static final RegistryObject<Item> COMMON_BEE = ITEMS.register("common_bee",
			() -> new ItemBee(new Item.Properties().durability(1)));
	
	public static final RegistryObject<Item> PRINCESS_BEE = ITEMS.register("princess_bee",
			() -> new ItemPrincess(new Item.Properties().durability(1)));
	
	public static final RegistryObject<Item> QUEEN_BEE = ITEMS.register("queen_bee",
			() -> new ItemQueen(new Item.Properties().durability(1)));
	
    public static void register(IEventBus eventBus) {
    	ITEMS.register(eventBus);
    }

    public static final Item getCommonBee() {
    	return COMMON_BEE.get();
    }
    
    public static final Item getPrincessBee() {
    	return PRINCESS_BEE.get();
    }
    
    public static final Item getQueenBee() {
    	return QUEEN_BEE.get();
    }
    
}
