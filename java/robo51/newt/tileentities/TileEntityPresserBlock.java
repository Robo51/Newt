package robo51.newt.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import robo51.newt.blocks.PresserBlock;
import robo51.newt.crafting.PresserBlockRecipes;
import robo51.newt.items.ModItems;

public class TileEntityPresserBlock extends TileEntity implements ISidedInventory {
	 
    public static final String publicName = "tileEntityPresserBlock";
    private String name = "tileEntityPresserBlock";
    
    private ItemStack slots[];
    
    public int dualPower;
    public int dualCookTime;
    public static final int maxPower = 1001;
    public static final int pressingSpeed = 50;
    
    private static final int[] slots_top = new int[] {0, 1};
    private static final int[] slots_bottom = new int[] {2};
    private static final int[] slots_side = new int[] {3};
 
    public String getName() {
 
        return name;
    }
    
    public TileEntityPresserBlock() {
    	slots = new ItemStack[4];
    	}

    public void setGuiDisplayName(String displayName) {
		this.name = displayName;
	}
    
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.name : "container.presserBlock";
	}

	public boolean hasCustomInventoryName() {
		return this.name != null && this.name.length() > 0;
	}
    
	public int getSizeInventory() {
		return slots.length;
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
		if (slots[i] != null) {
			ItemStack itemstack = slots[i];
			slots[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.slots[i] = itemstack;
		
		if(itemstack != null && itemstack.stackSize > getInventoryStackLimit()) {
			itemstack.stackSize = getInventoryStackLimit();
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
		return i == 3 ? false : (i == 2 ? hasItemPower(itemstack) : true);
	}
	
	public static boolean hasItemPower(ItemStack itemstack) {
		return getItemPower(itemstack) > 0;
	}
	
	private static int getItemPower (ItemStack itemstack) {
		if (itemstack == null) {
			return 0;
		} else {
			Item item = itemstack.getItem();
			
			if (item == ModItems.redcoalItem) return 100;
			
			return 0;
		}
	}
	
	public boolean isPressing() {
		return this.dualCookTime > 0;
	}
	
	public void updateEntity() {
		boolean flag = this.hasPower();
		boolean flag1= false;
		
		if(this.hasPower() && this.isPressing()) {
			this.dualPower--;
		}
		
		if(!worldObj.isRemote) {
			if (this.hasItemPower(this.slots[2]) && this.dualPower < (this.maxPower - this.getItemPower(this.slots[2]))) {
				this.dualPower += getItemPower(this.slots[2]);
				
				if(this.slots[2] != null) {
					flag1 = true;
					
					this.slots[2].stackSize--;
					
					if(this.slots[2].stackSize == 0) {
						this.slots[2] = this.slots[2].getItem().getContainerItem(this.slots[2]);
					}
				}
			}
			if (this.hasPower() && this.canPress()) {
				this.dualCookTime++;
				
				if (this.dualCookTime == this.pressingSpeed) {
					this.dualCookTime = 0;
					this.pressItem();
					flag1 = true;
				}
			} else {
				this.dualCookTime = 0;
			}
			
			if (flag != this.isPressing()) {
				flag1 = true;
				PresserBlock.updatePresserBlockState(this.isPressing(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if (flag1) {
			this.markDirty();
		}
    }
	
	private boolean canPress() {
		if (slots[0] == null || slots[1] == null) {
			return false;
		}
		
		ItemStack itemstack = PresserBlockRecipes.getPressingResult(slots[0].getItem(), slots[1].getItem());
		
		if (itemstack == null) {
			return false;
		}
		
		if (slots[3] == null) {
			return true;
		}
		
		if (!slots[3].isItemEqual(itemstack)) {
			return false;
		}
		
		if (slots[3].stackSize < getInventoryStackLimit() && slots[3].stackSize < slots[3].getMaxStackSize()) {
			return true;
		} else {
			return slots[3].stackSize < itemstack.getMaxStackSize();
		}
	}
	
	private void pressItem() {
		if (canPress()) {
			ItemStack itemstack = PresserBlockRecipes.getPressingResult(slots[0].getItem(), slots[1].getItem());
			
			if (slots[3] == null) {
				slots[3] = itemstack.copy();
			} else if (slots[3].isItemEqual(itemstack)) {
				slots[3].stackSize += itemstack.stackSize;
			}
			
			for (int i = 0; i < 2; i++) {
				if (slots[i].stackSize <= 0) {
					slots[i] = new ItemStack(slots[i].getItem().setFull3D());
				}else{
					slots[i].stackSize--;
				}
				
				if (slots[i].stackSize <= 0){
					slots[i] = null;
				}
			}
		}
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) {
		return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_side);
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
		return dualPower > 0;
	}
	
	public int getPresserProgressScaled(int i) {
		return (dualCookTime * i) / this.pressingSpeed;
	}
	
	public int getPowerRemainingScaled(int i) {
		return (dualPower * i) / maxPower;
	}
	
	public void readFromNBT (NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("Items", 10);
		slots = new ItemStack[getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound nbt1 = (NBTTagCompound)list.getCompoundTagAt(i);
			byte b0 = nbt1.getByte("Slot");
			
			if (b0 >= 0 && b0 < this.slots.length) {
				this.slots[b0] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
		
		this.dualPower = (int)nbt.getShort("PowerTime");
		this.dualCookTime = (int)nbt.getShort("CookTime");
		
		if(nbt.hasKey("CustomName")) {
			this.name = nbt.getString("CustomName");
		}
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		
		nbt.setShort("PowerTime", (short)this.dualPower);
		nbt.setShort("CookTime", (short)this.dualCookTime);
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < this.slots.length; i++) {
			if (this.slots[i] != null) {
				NBTTagCompound nbt1 = new NBTTagCompound();
				nbt1.setByte("Slot", (byte)i);
				this.slots[i].writeToNBT(nbt1);
				list.appendTag(nbt1);
			}
		}
		
		nbt.setTag("Items", list);
		
		if (this.hasCustomInventoryName()) {
			nbt.setString("CustomName", this.name);
		}
	}
}