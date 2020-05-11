package de.ItsAMysterious.mods.reallifemod.core.handlers;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class RLMPacketHandler implements IMessageHandler, IMessage {

   public void fromBytes(ByteBuf buf) {}

   public void toBytes(ByteBuf buf) {}

   public IMessage onMessage(IMessage message, MessageContext ctx) {
      return null;
   }
}
