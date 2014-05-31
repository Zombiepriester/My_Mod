package mod.zombiepriester.oreGenerators;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerDisgenerator extends Container{

	
	private TileDisgenerator inv;

	public ContainerDisgenerator(InventoryPlayer inventory, TileDisgenerator entity) {
		System.out.println(FMLCommonHandler.instance().getEffectiveSide()+" 1");
		inv = entity;
		addSlotToContainer(new Slot(entity, 0, 10, 29));
		for (int i = 0; i < 3; i++) {
			for (int k = 0; k < 9; k++) {
		        addSlotToContainer(new Slot(inventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
		    }
		}

		for (int j = 0; j < 9; j++) {
			addSlotToContainer(new Slot(inventory, j, 8 + j * 18, 142));
		}

	}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	@Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
		Slot slot = (Slot) getSlot(i);
			ItemStack itemstack1 = slot.getStack();
			Slot slot1 = (Slot)this.getSlot(0);
			slot1.putStack(itemstack1);
			slot.putStack(null);
		return itemstack1;
		  
	}
}
