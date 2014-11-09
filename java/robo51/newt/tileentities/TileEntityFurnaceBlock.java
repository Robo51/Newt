package robo51.newt.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import robo51.newt.blocks.FurnaceBlock;
import robo51.newt.items.ModItems;

public class TileEntityFurnaceBlock extends TileEntity implements ISidedInventory {
	
    public static final String publicName = "tileEntityFurnaceBlock";
    private String name = "tileEntityFurnaceBlock";
    
    private ItemStack slots[];
    
    public int burnPower;
    public int cookTime;
    public static final int maxPower = 1001;
    public static final int furnaceSpeed = 13;
    
    private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {1};
    private static final int[] slots_side = new int[] {2};
 
    public String getName() {
 
        return name;
    }
    
    public TileEntityFurnaceBlock() {
    	slots = new ItemStack[3];
    }

	public void setGuiDisplayName(String displayName) {
		this.name = displayName;
	}
	
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : "container.furnaceBlock";
	}

	public boolean hasCustomInventoryName() {
		return this.name != null && this.name.length() > 0;
	}
	
	public int getSizeInventory() {
		return this.slots.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return slots[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		if (slots[i] != null) {
			if (slots[i].stackSize <= j) {
				ItemStack itemstack = slots[i];
				slots[i] = null;
				return itemstack;
			}
			
			ItemStack itemstack1 = slots[i].splitStack(j);
			
			if (slots[i].stackSize == 0) {
				slots[i] = null;
			}
			
			return itemstack1;
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		if(this.slots[i] != null) {
			ItemStack itemstack = this.slots[i];
			this.slots[i] = null;
			return itemstack;
		} else {
		return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > this.getInventoryStackLimit()) {
			itemstack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		if (worldObj.getTileEntity(xCoord, yCoord, zCoord) != this) {
			return false;
		} else {
			return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64;
		}
	}

	public void openInventory() {}
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return i == 2 ? false : (i == 1 ? hasItemPower(itemstack) : true);
	}
	
	public static boolean hasItemPower(ItemStack itemstack) {
		return getItemPower(itemstack) > 0;
	}

	private static int getItemPower(ItemStack itemstack) {
		if(itemstack == null) {
			return 0;
		} else {
			Item item = itemstack.getItem();
			
			if (item == ModItems.redcoalItem) return 100;
			
			return 0;
		}
	}
	
	public boolean isBurning() {
		return this.cookTime > 0;
	}
	
	public void updateEntity() {
		boolean flag = this.hasPower();
		boolean flag1 = false;
		
		if(this.hasPower() && this.isBurning()) {
			this.burnPower--;
		}
		
		if(!this.worldObj.isRemote) {
			if(this.hasItemPower(this.slots[1]) && this.burnPower < (this.maxPower - this.getItemPower(this.slots[1]))) {
				this.burnPower += getItemPower(this.slots[1]);
					
				if(this.slots[1] != null)  {
					flag1 = true;
					
					this.slots[1].stackSize--;
						
					if(this.slots[1].stackSize == 0) {
						this.slots[1] = this.slots[1].getItem().getContainerItem(this.slots[1]);
					}
				}		
			}
			if(this.hasPower() && this.canSmelt()) {
				this.cookTime++;
				
				if(this.cookTime == this.furnaceSpeed) {
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
				
				if(flag != this.isBurning()) {
					flag1 = true;
					FurnaceBlock.updateFurnaceBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
				}
		}
		if(flag1) {
			this.markDirty();
		}
	}
	
	public boolean canSmelt() {
		if(this.slots[0] == null) {
			return false;
		} else {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(itemstack == null) return false;
			if(this.slots[2] == null) return true;
			if(!this.slots[2].isItemEqual(itemstack)) return false;
			
			int result = this.slots[2].stackSize + itemstack.stackSize;
			
			return (result <= getInventoryStackLimit() && result <= itemstack.getMaxStackSize());
		}
	}
	
	public void smeltItem() {
		if(this.canSmelt()) {
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.slots[0]);
			
			if(this.slots[2] == null) {
				this.slots[2] = itemstack.copy();
			} else if(this.slots[2].isItemEqual(itemstack)) {
				this.slots[2].stackSize += itemstack.stackSize;
			}
			
			this.slots[0].stackSize--;
			
			if(this.slots[0].stackSize <= 0) {
				this.slots[0] = null;
			}
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) {
		return i == 0 ? slots_bottom :  (i == 1 ? slots_top : slots_side);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemstack, int j) {
		return this.isItemValidForSlot(i, itemstack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemstack, int j) {
		return j != 0 || i != 1 || itemstack.getItem() == Items.bucket;
	}
	
	public boolean hasPower() {
		return burnPower > 0;
	}
	
	public int getCookProgressScaled(int i) {
		return (cookTime * i) / this.furnaceSpeed;
	}
	
	public int getBurnTimeRemainingScaled(int i) {
		return (burnPower * i) / maxPower;
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		
		NBTTagList list = nbt.getTagList("Items", 10);
		this.slots = new ItemStack[this.getSizeInventory()];
		
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = (NBTTagCompound) list.getCompoundTagAt(i);
			byte b = compound.getByte("Slot");
			
			if(b >= 0 && b < this.slots.length) {
				this.slots[b] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
		this.cookTime = (int)nbt.getShort("CookTime");
		this.burnPower = (int)nbt.getShort("BurnPower");
		
		if(nbt.hasKey("CustomName")) {
			this.name = nbt.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setShort("CookTime", (short)this.cookTime);
		nbt.setShort("BurnPower", (short)this.burnPower);
		
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < this.slots.length; i++) {
			if(this.slots[i] != null) {
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte)i);
				this.slots[i].writeToNBT(compound);
				list.appendTag(compound);
			}
		}
		
		nbt.setTag("Items", list);
		
		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.name);
		}
	}
}