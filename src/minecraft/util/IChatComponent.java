package net.minecraft.util;

import java.util.List;
import net.minecraft.util.ChatStyle;

public interface IChatComponent extends Iterable {

   IChatComponent setChatStyle(ChatStyle var1);

   ChatStyle getChatStyle();

   IChatComponent appendText(String var1);

   IChatComponent appendSibling(IChatComponent var1);

   String getUnformattedTextForChat();

   String getUnformattedText();

   String getFormattedText();

   List getSiblings();

   IChatComponent createCopy();
}
