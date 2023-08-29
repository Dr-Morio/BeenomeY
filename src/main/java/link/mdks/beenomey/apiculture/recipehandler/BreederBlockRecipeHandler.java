package link.mdks.beenomey.apiculture.recipehandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import link.mdks.beenomey.BeenomeY;
import link.mdks.beenomey.apiculture.blocks.entity.BreederBlockEntity;
import link.mdks.beenomey.apiculture.items.ItemCell;
import link.mdks.beenomey.apiculture.recipe.BreederBlockRecipe;
import link.mdks.beenomey.apiculture.util.BeeManager;
import link.mdks.beenomey.apiculture.util.BeeType;
import link.mdks.beenomey.init.BeeInit;
import link.mdks.beenomey.init.ItemInit;
import net.minecraft.util.Tuple;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler.FluidAction;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import oshi.util.tuples.Triplet;

public class BreederBlockRecipeHandler {

	@SuppressWarnings("serial")
	private static final List<Triplet<BeeType, BeeType, BeeType>> recipes = new ArrayList<Triplet<BeeType,BeeType,BeeType>>() {{
		add(0, new Triplet<BeeType, BeeType, BeeType>(BeeType.DESERT, BeeType.ROCK, BeeType.COAL));
		add(1, new Triplet<BeeType, BeeType, BeeType>(BeeType.DESERT, BeeType.COAL, BeeType.FLINT));
		add(2, new Triplet<BeeType, BeeType, BeeType>(BeeType.IRON, BeeType.GOLD, BeeType.COPPER));
	}};
	
	public static void fluidTick(BreederBlockEntity pEntity) {
		
		//Check slot first
		ItemStack cell = pEntity.itemHandler.getStackInSlot(6);
		ItemStack cellResult = pEntity.itemHandler.getStackInSlot(5);
		FluidTank tank = pEntity.getFluidTank();
		BeenomeY.LOGGER.debug("Breeder: FLUID TICK");
		if(cell.getItem() != Items.AIR) {
			if(cell.getItem() == ItemInit.EMPTY_CELL.get() && cellResult.getItem() == Items.AIR) {
				//Check fluid amount
				if(tank.getFluidAmount() >= 4000) {
					//Create Fluid Cell
					pEntity.itemHandler.extractItem(6, 1, false);
					consumeFluid(pEntity);
					pEntity.itemHandler.setStackInSlot(5, new ItemStack(tank.getFluid().getRawFluid().getBucket(), 1));
					pEntity.setChanged();
					return;
				}
			} 
			if(cell.getItem() != ItemInit.EMPTY_CELL.get() && containsRightFluid(tank, cell)){
				if (tank.getFluidAmount() + 4000 <= tank.getCapacity()) {
					Fluid cellFluid = ((ItemCell) cell.getItem()).getFluid();
					pEntity.itemHandler.extractItem(6, 1, false);
					pEntity.getFluidTank().fill(new FluidStack(cellFluid, 4000),FluidAction.EXECUTE);
					pEntity.setChanged();
				}
			}
		}
		
	}

	private static boolean containsRightFluid(FluidTank tank, ItemStack cell) {
		/* Checks if Tank holds the right fluid to work with the givel Cell */
		if(((ItemCell) cell.getItem()).getFluid() == tank.getFluid().getFluid() || tank.isEmpty())
			return true;
		else {
			return false;
		}
	}
	
	private static boolean containsRightFluid(FluidTank tank, BeeType type) {
		/* Checks if Tank holds the right fluid to work with the givel BeeType */
		if(BeeManager.getFluid(type) == tank.getFluid().getFluid()) {
			return true;
		} else {
			return false;
		}
	}

	public static void consumeFluid(BreederBlockEntity pEntity) {
		pEntity.getFluidTank().drain(4000, FluidAction.EXECUTE);
	}

	public static void loadRecipe(BreederBlockEntity pEntity) {

		//Check if bee is in every slot
		if(pEntity.itemHandler.getStackInSlot(0).getItem() != Items.AIR && 
				pEntity.itemHandler.getStackInSlot(1).getItem() != Items.AIR &&
				pEntity.itemHandler.getStackInSlot(2).getItem() != Items.AIR &&
				pEntity.itemHandler.getStackInSlot(3).getItem() != Items.AIR)
		{
			//Check if the contain the right type
			if(hasEqualMainType(pEntity.itemHandler.getStackInSlot(0),pEntity.itemHandler.getStackInSlot(1)) &&
					hasEqualMainType(pEntity.itemHandler.getStackInSlot(2),pEntity.itemHandler.getStackInSlot(3))) {
				
				Tuple<BeeType, BeeType> input = new Tuple<>(
						BeeType.valueOf(pEntity.itemHandler.getStackInSlot(0).getTag().get("MainType").getAsString()),
						BeeType.valueOf(pEntity.itemHandler.getStackInSlot(2).getTag().get("MainType").getAsString()));
				
				
				for(Triplet<BeeType, BeeType, BeeType> recipe : recipes) {
					if ((input.getA() == recipe.getA() || input.getA() == recipe.getB()) ||
							(input.getB() == recipe.getA() || input.getB() == recipe.getB())) {
						BeenomeY.LOGGER.debug("Breeder: VALID ITEMS");
						if (containsRightFluid(pEntity.getFluidTank(), recipe.getC())) {
							BeenomeY.LOGGER.debug("Breeder: RIGHT FLUID");
							if(hasEnoughFluid(pEntity)) {
								BeenomeY.LOGGER.debug("Breeder: ENOUGH FLUID");
								if (hasEnoughEnergy(pEntity)) {
									BeenomeY.LOGGER.debug("Breeder: ENOUGH ENERGY");
									pEntity.isCrafting = true;
									pEntity.loadedRecipe = recipe;
									BeenomeY.LOGGER.debug("Breeder: CRAFTING BEGINS");
								}
							}
						}
					}
				}
			}
		}
	}
	
	public static void craft(BreederBlockEntity pEntity) {
		//Consume 
		pEntity.itemHandler.extractItem(0, 1, false);
		pEntity.itemHandler.extractItem(1, 1, false);
		pEntity.itemHandler.extractItem(2, 1, false);
		pEntity.itemHandler.extractItem(3, 1, false);
		
		pEntity.getFluidTank().drain(4000, FluidAction.EXECUTE);
		
		pEntity.getEnergyStorage().extractEnergy(pEntity.getReqEnergy(), false);
		pEntity.setChanged();
		
		BeenomeY.LOGGER.debug("Breeder: CONSUMED ALL");
		//Place new Bee (maybe)
		Random rnd = new Random();
		int pick = rnd.nextInt(1,100);
		BeenomeY.LOGGER.debug("Breeder: CHANCE IS 20 - PICK IS: " + pick);
		if(pick <= 20) {
			pEntity.itemHandler.setStackInSlot(4, 
					BeeManager.getBee(
							pEntity.loadedRecipe.getC(), 
							pEntity.loadedRecipe.getC(), 
							new ItemStack(BeeInit.getCommonBee())));
			BeenomeY.LOGGER.debug("Breeder: BEE CREATED");
			pEntity.setChanged();
		}
	}
	
	public static boolean hasInventoryChanged(BreederBlockEntity pEntity) {
		/* Checks if inventory has changed */
		/* Will not check fluid system */
		Map<Integer, ItemStack> currentInventory = new HashMap<Integer, ItemStack>();
		for(int i = 0; i < pEntity.itemHandler.getSlots(); i++) {
			currentInventory.put(i, pEntity.itemHandler.getStackInSlot(i));
		}
		
		if (pEntity.lastInventory.size() != currentInventory.size()) {
			BeenomeY.LOGGER.debug("Breeder: Inventory Size Changed - Before: " + pEntity.lastInventory.size() + " Now: " + currentInventory.size());
			pEntity.lastInventory = currentInventory;
			return true;
		}
		
		for (int i = 0; i < 5; i ++) {
			if (currentInventory.get(i) != pEntity.lastInventory.get(i)) {
				BeenomeY.LOGGER.debug("Breeder: Inventory Slot Changed: " + currentInventory.get(i));
				pEntity.lastInventory = currentInventory;
				return true;
			}
		}
		pEntity.lastInventory = currentInventory;
		return false;
	}
	
	public static boolean hasFluidChanged(BreederBlockEntity pEntity) {
		/* Checks if fluid has changed */
		if (pEntity.getFluidTank().getFluidAmount() != pEntity.lastFluidAmount) {
			return true;
		}
		return false;
	}

	public static boolean hasEnergyChanged(BreederBlockEntity pEntity) {
		/* Checks if fluid has changed */
		if(pEntity.getEnergyStorage().getEnergyStored() != pEntity.lastEnergyAmount) {
			return true;
		}
		return false;
	}
	
	public static boolean hasEnoughEnergy(BreederBlockEntity pEntity) {
		if (pEntity.getEnergyStorage().getEnergyStored() >= pEntity.getReqEnergy()) {
			return true;
		}
		return false;
	}

	private static boolean hasEqualMainType(ItemStack stack1, ItemStack stack2) {
		BeeType main1 = BeeType.valueOf(stack1.getTag().get("MainType").getAsString());
		BeeType main2 = BeeType.valueOf(stack2.getTag().get("MainType").getAsString());
		
		if (main1 == main2) {
			return true;
		}
		return false;
	}
	
	public static List<Triplet<BeeType, BeeType, BeeType>> getRecipes() {
		return recipes;
	}

	public static boolean hasEnoughFluid(BreederBlockEntity pEntity) {
		if (pEntity.getFluidTank().getFluidAmount() >= 4000) {
			return true;
		}
		return false;
	}


	
}
