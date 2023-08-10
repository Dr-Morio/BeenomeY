package link.mdks.beenomey.util;

import java.util.Random;

import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import link.mdks.beenomey.init.BeeInit;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;

public class ApiaryModBlockRecipeHandler{
	
	public enum ApiaryMode{
		BREED,
		DECIDE
	}
	
	
	public static ApiaryMode determineApiaryMode(ApiaryModBlockEntity pEntity) {
		if (pEntity.itemHandler.getStackInSlot(10).getItem() == BeeInit.PRINCESS_BEE.get()) {
			return ApiaryMode.BREED;
		} else {
			return ApiaryMode.DECIDE;
		}
		
	}
	
	public static int beeLoad(ApiaryModBlockEntity pEntity) {
		int bees = 0;
		for (int i = 4; i < 10; i++) {
			if (pEntity.itemHandler.getStackInSlot(i).getItem() == BeeInit.COMMON_BEE.get()) {
				bees++;
			}
		}
		return bees;
	}

	public static void updateBees(ApiaryModBlockEntity pEntity, ApiaryMode mode) {
		//This method should only be called server sided;
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
			int emptyBeeSlots = 0;
			
			// Update all Bees
		    for(int i = 4; i <=10; i++) {
		    	ItemStack is = pEntity.itemHandler.getStackInSlot(i);
		    	if(is.getItem() != Items.AIR) {
			    	int maxBeeLife = is.getMaxDamage();
			    	int damage = is.getDamageValue();
					int effectiveLife = is.getTag().getInt("EffectiveLifecycle");
					int effectiveLifeAD = is.getTag().getInt("EffectiveLifecycleAD");
					
					if (effectiveLifeAD <= maxBeeLife) { //now damage is shown on item
						is.setDamageValue(is.getDamageValue() + 1);//Damage Bee as Item
						is.getTag().putInt("EffectiveLifecycleAD", (effectiveLifeAD - 1)); //Damage Bee in Tag
					} else { // Just Tag Damage is shown
						is.getTag().putInt("EffectiveLifecycleAD", (effectiveLifeAD - 1)); //Damage Bee in Tag
					}
					//Check if bee is dead now
					if(effectiveLifeAD - 1 == 0) {
						//Consume bee
						pEntity.itemHandler.extractItem(i, 1, false);
						emptyBeeSlots++;
					}
		    	} else {
					emptyBeeSlots++;
		    	}
		    }
			
		    // DECIDE
		    if(mode == ApiaryMode.DECIDE) {
				SpawnPrincess(pEntity);
				return;
		    }
		    
		    // BREED
		    if(mode == ApiaryMode.BREED && emptyBeeSlots != 0 && emptyBeeSlots != 6 && pEntity.itemHandler.getStackInSlot(10).getItem() != Items.AIR) {
				Long gameTime = pEntity.getLevel().getGameTime(); //used for seed. To sync Server and Client for random on tick
				Random randomProvider = new Random(gameTime);
				
				// 33,33 Percent chance to spawn common Bee
				int chance = randomProvider.nextInt(1, 3);
				if(chance >= 1) {
					SpawnHybridBee(pEntity);
				}
		    }
		    
		}
	}
	
	private static void SpawnHybridBee(ApiaryModBlockEntity pEntity) {
		// Princess determines main Type
		Long gameTime = pEntity.getLevel().getGameTime(); //used for seed. To sync Server and Client for random on tick
		//Random randomProvider = new Random(gameTime);
		Random randomProvider = new Random();
		boolean needBee = true;
		int beeSlot = 0;
		
		do {
			int slot = randomProvider.nextInt(4,9); // 6 Slots for bees		
			
			if(pEntity.itemHandler.getStackInSlot(slot).getItem() != Items.AIR) {
				needBee = false;
				beeSlot = slot;
			} else {
			}

			
		} while(needBee);
		
		ItemStack princess = pEntity.itemHandler.getStackInSlot(10); // 10 is always princess
		BeeType mainType = BeeType.valueOf(princess.getTag().getString("MainType").toString());
		
		ItemStack commonBee;
		BeeType secondType;
		// Chose Second type by Bee at Random
		int secondChanceOf = randomProvider.nextInt(0,1);
		if (secondChanceOf == 0) {
			
			commonBee = pEntity.itemHandler.getStackInSlot(beeSlot);
			secondType = BeeType.valueOf(commonBee.getTag().getString("MainType").toString());
		} else {
			commonBee = pEntity.itemHandler.getStackInSlot(beeSlot);
			secondType = BeeType.valueOf(commonBee.getTag().getString("SecondType").toString());
		}
//		default:
//			secondType = mainType;
		
		//Chose slot for bee placement
	    for(int i = 4; i <=9; i++) {
	    	ItemStack is = pEntity.itemHandler.getStackInSlot(i);
	    	if(is.getItem() == Items.AIR) {
	    		pEntity.itemHandler.setStackInSlot(i, BeeManager.getBee(mainType, secondType, new ItemStack(BeeInit.getCommonBee()))); // Sets Princess in Princess Slot
				break;
	    	}
    	}
	}
	
	
	private static void SpawnPrincess(ApiaryModBlockEntity pEntity) {
		Long gameTime = pEntity.getLevel().getGameTime(); //used for seed. To sync Server and Client for random on tick
		//Random randomProvider = new Random(gameTime);
		Random randomProvider = new Random();
		boolean needBee = true;
		int beeSlot = 0;
		
		do {
			int slot = randomProvider.nextInt(4,9); // 6 Slots for bees		
			
			if(pEntity.itemHandler.getStackInSlot(slot).getItem() != Items.AIR) {
				needBee = false;
				beeSlot = slot;
			}
			
		} while(needBee);
		
		ItemStack commonBee = pEntity.itemHandler.getStackInSlot(beeSlot);
		BeeType mainType = BeeType.valueOf(commonBee.getTag().getString("MainType").toString());
		BeeType secondType = BeeType.valueOf(commonBee.getTag().getString("SecondType").toString());
		ItemStack princess = BeeManager.getBee(mainType, secondType, new ItemStack(BeeInit.getPrincessBee()));
		pEntity.itemHandler.extractItem(beeSlot, 1, false); //Consumes bee
		pEntity.itemHandler.setStackInSlot(10, princess); // Sets Princess in Princess Slot
		
		
	}
	
}


