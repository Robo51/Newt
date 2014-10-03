package newt.tileentities;

import net.minecraft.tileentity.TileEntity;

public class TileEntityPresserBlock extends TileEntity{
	
	public int tick = 0;
	 
    public static final String publicName = "tileEntityPresserBlock";
    private String name = "tileEntityPresserBlock";
 
    public String getName() {
 
        return name;
    }
    
    @Override
    public void updateEntity() {
 
        if(!worldObj.isRemote) {
 
            tick++;
            if(tick == 100) {
 
                this.worldObj.setWorldTime(1000);
                tick = 0;
            }
        }
    }
}