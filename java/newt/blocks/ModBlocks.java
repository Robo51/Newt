package Newt.blocks;

import net.minecraft.block.Block;

public final class ModBlocks {
	
	public static Block redcoalBlock;
	public static Block mageniteoreBlock;
	public static Block presserBlock;
	public static Block solarcellBlock;
	
    public static void init(){
    	
		redcoalBlock = new RedCoalBlock();
		mageniteoreBlock = new MageniteOreBlock();
		presserBlock = new PresserBlock();
		solarcellBlock = new SolarCellBlock();
    }
}
