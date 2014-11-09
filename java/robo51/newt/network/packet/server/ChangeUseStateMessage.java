package robo51.newt.network.packet.server;

import org.lwjgl.input.Keyboard;

import robo51.newt.Newt;
import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * 
 * A simple message telling the server that the client wants to open a GUI.
 * 
 */
public class ChangeUseStateMessage implements IMessage {

	// The basic, no-argument constructor MUST be included to use the new automated handling
	public ChangeUseStateMessage() {}
	
	public static class Handler extends AbstractServerMessageHandler<ChangeUseStateMessage> {
		@Override
		public IMessage handleServerMessage(EntityPlayer player, ChangeUseStateMessage message, MessageContext ctx) {
			if(player.isInWater()) {
				player.setAir(10);
				player.motionY = 0.005D;
				if(player.isCollidedHorizontally) {
					player.motionY = 0.2D;
				} else {
					player.motionY = -1.0D;
				}
			}
			return null;
		}
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		// TODO Auto-generated method stub
		
	}
}