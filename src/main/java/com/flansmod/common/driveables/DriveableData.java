package com.flansmod.common.driveables;

import java.util.HashMap;

import com.flansmod.common.guns.ItemBullet;
import com.flansmod.common.parts.ItemPart;
import com.flansmod.common.parts.PartType;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class DriveableData implements IInventory
{
	/** The name of this driveable's type */
	public String type;
	/** The sizes of each inventory (guns, bombs / mines, missiles / shells, cargo) */
	public int numGuns, numBombs, numMissiles, numCargo;
	/** The inventory stacks */
	public ItemStack[] ammo, bombs, missiles, cargo;
	/** The engine in this driveable */
	public PartType engine;
	/** The stack in the fuel slot */
	public ItemStack fuel;
	public ItemStack disk;
	//TODO
	public float fr = 0F;
    public float fg = 0F;
    public float fb = 0F;
	
	public String str1="statusdisk_5rect";
	public String str2="";
	public String str3="bort_grey_kuzov";
	public String disk_path="";
    public String disk_texture_path="";
    String[] subStr1;
    String delimeter1 = "_";
    public ResourceLocation disk_texture= new ResourceLocation("minecraft:d33vaz/textures/model/statusdisk.png");
	public ResourceLocation disk_model= new ResourceLocation("minecraft:d33vaz/textures/model/disk/statusdisk_5rect.obj");
	public ResourceLocation disk_model1= new ResourceLocation("minecraft:d33vaz/textures/model/disk/statusdisk_5rect.obj");
	
	public ResourceLocation kuzov_texture= new ResourceLocation("minecraft:d33vaz/textures/model/bort.png");
	public ResourceLocation kuzov_model= new ResourceLocation("minecraft:d33vaz/textures/model/kuzov/grey_kuzov.obj");
	public ResourceLocation kuzov_model1= new ResourceLocation("minecraft:d33vaz/textures/model/kuzov/grey_kuzov.obj");
	//public IModelCustom d33wheel;//=AdvancedModelLoader.loadModel(disk_model1);
	/** The amount of fuel in the tank */
	public float fuelInTank;
	/** Each driveable part has a small class that holds its current status */
	public HashMap<EnumDriveablePart, DriveablePart> parts;
		
	public DriveableData(NBTTagCompound tags)
	{
		parts = new HashMap<EnumDriveablePart, DriveablePart>();
		readFromNBT(tags);
		//d33wheel=AdvancedModelLoader.loadModel(disk_model1);
	}

	public void readFromNBT(NBTTagCompound tag)
    {
		if(tag == null)
			return;
    	if(!tag.hasKey("Type"))
    		return;
    	fr = tag.getFloat("FloatR");
		fg = tag.getFloat("FloatG");
		fb = tag.getFloat("FloatB");
		type = tag.getString("Type");
		str1 = tag.getString("Pathstr1");
		if(fr==0.00F&&fg==0.00F&&fb==0.00F){
			fr = (float)Math.random();
		    fg = (float)Math.random();
		    fb = (float)Math.random();
		}
		//disk_path = tag.getString("model_disk");
		//disk_texture_path = tag.getString("texture_disk");
		// Get disk texture and model
	       // str3=data.str1;
			if (disk_path != str1) {
				if (str1 != "") {
					subStr1 = str1.split(delimeter1);
					if (subStr1.length == 2) {
						/* if(subStr[0]!="") */disk_texture_path = subStr1[0];
						disk_texture = new ResourceLocation("minecraft:d33vaz/textures/model/" + disk_texture_path + ".png");
						/* if(subStr[1]!="") */disk_path = str1;
						disk_model = new ResourceLocation("minecraft:d33vaz/textures/model/disk/" + disk_path + ".obj");
						System.out.println(disk_model.getResourcePath());
						//d33wheel = AdvancedModelLoader.loadModel(disk_model);
					}
					else if (subStr1.length == 3) {
						if(subStr1[2].equals("kuzov")){
						/* if(subStr[0]!="") */disk_texture_path = subStr1[0];
						kuzov_texture = new ResourceLocation("minecraft:d33vaz/textures/model/" + disk_texture_path + ".png");
						/* if(subStr[1]!="") */disk_path = subStr1[1]+"_"+subStr1[2];
						kuzov_model = new ResourceLocation("minecraft:d33vaz/textures/model/kuzov/" + disk_path + ".obj");
						System.out.println(kuzov_model.getResourcePath());
						//d33wheel = AdvancedModelLoader.loadModel(disk_model);
						}
						else if(subStr1[2].equals("disk")){
							/* if(subStr[0]!="") */disk_texture_path = subStr1[0];
							disk_texture = new ResourceLocation("minecraft:d33vaz/textures/model/" + disk_texture_path + ".png");
							/* if(subStr[1]!="") */disk_path = subStr1[1]+"_"+subStr1[2];
							disk_model = new ResourceLocation("minecraft:d33vaz/textures/model/disk/" + disk_path + ".obj");
							System.out.println(disk_model.getResourcePath());
							//d33wheel = AdvancedModelLoader.loadModel(disk_model);
						}
						
					}
				} 
				else /*if (data.str1 == "") */{
					disk_texture_path = disk_path = "";
					disk_texture = new ResourceLocation("minecraft:d33vaz/textures/model/statusdisk.png");
					disk_model = disk_model1;
					
					kuzov_texture = new ResourceLocation("minecraft:d33vaz/textures/model/bort.png");
					kuzov_model = kuzov_model1;
					System.out.println(disk_model.getResourcePath());
					System.out.println(kuzov_model.getResourcePath());
					//d33wheel = AdvancedModelLoader.loadModel(disk_model);
				}
			}
		DriveableType dType = DriveableType.getDriveable(type);
		numBombs = dType.numBombSlots;
		numCargo = dType.numCargoSlots;
		numMissiles = dType.numMissileSlots;
		numGuns = dType.ammoSlots();
		engine = PartType.getPart(tag.getString("Engine"));
		ammo = new ItemStack[numGuns];
		bombs = new ItemStack[numBombs];
		missiles = new ItemStack[numMissiles];
		cargo = new ItemStack[numCargo];
		for(int i = 0; i < numGuns; i++)
			ammo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Ammo " + i));
		
		for(int i = 0; i < numBombs; i++)
			bombs[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Bombs " + i));

		for(int i = 0; i < numMissiles; i++)
			missiles[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Missiles " + i));

 		for(int i = 0; i < numCargo; i++)
			cargo[i] = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Cargo " + i));

		fuel = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Fuel"));
		disk = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Disk"));
		//if(disk != null)System.out.println(""+disk.getUnlocalizedName());
		fuelInTank = tag.getInteger("FuelInTank");
		for(EnumDriveablePart part : EnumDriveablePart.values())
		{
			parts.put(part, new DriveablePart(part, dType.health.get(part)));
		}
		for(DriveablePart part : parts.values())
		{
			part.readFromNBT(tag);
		}
    }

    public void writeToNBT(NBTTagCompound tag)
    {
		tag.setString("Type", type);
		tag.setString("Pathstr1", str1);
		
		tag.setFloat("FloatR", fr);
		tag.setFloat("FloatG", fg);
		tag.setFloat("FloatB", fb);
		//tag.setString("model_disk", disk_path);
		//tag.setString("texture_disk", disk_texture_path);
		tag.setString("Engine", engine.shortName);
		for(int i = 0; i < ammo.length; i++)
		{
			if(ammo[i] != null)
				tag.setTag("Ammo " + i, ammo[i].writeToNBT(new NBTTagCompound()));
		}
		for(int i = 0; i < bombs.length; i++)
		{
			if(bombs[i] != null)
				tag.setTag("Bombs " + i, bombs[i].writeToNBT(new NBTTagCompound()));
		}
		for(int i = 0; i < missiles.length; i++)
		{
			if(missiles[i] != null)
				tag.setTag("Missiles " + i, missiles[i].writeToNBT(new NBTTagCompound()));
		}
		for(int i = 0; i < cargo.length; i++)
		{
			if(cargo[i] != null)
				tag.setTag("Cargo " + i, cargo[i].writeToNBT(new NBTTagCompound()));
		}
		if(fuel != null)
			tag.setTag("Fuel", fuel.writeToNBT(new NBTTagCompound()));
		if(disk != null)
			tag.setTag("Disk", disk.writeToNBT(new NBTTagCompound()));
		tag.setInteger("FuelInTank", (int)fuelInTank);
		for(DriveablePart part : parts.values())
		{
			part.writeToNBT(tag);
		}
    }
	
	@Override
	public int getSizeInventory() 
	{ 
		return getDiskSlot() + 1; 
	}

    @Override
	public ItemStack getStackInSlot(int i) 
	{ 
		//Find the correct inventory
		ItemStack[] inv = ammo;
		if(i >= ammo.length)
		{
			i -= ammo.length;
			inv = bombs;
			if(i >= bombs.length)
			{
				i -= bombs.length;
				inv = missiles;
				if(i >= missiles.length)
				{
					i -= missiles.length;
					inv = cargo;
					if(i >= cargo.length)
					{
						if(i==getFuelSlot())
						return fuel;
						else
						if(i==getDiskSlot())
						return disk;
					}
				}
			}	
		}
		//Return the stack in the slot
		return inv[i];
	}

    @Override
	public ItemStack decrStackSize(int i, int j) 
	{
		//Find the correct inventory
		ItemStack[] inv = ammo;
		if(i >= ammo.length)
		{
			i -= ammo.length;
			inv = bombs;
			if(i >= bombs.length)
			{
				i -= bombs.length;
				inv = missiles;
				if(i >= missiles.length)
				{
					i -= missiles.length;
					inv = cargo;
					if(i >= cargo.length)
					{
						//Put the fuel stack in a stack array just to simplify the code
						i -= cargo.length;
						inv = new ItemStack[2];
						inv[0] = fuel;	
						inv[1] = disk;
	
						//setInventorySlotContents(getFuelSlot(), null);
						//setInventorySlotContents(getDiskSlot(), null);
					}
				}
			}	
		}
		//Decrease the stack size
		if(inv[i] != null)
        {
            /*if(inv[i].stackSize <= j)
            {
                ItemStack itemstack = inv[i];
                inv[i] = null;
                return itemstack;
            }*/
            ItemStack itemstack1 = inv[i].splitStack(j);
            if(inv[i].stackSize <= 0)
            {
                inv[i] = null;
            }
            return itemstack1;
        } else
        {
            return null;
        }
		
	}

    @Override
	public ItemStack getStackInSlotOnClosing(int i) 
	{ 
		return getStackInSlot(i);	
	}

    @Override
	public void setInventorySlotContents(int i, ItemStack stack) 
	{ 
		//Find the correct inventory
		ItemStack[] inv = ammo;
		if(i >= ammo.length)
		{
			i -= ammo.length;
			inv = bombs;
			if(i >= bombs.length)
			{
				i -= bombs.length;
				inv = missiles;
				if(i >= missiles.length)
				{
					i -= missiles.length;
					inv = cargo;
					if(i >= cargo.length)
					{
						if(i==getFuelSlot())
						//fuel = stack;
							fuel = stack;
						else
						if(i==getDiskSlot()){
							//fuel = stack;
								disk = stack;
								if(disk!=null && disk.getItem() instanceof ItemPart){
									ItemPart diskItem = (ItemPart)disk.getItem();
									if(diskItem.type.category == 11)
						                str2 = disk.getUnlocalizedName();
									  String[] subStr;
								      String delimeter = "\\."; // Разделитель
								      subStr = str2.split(delimeter);
								      if(subStr.length==2)
								      if(subStr[1] != null)
								    	  if(str1!= subStr[1])str1 =subStr[1];
									}
								else str1 = "";
								System.out.println(str1);
								// Get disk texture and model
							       // str3=data.str1;
									if (disk_path != str1) {
										if (str1 != "") {
											subStr1 = str1.split(delimeter1);
											if (subStr1.length == 2) {
												/* if(subStr[0]!="") */disk_texture_path = subStr1[0];
												disk_texture = new ResourceLocation("minecraft:d33vaz/textures/model/" + disk_texture_path + ".png");
												/* if(subStr[1]!="") */disk_path = str1;
												disk_model = new ResourceLocation("minecraft:d33vaz/textures/model/disk/" + disk_path + ".obj");
												System.out.println(disk_model.getResourcePath());
												//d33wheel = AdvancedModelLoader.loadModel(disk_model);
											}
											else if (subStr1.length == 3) {
												if(subStr1[2].equals("kuzov")){
												/* if(subStr[0]!="") */disk_texture_path = subStr1[0];
												kuzov_texture = new ResourceLocation("minecraft:d33vaz/textures/model/" + disk_texture_path + ".png");
												/* if(subStr[1]!="") */disk_path = subStr1[1]+"_"+subStr1[2];
												kuzov_model = new ResourceLocation("minecraft:d33vaz/textures/model/kuzov/" + disk_path + ".obj");
												System.out.println(kuzov_model.getResourcePath());
												//d33wheel = AdvancedModelLoader.loadModel(disk_model);
												}
												else if(subStr1[2].equals("disk")){
													/* if(subStr[0]!="") */disk_texture_path = subStr1[0];
													disk_texture = new ResourceLocation("minecraft:d33vaz/textures/model/" + disk_texture_path + ".png");
													/* if(subStr[1]!="") */disk_path = subStr1[1]+"_"+subStr1[2];
													disk_model = new ResourceLocation("minecraft:d33vaz/textures/model/disk/" + disk_path + ".obj");
													System.out.println(disk_model.getResourcePath());
													//d33wheel = AdvancedModelLoader.loadModel(disk_model);
												}
												
											}
										}
										else /*if (data.str1 == "") */{
											disk_texture_path = disk_path = "";
											disk_texture = new ResourceLocation("minecraft:d33vaz/textures/model/statusdisk.png");
											disk_model = disk_model1;
											
											kuzov_texture = new ResourceLocation("minecraft:d33vaz/textures/model/bort.png");
											kuzov_model = kuzov_model1;
											System.out.println(disk_model.getResourcePath());
											System.out.println(kuzov_model.getResourcePath());
											//d33wheel = AdvancedModelLoader.loadModel(disk_model);
										}
									}
						}
						return;
					}
				}
			}	
		}
		//Set the stack
		inv[i] = stack;
	}

    @Override
	public String getInventoryName() 
	{ 
		return "Flan's Secret Data"; 
	}

    @Override
	public int getInventoryStackLimit() 
	{ 
		return 64; 
	}

    @Override
	public void markDirty() {}

    @Override
	public boolean isUseableByPlayer(EntityPlayer player) 
	{ 
		return true; 
	}

    @Override
	public void openInventory() {}

    @Override
	public void closeInventory() {}
	
	public int getAmmoInventoryStart()
	{
		return 0;
	}
	
	public int getBombInventoryStart()
	{
		return ammo.length;
	}	
	
	public int getMissileInventoryStart()
	{
		return ammo.length + bombs.length;
	}	
	
	public int getCargoInventoryStart()
	{
		return ammo.length + bombs.length + missiles.length; 
	}
	
	public int getFuelSlot()
	{
		return ammo.length + bombs.length + missiles.length + cargo.length;
	}
	
	public int getDiskSlot()
	{
		return ammo.length + bombs.length + missiles.length + cargo.length + 1;
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) 
	{
		if(i < getBombInventoryStart() && itemstack != null && itemstack.getItem() instanceof ItemBullet) //Ammo
		{
			return true;
		}		
		if(i >= getBombInventoryStart() && i < getMissileInventoryStart() && itemstack != null && itemstack.getItem() instanceof ItemBullet) //Ammo
		{
			return true;
		}
		if(i >= getMissileInventoryStart() && i < getCargoInventoryStart() && itemstack != null && itemstack.getItem() instanceof ItemBullet)
		{
			return true;
		}
		if(i >= getCargoInventoryStart() && i < getFuelSlot())
		{
			return true;
		}
		if(i == getFuelSlot() && itemstack != null && itemstack.getItem() instanceof ItemPart && ((ItemPart)itemstack.getItem()).type.category == 9) //Fuel
		{
			return true;
		}
		if(i == getDiskSlot() && itemstack != null/* && itemstack.getItem() instanceof ItemPart && ((ItemPart)itemstack.getItem()).type.category == 9*/) //Fuel
		{
			return true;
		}

		return false;
	}
}
