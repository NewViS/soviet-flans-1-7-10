//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33nissan; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelsilvia15 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation car_chassis = new ResourceLocation("minecraft:d33nissan/textures/model/undercarriage.png");
	private ResourceLocation sil15_bdg = new ResourceLocation("minecraft:d33nissan/textures/model/sil15_bdg.png");
	private ResourceLocation sil15_lights = new ResourceLocation("minecraft:d33nissan/textures/model/sil15_lights.png");
	/*private ResourceLocation r34_ext = new ResourceLocation("minecraft:d33nissan/textures/model/r34_ext.png");
	private ResourceLocation r34_lights = new ResourceLocation("minecraft:d33nissan/textures/model/r34_lights.png");
	private ResourceLocation r34_misc = new ResourceLocation("minecraft:d33nissan/textures/model/r34_misc.png");
	private ResourceLocation carbon_black = new ResourceLocation("minecraft:d33nissan/textures/model/carbon_black.png");
	private ResourceLocation strel = new ResourceLocation("minecraft:d33nissan/textures/model/strel1.png");*/
	public Modelsilvia15() //Same as Filename
	{	    	
		    steer = sil15_bdg; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33nissan/textures/model/silvia151.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = 32.928F;
	        steerY = 81.344F;
	        steerZ = -35.278F;
	        steerR = -19.413F;
	        
	        wheelX = 61F;
	        wheelX1 = 61F;
	        wheelY = 29F;
	        wheelZ = -135F;
	        wheelZ1 = 118F;
	        
	        maxSpedoAngle = 230F;
	        //mSpeed = 210;
	        //elSpedo = true;
	    	//list = GL11.glGenLists(1);
	        //GL11.glNewList(list, GL11.GL_COMPILE);
	   	  	        
	        //GL11.glEndList();
	    	 
	        translateAll(0F, 0F, 0F);
	        
	 		//flipAll();	    
	}
	 @Override
	 public void renderColoredParts(){
		 	model.renderPart("winga_body_2");
			model.renderPart("trunk_body");
			model.renderPart("mirrorR_body");
			model.renderPart("mirrorL_body");
			model.renderPart("hooda_body");
			model.renderPart("bumperRa_body");
			model.renderPart("bumperFa_body");
			model.renderPart("body_body");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.1, 1.1, 1.2);
	 }
	 
	 @Override
	 public void pre(){
		 //GL11.glTranslatef(0.0F, 0.0F, 0.0F);	
		 //GL11.glShadeModel(GL11.GL_SMOOTH);
	 }
	 
	/* @Override
	 public void renderSpedo(){
		 GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(46.857F, 82.257F, -63.852F);
			 GL11.glRotatef(-10F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(10F, 1f, 0f, 0f);
			 GL11.glTranslatef(-46.857F, -82.257F, 63.852F);

			 Minecraft.getMinecraft().renderEngine.bindTexture(strel);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();
	 }*/
	 
	 @Override
	 public void renderTexturedParts(){
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(sil15_bdg);
			model.renderPart("trunk_misc");
		 	model.renderPart("trunk_chrome");
			model.renderPart("trunk_zmk");
			model.renderPart("taillightR_rubber_trim");
			model.renderPart("taillightL_rubber_trim");
			model.renderPart("seatR_interior");
			model.renderPart("seatL_interior");
			model.renderPart("mirrorR_chrome");
			model.renderPart("mirrorR_black");
			model.renderPart("mirrorL_chrome");
			model.renderPart("mirrorL_black");
			model.renderPart("interior_interior");
			model.renderPart("interior_chrome");
			model.renderPart("hooda_rubber_trim");
			model.renderPart("hooda_frame");
		 	model.renderPart("headlightR_rubber_trim");
			model.renderPart("headlightR_frame");
			model.renderPart("headlightL_rubber_trim");
			model.renderPart("headlightL_frame");
			model.renderPart("exhausta_chrome");
			model.renderPart("exhausta_black");						
			model.renderPart("bumperFa_plastic2");			
			model.renderPart("bumperFa_black");
			model.renderPart("body_rubber_trim");
			model.renderPart("body_reflector");
			model.renderPart("bumperRa_black");
			model.renderPart("body_zmk");
			model.renderPart("body_black");
			Minecraft.getMinecraft().renderEngine.bindTexture(sil15_lights);
			model.renderPart("winga_lights_gls_tail_light");
			model.renderPart("headlightR_oldhead");
			model.renderPart("headlightR_indicator_right");
			model.renderPart("headlightR_hidhead");
			model.renderPart("headlightR_head_light");
			model.renderPart("headlightR_fullbeam");
			model.renderPart("headlightL_oldhead");
			model.renderPart("headlightL_indicator_left");
			model.renderPart("headlightL_hidhead");
			model.renderPart("headlightL_head_light");
			model.renderPart("headlightL_fullbeam");
			model.renderPart("glassRTL_lights_gls_taillight2S");
			model.renderPart("glassRTL_lights_gls_reverse_light");
			model.renderPart("glassRTL_lights_gls_noemit");
			model.renderPart("glassRTL_lights_gls_indicator_right");
			model.renderPart("glassLTL_lights_gls_taillight2S");
			model.renderPart("glassLTL_lights_gls_reverse_light");
			model.renderPart("glassLTL_lights_gls_noemit");
			model.renderPart("glassLTL_lights_gls_indicator_left");
			model.renderPart("winga_tail_light");
			Minecraft.getMinecraft().renderEngine.bindTexture(car_chassis);
			model.renderPart("undercarriagea_undercarriage");
			model.renderPart("bumperFa_bottom");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("headlightR_black_glass");
			model.renderPart("headlightL_black_glass");
			model.renderPart("glassRR_black_glass");
			model.renderPart("glassRHL_black_glass");
			model.renderPart("glassRF_black_glass");
			model.renderPart("glassR_black_glass");
			model.renderPart("glassLR_black_glass");
			model.renderPart("glassLHL_black_glass");
			model.renderPart("glassLF_black_glass");
			model.renderPart("glassF_black_glass");
			model.renderPart("body_frame");
			model.renderPart("bumperFrameR_bumper_frame");
			model.renderPart("bumperFrameF_bumper_frame");
			model.renderPart("body_matte_colors");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassRR_window");
			model.renderPart("glassRF_window");
			model.renderPart("glassR_window");
			model.renderPart("glassLR_window");
			model.renderPart("glassLF_window");
			model.renderPart("glassF_window");
			GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.6F);
			model.renderPart("glassRHL_glass");
			model.renderPart("glassLHL_glass");
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(sil15_bdg);
			 	model.renderPart("trunk_badge");
			 	model.renderPart("hooda_badge");
				model.renderPart("body_badge");
			Minecraft.getMinecraft().renderEngine.bindTexture(sil15_lights);
			 	model.renderPart("headlightR_glass");
			 	model.renderPart("headlightL_glass");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
         
	 }
	 @Override
	 public void renderSteer(){
		model.renderPart("steering_wheel_interior");
	 }
}