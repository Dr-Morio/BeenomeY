package link.mdks.beenomey.apiculture.recipehandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.ApiaryModBlockEntity;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.apiculture.util.Ecosystem;
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
	
	/** Returns amount of bees in Apiary (Slot 4-9)*/
	public static int beeLoad(ApiaryModBlockEntity pEntity) {

		int bees = 0;
		for (int i = 4; i < 10; i++) {
			if (pEntity.itemHandler.getStackInSlot(i).getItem() == BeeInit.COMMON_BEE.get()) {
				bees++;
			}
		}
		return bees;
	}

	/** Updates all Bees and the Princess inside Apiary Inventory */
	public static void updateBees(ApiaryModBlockEntity pEntity, ApiaryMode mode) {
		//This method should only be called server sided;
		if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER) {
			int emptyBeeSlots = 0;
		
			// drop Combs
			if(mode == ApiaryMode.BREED) {
				dropCombs(pEntity);
			}
			
		    // DECIDE which bee will be Princess
		    if(mode == ApiaryMode.DECIDE) {
				SpawnPrincess(pEntity);
				return;
		    }
			
			// Update all bees and princess
		    for(int i = 4; i <=10; i++) {
		    	ItemStack is = pEntity.itemHandler.getStackInSlot(i);
		    	if(is.getItem() != Items.AIR) {
		    		
		    		//Kill bee if its wrong Ecosystem
		    		BeenomeY.LOGGER.debug("ECO Bee: " + Ecosystem.valueOf(is.getTag().getString("Ecosystem")));
		    		BeenomeY.LOGGER.debug("ECO Level: " + Ecosystem.getBiomeTemp(pEntity.getLevel().getBiome(pEntity.getBlockPos()).unwrapKey().get()));
		    		if(Ecosystem.valueOf(is.getTag().getString("Ecosystem")) == Ecosystem.getBiomeTemp(pEntity.getLevel().getBiome(pEntity.getBlockPos()).unwrapKey().get())) {
				    	int maxBeeLife = is.getMaxDamage();
				    	//int damage = is.getDamageValue();
						//int effectiveLife = is.getTag().getInt("EffectiveLifecycle");
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
						pEntity.itemHandler.extractItem(i, 1, false);
						emptyBeeSlots++;
					}
		    	} else {
					emptyBeeSlots++;
		    	}
		    }
		    
		    // Breed new bee
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
		//Princess determines main Type
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
		//Long gameTime = pEntity.getLevel().getGameTime(); //used for seed. To sync Server and Client for random on tick
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

	private static void dropCombs(ApiaryModBlockEntity pEntity) {
		
		/**
		A random slot within a specific range (4-10) is chosen for decision-making. This slot range contains bees and princesses.
		The function checks if the chosen slot holds a bee. If not, it moves one slot up. Slot 10 always holds a princess.
		The type of bee producing the comb is determined by a 50/50 chance.
		The number of bees with the same type is counted.
		The CompDropValue (a value associated with the bee) determines how many combs will be dropped.
		A compDropMultiplier value from the princess is applied to the count of combs.
		The function attempts to place the calculated number of combs into slots that already hold the same type of comb. If the slot is empty, the combs are placed there. If not, the existing combs are combined with the new combs, ensuring no slot exceeds a stack size of 64.
		If there is still a surplus of combs after attempting to place them in slots, they will be dropped.
		*/
	
		Random randomProvider = new Random();
		int preferredDescissionSlot = randomProvider.nextInt(4,10); // Slot 4-10 contain bees and princess
		BeenomeY.LOGGER.debug("DropCombs: SLOT: " + preferredDescissionSlot);
		
		//Check if chosen slot holds bee
		//If not go one slot up
		//Slot 10 always holds Princess in that step
		while (pEntity.itemHandler.getStackInSlot(preferredDescissionSlot).getItem() == Items.AIR) {
				preferredDescissionSlot++;
				BeenomeY.LOGGER.debug("DropCombs: SLOT+ now: " + preferredDescissionSlot);
		}
		BeenomeY.LOGGER.debug("DropCombs: SLOT: Chosen: " + preferredDescissionSlot);
		ItemStack builderBee = pEntity.itemHandler.getStackInSlot(preferredDescissionSlot);
		int typeChoseValue = randomProvider.nextInt(1,100);
		BeenomeY.LOGGER.debug("DropCombs: ChoseValue: " + typeChoseValue);
		BeeType selectedType = BeeType.EMPTY;
		if (typeChoseValue >= 50) {
			selectedType = BeeType.valueOf(builderBee.getTag().get("MainType").getAsString());
			BeenomeY.LOGGER.debug("DropCombs: Selected Type Main: " + selectedType);
		} else {
			selectedType = BeeType.valueOf(builderBee.getTag().get("SecondType").getAsString());
			BeenomeY.LOGGER.debug("DropCombs: Selected Type Second: " + selectedType);
		}
		
		//Get All Present bees and check if a match of sectedType is present
		int beesOfType = 0;
		for(int i = 4; i <= 10; i++) {
			if(pEntity.itemHandler.getStackInSlot(i).getItem() != Items.AIR) {
				ItemStack possibleBee = pEntity.itemHandler.getStackInSlot(i);
				BeeType mainType = BeeType.valueOf(possibleBee.getTag().get("MainType").getAsString());
				BeeType secondType = BeeType.valueOf(possibleBee.getTag().get("SecondType").getAsString());
				
				BeenomeY.LOGGER.debug("DropCombs: Bee with Type: " + mainType + " / " + secondType + " in slot: " + i);
				
				//Add counter for each present type
				if (mainType == selectedType) {
					beesOfType++;
					BeenomeY.LOGGER.debug("DropCombs: Type Match by Main");
				}
				if (secondType == selectedType) {
					beesOfType++;
					BeenomeY.LOGGER.debug("DropCombs: Type Match by Second");
				}

			}
		}
		BeenomeY.LOGGER.debug("DropCombs: Bees of that Type: " + beesOfType);
		
		// Get CompDropValue and Calculate Comps to Drop before multiplayer
		float comps = builderBee.getTag().getFloat("CompDrop") * beesOfType;
		BeenomeY.LOGGER.debug("DropCombs: CompsToDropByBees: " + comps);
		
		// Calculate effective CompsToDrop with Multiplayer of Princess
		float compDropMultiplayer = pEntity.itemHandler.getStackInSlot(10).getTag().getFloat("CompDropMultiplier");
		int compsToDrop = Math.round(comps * compDropMultiplayer);
		BeenomeY.LOGGER.debug("DropCombs: CompsDropMultipalayer: " + compDropMultiplayer);
		BeenomeY.LOGGER.debug("DropCombs: CompsToDropAfterPrincess: " + compsToDrop);
		
		//ItemStack combs = BeeManager.getComb(selectedType, compsToDrop);
		//Check if comb of that type is already present
		List<Integer> possibleSlots = new ArrayList<Integer>();
		
		
		//Get Slots that already contain type but not 64
		for (int i = 0; i < 4; i++) { //slot 0-3 are combSlots
			if(pEntity.itemHandler.getStackInSlot(i).getItem() == BeeManager.getComb(selectedType, compsToDrop).getItem() && pEntity.itemHandler.getStackInSlot(i).getCount() < 64) {
				possibleSlots.add(i);
				BeenomeY.LOGGER.debug("DropCombs: SLOT POSSIBLE WITH TYPE: " + i);
			}
		}
		
		//GetSlots that are empty for leftovers
		for (int i = 0; i < 4; i++) { //slot 0-3 are combSlots
			if(pEntity.itemHandler.getStackInSlot(i).getItem() == Items.AIR ) {
				possibleSlots.add(i);
				BeenomeY.LOGGER.debug("DropCombs: SLOT POSSIBLE: " + i);
			}
		}
		
		for(int i : possibleSlots) {
			if(compsToDrop > 0) {
				BeenomeY.LOGGER.debug("DropCombs: STACK > 0: " + compsToDrop);
				if(pEntity.itemHandler.getStackInSlot(i).getItem() == Items.AIR) {
					// PLACE STACK
					if(compsToDrop > 64) {
						ItemStack combsToPlace = BeeManager.getComb(selectedType, 64);
						pEntity.itemHandler.setStackInSlot(i, combsToPlace);
						pEntity.setChanged();
						compsToDrop = compsToDrop - 64;
					} else {
						ItemStack combsToPlace = BeeManager.getComb(selectedType, compsToDrop);
						pEntity.itemHandler.setStackInSlot(i, combsToPlace);
						pEntity.setChanged();
						BeenomeY.LOGGER.debug("DropCombs: PLACED ALL IN SLOT: " + i);
						compsToDrop = 0;
					}

				} else {
					int existingCombs = pEntity.itemHandler.getStackInSlot(i).getCount();
					int combinedValue = existingCombs + compsToDrop;
					BeenomeY.LOGGER.debug("DropCombs: BEFORE COMBINATION: " + existingCombs);
					BeenomeY.LOGGER.debug("DropCombs: AFTER COMBINATION: " + combinedValue);
					if(combinedValue > 64) {
						ItemStack combsToPlace = BeeManager.getComb(selectedType, 64);
						pEntity.itemHandler.extractItem(i, pEntity.itemHandler.getStackInSlot(i).getCount(), false); //Extract all
						pEntity.itemHandler.setStackInSlot(i, combsToPlace);
						pEntity.setChanged();
						compsToDrop = combinedValue - 64;
						BeenomeY.LOGGER.debug("DropCombs: PLACED: 64 in slot: " + i );
						BeenomeY.LOGGER.debug("DropCombs: LEFTOVER: " + compsToDrop );
					} else {
						ItemStack combsToPlace = BeeManager.getComb(selectedType, combinedValue);
						pEntity.itemHandler.extractItem(i, pEntity.itemHandler.getStackInSlot(i).getCount(), false); //Extract all
						pEntity.itemHandler.setStackInSlot(i, combsToPlace);
						pEntity.setChanged();
						BeenomeY.LOGGER.debug("DropCombs: PLACED: " + combinedValue + " in slot: " + i );
						compsToDrop = 0;
					}
				}
			}
		}
		
		// At this pint the combs will be dropped because there was no space to place them		

	}
	
}


