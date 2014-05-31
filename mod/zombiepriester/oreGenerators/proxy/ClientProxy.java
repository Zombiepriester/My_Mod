package mod.zombiepriester.oreGenerators.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ClientProxy extends ServerProxy{

	@Override
	public void registerRenderers() {
		
		super.registerRenderers();
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
	if (tileEntity != null)
	{
	switch(ID)
	{
	case 0: /* your GUIs go here */
	}
	}
	return tileEntity;

	}

}
