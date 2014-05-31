package mod.zombiepriester.oreGenerators;

import java.util.ArrayList;
import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.entity.passive.EntitySheep;

public class GeneratorBlock extends Block implements IWorldGenerator{

	String metal;
	int metalID;
	public Block dieserBlock;
	public int erz;
	
	public GeneratorBlock(int id,int textureid,String metal) {
		super(id, textureid, Material.iron);
		this.metal = metal;
		setStepSound(soundMetalFootstep);
		setHardness(0F);
		dieserBlock = this;
	}
	public String getTextureFile(){
		return "/modTextures/Blocks.png";
	}
	@Override
	public void onNeighborBlockChange(World World, int x, int y, int z, int nachbarID) {
		if(World.isBlockIndirectlyGettingPowered(x, y, z)){
			World.addWeatherEffect(new EntityLightningBolt(World,x,y,z));
			super.breakBlock(World, x, y, z, 0, 0);
			ArrayList<Chunk> bereich = new ArrayList<Chunk>();
			bereich.add(World.getChunkFromBlockCoords(x+16, z-16));
			bereich.add(World.getChunkFromBlockCoords(x+16, z));
			bereich.add(World.getChunkFromBlockCoords(x+16, z+16));
			//
			bereich.add(World.getChunkFromBlockCoords(x, z-16));
			bereich.add(World.getChunkFromBlockCoords(x, z));
			bereich.add(World.getChunkFromBlockCoords(x, z+16));
			//
			bereich.add(World.getChunkFromBlockCoords(x-16, z-16));
			bereich.add(World.getChunkFromBlockCoords(x-16, z));
			bereich.add(World.getChunkFromBlockCoords(x+16, z+16));
			//
			for(int i = 0;i <= 9; i++){
				for(int g = 0; g < 77; g++){
					int chunkx = bereich.get(i).getChunkCoordIntPair().chunkXPos;
					int chunkz = bereich.get(i).getChunkCoordIntPair().chunkZPos;
					Random rand = new Random();
					int randPosX = chunkx +rand.nextInt(16);
					int randPosY = rand.nextInt(64);
					int randPosZ = chunkz +rand.nextInt(16);
						
					new WorldOreGenerator(erz,30).generate(World, rand, randPosX, randPosY, randPosZ);
				}
			}
		}
	}
	@Override
	public void onBlockDestroyedByPlayer(World World, int par2, int par3, int par4, int par5) {
		EntityItem drop = new EntityItem(World,par2,par3,par4,new ItemStack(this,1)){
			
			@Override
			public void onCollideWithPlayer(EntityPlayer Player){
				if(Player.inventory.getFirstEmptyStack() != -1){
				Player.inventory.addItemStackToInventory(this.getEntityItem());
				}
			}
		};
		World.spawnEntityInWorld(drop);
	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
			
	}
	public void setErz(int erz){
		this.erz = erz;
		
	}
}
