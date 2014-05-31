package mod.zombiepriester.oreGenerators.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ServerProxy implements IGuiHandler{

	public void registerRenderers(){
		
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	return null;
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
	TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

	if(tileEntity != null)
	{
	switch(ID)
	{
	case 0: /* your Containers go here*/
	}
	}
	return tileEntity;
	}

}
