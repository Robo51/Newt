package newt.items;

import net.minecraft.item.Item;

public final class ModItems {

	public static Item redcoalItem;
	public static Item redcoalingotItem;
	public static Item redcoalbreadItem;
	public static Item magenitedustItem;
	
	public static void init() {
		
		redcoalItem = new RedCoalItem();
		redcoalingotItem = new RedCoalIngotItem();
		redcoalbreadItem = new RedCoalBreadItem(5, 0.2f, false);
		magenitedustItem = new MageniteDustItem();
		
	}
}
