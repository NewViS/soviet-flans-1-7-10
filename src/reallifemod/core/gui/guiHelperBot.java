package de.ItsAMysterious.mods.reallifemod.core.gui;

import de.ItsAMysterious.mods.reallifemod.core.entitys.npcs.EntityRobot;
import de.ItsAMysterious.mods.reallifemod.core.handlers.SpeechHandler;
import java.awt.Color;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.ClickEvent.Action;
import net.minecraft.util.ChatComponentText;
import org.lwjgl.input.Keyboard;

public class guiHelperBot extends GuiScreen {

   public GuiTextField chatline;
   private SpeechHandler handler;
   private boolean saidname = false;
   public boolean warnWhenNight;
   private int voicelevel = 0;
   EntityRobot theBot;
   public static int GUI_ID;


   public guiHelperBot(EntityRobot bot) {
      this.theBot = bot;
      this.handler = new SpeechHandler();
      Keyboard.enableRepeatEvents(true);
   }

   public void func_73866_w_() {
      super.initGui();
      this.chatline = new GuiTextField(this.field_146289_q, this.field_146294_l / 2 - 150, this.field_146295_m / 2 - 52, 300, 15);
      this.chatline.setCanLoseFocus(true);
      this.chatline.setFocused(true);
      this.chatline.setMaxStringLength(200);
      this.field_146292_n.add(new GuiButton(0, this.field_146294_l / 2 - 100, this.field_146295_m / 2, "Ask/Say something"));
      this.field_146292_n.add(new GuiButton(1, this.field_146294_l / 2 - 100, this.field_146295_m / 2 + 25, "Talk to me zanor!(speak line)"));
      this.field_146292_n.add(new GuiButton(2, this.field_146294_l / 2 - 100, this.field_146295_m / 2 + 50, 100, 20, "Voice up"));
      this.field_146292_n.add(new GuiButton(3, this.field_146294_l / 2, this.field_146295_m / 2 + 50, 100, 20, "Set remembrance"));
   }

   public boolean func_73868_f() {
      return true;
   }

   public void func_73863_a(int x, int y, float f) {
      this.field_146292_n.add(new GuiButton(4, this.field_146294_l - 100, 5, 75, 20, this.theBot.shouldFollow?"Stop Following":"Start Following"));
      if(this.chatline != null) {
         this.chatline.drawTextBox();
      }

      this.func_73731_b(this.field_146289_q, "Voicelevel: " + this.voicelevel, this.field_146294_l / 2 - 100, this.field_146295_m / 2 - 25, Color.white.getRGB());
      this.func_73732_a(this.field_146289_q, "Write your question/sentence in the box and click \'ask robot\'! ", this.field_146294_l / 2, this.field_146295_m / 2 - 75, Color.white.getRGB());
      long time = this.field_146297_k.theWorld.func_72820_D();
      int days = (int)(time / 24000L);
      int hours = (int)((time + 12000L - (long)(days * 24000)) / 20L / 60L);
      int minutes = (int)((time + 12000L - (long)(days * 24000) - (long)(hours * 60 * 20)) / 20L);
      this.func_73731_b(this.field_146289_q, "Time: " + days + ":" + hours + ":" + minutes, 5, 5, Color.white.getRGB());
      super.drawScreen(x, y, f);
   }

   public void func_146284_a(GuiButton button) {
      SpeechHandler var10000;
      if(button.id == 0) {
         var10000 = this.handler;
         SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, this.getAnswer(this.chatline.getText()));
      }

      if(button.id == 1) {
         var10000 = this.handler;
         SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, this.getAnswer(this.chatline.getText().toLowerCase()));
      }

      if(button.id == 2) {
         ++this.voicelevel;
      }

      if(button.id == 4) {
         this.func_73878_a(true, 4);
         this.theBot.shouldFollow = !this.theBot.shouldFollow;
      }

   }

   protected void func_73869_a(char theChar, int id) {
      super.keyTyped(theChar, id);
      if(id == 28) {
         this.func_146284_a((GuiButton)this.field_146292_n.get(0));
      }

      this.chatline.textboxKeyTyped(theChar, id);
   }

   protected void func_146286_b(int p_146286_1_, int p_146286_2_, int p_146286_3_) {
      this.chatline.mouseClicked(p_146286_1_, p_146286_2_, p_146286_3_);
   }

   public String getAnswer(String question) {
      if(question.contains("money")) {
         return "You can earn money by mining ores or by Killing mobs. You can get up to 1000$ per collected ore!";
      } else if(question.contains("buy")) {
         return "To buy something you have to craft a computer out of TWO circuit boards, FIVE iron-ingots and TWO redstone!";
      } else if(question.contains("circuitboard")) {
         return "Kill Zombies or robots to get Circuitboards!";
      } else {
         SpeechHandler var10000;
         if(question.contains("shut up")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Nope!");
         } else if(question.contains("like")) {
            if(question.contains("don\'t") && question.contains("you")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 0.0F, "Stop weisting my Time!");
            }

            if(!question.contains("don\'t") && question.contains("you")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 0.0F, "That\'s nice!");
            }

            if(!question.contains("don\'t") && question.contains("me")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Of course i do!");
            }
         }

         if(question.contains("what") && question.contains("can")) {
            if(question.contains("you")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Many things!");
            }

            if(question.contains("i") && question.contains("do")) {
               Random time = new Random();
               boolean sound = false;
               int sound1 = time.nextInt(6);
               switch(sound1 + 1) {
               case 1:
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Go in your favorite mine, but don\'t forget the torches again!");
                  break;
               case 2:
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "I don\'t know everything!");
                  break;
               case 3:
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Build a new House!");
                  break;
               case 4:
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Wait for some monsters and kill them!");
                  break;
               case 5:
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Dig a new mine!");
               case 6:
               default:
                  break;
               case 7:
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 20.0F, "Take a nap!");
               }
            }
         } else if(question.contains("what does the fox say")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(2.0D, 7.0F, 25.0F, 35.0F, "Ring ding ding ding dinkeding, gering ding ding ding dinkeding!");
         } else if(question.equals("hello") && this.chatline.getText().substring(5) != "") {
            EntityRobot var12 = this.theBot;
            EntityRobot.name = question.substring(5).split(" ")[1];
         } else if(question.contains("radio")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "The modmaker says, he is sorry, but the radio can not be used at the moment. He is working on easier way to play songs and also CD\'s in Minecraft.");
         } else if(question.equals("what is your name?")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "My name is Zanor! And what is your name?");
         } else if(question.contains("me") && this.chatline.getText().toLowerCase().contains("joke")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "What is a Fish without Eyes?.       A Fffsche!!");
         } else if(question.contains("I am your coder!")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "What can i do for you Master?");
         }

         if(question.contains("Star Wars")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, (float)(this.voicelevel * 10), 0.0F, (float)(this.voicelevel * 5), "I am your FAAATHER!!!!");
         }

         if(question.contains("my name is")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Hello " + question.split("is ")[1] + "!");
            this.saidname = true;
         } else if(question.contains("bye")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "See you later!");
         } else if(question.contains("what can you")) {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "I can speak with you and you can speak with me!");
         } else if(!question.contains("hello") && !this.chatline.getText().toLowerCase().contains("hi")) {
            if(question.contains("ask you something")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Ask me any question you want!");
            } else if(question.contains("fuck")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Im a robot, not a sextoy!");
            } else if(question.contains("you") && this.chatline.getText().toLowerCase().contains("asshole")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Stop swearing !");
            } else if(question.contains("you") && this.chatline.getText().toLowerCase().contains("hate")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "I don\'t have any feelings! You don\'t have to love me!");
            } else if(question.contains("what") && question.contains("you") && this.chatline.getText().toLowerCase().contains("doing")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Waiting for some questions i can answer better.");
            } else if(question.contains("how") && question.contains("are") && this.chatline.getText().toLowerCase().contains("you")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Im fine, thanks! And you ?");
            } else {
               if(question.contains("i") && question.contains("love") && this.chatline.getText().toLowerCase().contains("you")) {
                  return "That\'s nice ! Just ask me when you need something! Ok?";
               }

               if((question.contains("me") || this.chatline.getText().toLowerCase().contains("im")) && (question.toLowerCase().contains("too") || question.contains("good") || this.chatline.getText().toLowerCase().contains("fine"))) {
                  return "That\'s nice !";
               }

               if(question.contains("suck my dick")) {
                  return "No i don\'t, do it on your own!";
               }

               if(question.contains("are you human")) {
                  return "No, i am a robot!";
               }

               if(question.contains("real life mod") && (this.chatline.getText().toLowerCase().contains("cool") || question.contains("awesome") || this.chatline.getText().toLowerCase().contains("sick"))) {
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "The modmakers say:<<THANK YOU! Whe are doing our best to make it COOL !>>.");
               } else if(question.contains("real life mod") && this.chatline.getText().toLowerCase().contains("don\'t like")) {
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Well, why have you downloaded it then ?");
               } else if(question.contains("when") && question.contains("will") && question.contains("mod") && this.chatline.getText().toLowerCase().contains("update")) {
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Next update will be on Saturday the 7th of March 2015 at 6 pm, german time!");
               } else if(question.contains("who ") && this.chatline.getText().toLowerCase().contains("modmakers")) {
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "ItsAMysterious is the leed programmer, he had the idea and started coding the hole thing. He also makes some 3D models. Treedent Killer is the graphics designer and helps with websitehosting, 3D-moddeling and the Real life craft-server. El ar a 10 is making minecraft-styled 3d models for version Alpha 0.14!");
               } else if(!question.contains("penis") && !this.chatline.getText().toLowerCase().contains("dick")) {
                  if(question.contains("against") && this.chatline.getText().toLowerCase().contains("gay")) {
                     var10000 = this.handler;
                     SpeechHandler.speechSynth(1.0D, 0.0F, (float)this.voicelevel, 0.0F, "No, but if you are not gay, you should not ask about penises!");
                  } else if(question.contains("what is planned")) {
                     var10000 = this.handler;
                     SpeechHandler.speechSynth(1.0D, (float)this.voicelevel, 16.0F, 0.0F, "Have a look at the link in chat, to see which features are planned for the Real Life Mod!");
                     ChatComponentText time1 = new ChatComponentText("http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2196915-real-life-mod-updated-alpha-0-12-realistic");
                     time1.getChatStyle().setChatClickEvent(new ClickEvent(Action.OPEN_URL, "http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/wip-mods/2196915-real-life-mod-updated-alpha-0-12-realistic"));
                     Minecraft.getMinecraft().thePlayer.func_146105_b(time1);
                  } else if(question.contains("what is the meaning") && question.contains("of life") && question.contains("universe") && this.chatline.getText().toLowerCase().contains("everything")) {
                     return "42";
                  }
               } else {
                  var10000 = this.handler;
                  SpeechHandler.speechSynth(1.0D, (float)this.voicelevel, 16.0F, 0.0F, "Are you a boy? Then stop asking about penises, because if you do so, you must be gay!");
               }
            }
         } else {
            var10000 = this.handler;
            SpeechHandler.speechSynth(1.0D, 7.0F, 16.0F, 0.0F, "Hello! What\'s your name?");
         }

         long time2 = this.field_146297_k.theWorld.func_72820_D();
         int days = (int)(time2 / 24000L);
         int hours = (int)((time2 + 12000L - (long)(days * 24000)) / 20L / 60L);
         int minutes = (int)((time2 + 12000L - (long)(days * 24000) - (long)(hours * 60 * 20)) / 20L);
         System.out.println("time: " + hours + " " + minutes);
         if(question.contains("time") && this.chatline.getText().toLowerCase().contains("is")) {
            return String.valueOf(hours + " hours and " + minutes + " minutes");
         } else if(question.equals("Why you don\'t know everything")) {
            return "Cuz im not human!";
         } else {
            if(question.contains("kill me")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, -50.0F, 7.5F, "Do not end your life It\'s your only one. Killing yourself would make things even wors-It makes people sad, who love you and those who don\'t love you, would be happy!!! Speaking about problems will help more, than anything else!");
            } else if(question.contains("lol")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, 10.0F, 7.5F, "HAHAHAHAHAHAHAHA!!!HHIHIHIHIHIHIHIHIHI");
            } else if(question.contains("lol")) {
               var10000 = this.handler;
               SpeechHandler.speechSynth(2.0D, 7.0F, 10.0F, 7.5F, "HAHAHAHAHAHAHAHA!!!HHIHIHIHIHIHIHIHIHI");
            } else if(question.contains("name")) {
               StringBuilder var13 = (new StringBuilder()).append("My name is:");
               EntityRobot var10001 = this.theBot;
               String var11;
               if(EntityRobot.name != "") {
                  var10001 = this.theBot;
                  var11 = EntityRobot.name;
               } else {
                  var11 = "set a name for me!";
               }

               return var13.append(var11).toString();
            }

            if(question.contains("?")) {
               return "I cant answer that!Ask another question please!";
            } else {
               if(question.contains("!")) {
                  Random random = new Random();
                  switch(random.nextInt(5)) {
                  case 0:
                     return "Cool!";
                  case 1:
                     return "Your kidding...";
                  case 2:
                     return "NO WAY!";
                  case 3:
                     return "Awesome!";
                  case 4:
                     return "Sweet!";
                  }
               }

               return "Please write something in the box!";
            }
         }
      }
   }
}
