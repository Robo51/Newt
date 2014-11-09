package robo51.newt.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import robo51.newt.Newt;
import robo51.newt.lib.Constants;
import net.minecraft.block.Block;

public final class ModBlocks {
	//Custom Models
	public static Block neuralcellBlock;
	
	//Storage Blocks
	public static Block redcoalBlock;
	public static Block mageniteBlock;
	
	//Ores
	public static Block mageniteoreBlock;
	public static Block aluminiumoreBlock;
	public static Block zincoreBlock;
	public static Block nickeloreBlock;
	public static Block copperoreBlock;
	
	//Machines
	public static Block presserBlock_Idle;
	public static Block presserBlock_Active;
	public static final int guiIdPresserBlock = 1;
	
	public static Block solarcellBlock;
	public static Block chassisBlock;
	public static Block powercellBlock;
	
	public static Block furnaceBlock_Idle;
	public static Block furnaceBlock_Active;
	public static final int guiIdFurnaceBlock = 0;
	
    public static void init(){
    	//Custom Models
    	neuralcellBlock = new NeuralCellBlock();
    	
    	//Storage Blocks
		redcoalBlock = new RedCoalBlock();
		mageniteBlock = new MageniteBlock();
		
		//Ores
		mageniteoreBlock = new MageniteOreBlock();
		aluminiumoreBlock = new AluminiumOreBlock();
		zincoreBlock = new ZincOreBlock();
		nickeloreBlock = new NickelOreBlock();
		copperoreBlock = new CopperOreBlock();
		
		//Machines
		presserBlock_Idle = new PresserBlock(false).setBlockName(Constants.MODID + "_" + "presserBlock_Idle").setCreativeTab(Newt.tabNewt);
		presserBlock_Active = new PresserBlock(true).setBlockName(Constants.MODID + "_" + "presserBlock_Active");		
		
		solarcellBlock = new SolarCellBlock();
		chassisBlock = new ChassisBlock();
		powercellBlock = new PowerCellBlock();
		
		furnaceBlock_Idle = new FurnaceBlock(false).setBlockName(Constants.MODID + "_" + "furnaceBlock_Idle").setCreativeTab(Newt.tabNewt);
		furnaceBlock_Active = new FurnaceBlock(true).setBlockName(Constants.MODID + "_" + "furnaceBlock_Active").setLightLevel(0.5F);
		
		
		
		GameRegistry.registerBlock(presserBlock_Idle, "presserBlock_Idle");
		GameRegistry.registerBlock(presserBlock_Active, "presserBlock_Active");
		
		GameRegistry.registerBlock(furnaceBlock_Idle, "furnaceBlock_Idle");
		GameRegistry.registerBlock(furnaceBlock_Active, "furnaceBlock_Active");
    }

}