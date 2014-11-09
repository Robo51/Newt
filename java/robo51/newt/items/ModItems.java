package robo51.newt.items;

import net.minecraft.item.Item;

public final class ModItems {
	//Basic Materials
	public static Item redcoalItem;
	public static Item magenitedustItem;
	public static Item unrefinedmageniteItem;
	public static Item saltpaperItem;
	public static Item paperstackItem;
	public static Item copperstackItem;
	
	//Ingot
	public static Item redcoalingotItem;
	public static Item mageniteingotItem;
	public static Item aluminiumingotItem;
	public static Item zincingotItem;
	public static Item alumicingotItem;
	public static Item nickelingotItem;
	public static Item copperingotItem;
	
	//Food
	public static Item redcoalbreadItem;
	
	//Electronics
	public static Item smallbatteryItem;
	public static Item tempbatteryItem;
	public static Item coppercoilItem;
	public static Item motorcoilItem;
	
	public static void init() {
		//Basic Materials
		redcoalItem = new RedCoalItem();	
		magenitedustItem = new MageniteDustItem();
		unrefinedmageniteItem = new UnrefinedMageniteItem();
		saltpaperItem = new SaltPaperItem();
		paperstackItem = new PaperStackItem(null);
		copperstackItem = new CopperStackItem(null);
		
		//Ingot
		redcoalingotItem = new RedCoalIngotItem();
		mageniteingotItem = new MageniteIngotItem();
		aluminiumingotItem = new AluminiumIngotItem();
		zincingotItem = new ZincIngotItem();
		alumicingotItem = new AlumicIngotItem();
		nickelingotItem = new NickelIngotItem();
		copperingotItem = new CopperIngotItem();
		
		//Food
		redcoalbreadItem = new RedCoalBreadItem(5, 0.5F, false);
		
		//Electronics
		smallbatteryItem = new SmallBatteryItem();
		tempbatteryItem = new TempBatteryItem(2, 0.2F, false);
		coppercoilItem = new CopperCoilItem();
		motorcoilItem = new MotorCoilItem();
		
		
	}

}