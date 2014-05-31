package mod.zombiepriester.oreGenerators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.nbt.NBTTagCompound;


public class OreDisgenerator extends BlockContainer{

	public OreDisgenerator(int id, Material Material) {
		super(id, 15, Material.iron);
		setStepSound(soundMetalFootstep);
	}
	
	public String getTextureFile(){
		return "/modTextures/Blocks.png";
	}

	/*@Override
	public void onNeighborBlockChange(World World, int x, int y, int z, int nachbarID) {
		if(World.isBlockIndirectlyGettingPowered(x, y, z)){
			World.addWeatherEffect(new EntityLightningBolt(World,x,y,z));
			this.removeBlockByPlayer(World, World.getClosestPlayer(x, y, z, -1), x, y, z);
			//289000000000000 Chunks in einer Welt
			for(long i = 0;i <= 289000000000000L; i++){
				
			}
		}
	}*/
	

public boolean renderAsNormalBlock() {
return false;
}
@Override
public TileEntity createNewTileEntity(World var1) {
return new TileDisgenerator();
}

	
	@Override
	public boolean onBlockActivated(World par1World, int x, int y, int z, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9){
		if(!par1World.isRemote){	
			Object tileD = (TileDisgenerator)par1World.getBlockTileEntity(x, y, z);
			par5EntityPlayer.openGui(OreGenerators.instance, 0, par1World, x, y, z);
		}
	return true;
		
	}
	
	@Override
    public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
            dropItems(world, x, y, z);
            super.breakBlock(world, x, y, z, par5, par6);
    }
	
	private void dropItems(World world, int x, int y, int z){
        Random rand = new Random();

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
                return;
        }
        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
                ItemStack item = inventory.getStackInSlot(i);

                if (item != null && item.stackSize > 0) {
                        float rx = rand.nextFloat() * 0.8F + 0.1F;
                        float ry = rand.nextFloat() * 0.8F + 0.1F;
                        float rz = rand.nextFloat() * 0.8F + 0.1F;

                        EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

                        if (item.hasTagCompound()) {
                                entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                        }

                        float factor = 0.05F;
                        entityItem.motionX = rand.nextGaussian() * factor;
                        entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                        entityItem.motionZ = rand.nextGaussian() * factor;
                        world.spawnEntityInWorld(entityItem);
                        item.stackSize = 0;
                }
        }
}
}
