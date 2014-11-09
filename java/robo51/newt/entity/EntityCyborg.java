package robo51.newt.entity;

import robo51.newt.items.ModItems;
import robo51.newt.lib.Constants;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeModContainer;

public class EntityCyborg extends EntityMob {

	public EntityCyborg(World world) {
		super(world);
		
		this.setSize(0.8F, 2.5F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	protected String getLivingSound() {
        return Constants.MODID + ":" + "cyborg-say";
    }

    protected String getHurtSound() {
        return Constants.MODID + ":" + "cyborg-hurt";
    }

    protected String getDeathSound() {
        return Constants.MODID + ":" + "cyborg-death";
    }
    
    protected void func_145780_a(int p_145780_1_, int p_145780_2_, int p_145780_3_, Block p_145780_4_)
    {
        this.playSound("mob.irongolem.walk", 0.5F, 1.0F);
    }
    
    protected Item getDropItem() {
        return ModItems.smallbatteryItem;
    }
    
    protected void dropRareDrop(int par1) {
        this.entityDropItem(new ItemStack(Items.diamond), 0.0F);
    }
	
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(3.0D);
    }
    
    public void onLivingUpdate() {
    	if(this.isWet()) {
            this.attackEntityFrom(DamageSource.drown, 2.0F);
        }
        super.onLivingUpdate();
    }
    
    protected boolean isAIEnabled() {
        return true;
    }

}
