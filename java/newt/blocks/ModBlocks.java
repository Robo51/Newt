package newt.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import newt.lib.Constants;

public final class ModBlocks {
	
	public static Block redcoalBlock;
	public static Block mageniteoreBlock;
	public static Block presserBlock;
	
	public static void init() {
		
		redcoalBlock = new RedCoalBlock();
		mageniteoreBlock = new MageniteOreBlock();
		presserBlock = new PresserBlock();
	}

}
