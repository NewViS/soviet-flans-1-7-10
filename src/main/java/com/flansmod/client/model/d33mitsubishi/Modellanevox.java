//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33mitsubishi; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modellanevox extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation grill = new ResourceLocation("minecraft:d33mitsubishi/textures/model/grill.png");
	private ResourceLocation lan_ext = new ResourceLocation("minecraft:d33mitsubishi/textures/model/lan_ext.png");
	private ResourceLocation lan_lights = new ResourceLocation("minecraft:d33mitsubishi/textures/model/lan_lights.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33mitsubishi/textures/model/undercarriage.png");
	public Modellanevox() //Same as Filename
	{	    	
		    steer = lan_ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33mitsubishi/textures/model/lanevo10.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -33.03F;
	        steerY = 68.673F;
	        steerZ = -37.413F;
	        steerR = -29.697F;
	        
	        wheelX = 60F;
	        wheelX1 = 60F;
	        wheelY = 27F;
	        wheelZ = -138F;
	        wheelZ1 = 121F;
	        
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
		 wheelX = 58F;
	        wheelX1 = 58F;
	        wheelY = 26F;
	        wheelZ = -113F;
	        wheelZ1 = 118F;
		 //translateAll(0F, 20F, 0F);
		 //Minecraft.getMinecraft().renderEngine.bindTexture(body);
		 model.renderPart("winga_body_2");
		 	model.renderPart("trunk_body");
			model.renderPart("skirtRa_body");
			model.renderPart("skirtLa_body");
			model.renderPart("mirrorR_body");
			model.renderPart("mirrorL_body");
			model.renderPart("hooda_body");
		 	model.renderPart("bumperRa_body");
		 	model.renderPart("bumperFa_body");
		 	model.renderPart("body_body_2");
		 	model.renderPart("body_body");
		 	model.renderPart("");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.0, 1.0, 1.2);
	 }
	 
	 @Override
	 public void pre(){
		 //GL11.glTranslatef(0.0F, 0.0F, 0.0F);	
		 //GL11.glShadeModel(GL11.GL_SMOOTH);
	 }
	 
	 @Override
	 public void renderSpedo(){
		 /*GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(-45.803F, 91.467F, -67.636F);
			 GL11.glRotatef(-19.553F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(19.553F, 1f, 0f, 0f);
			 GL11.glTranslatef(45.803F, -91.467F, 67.636F);

			 Minecraft.getMinecraft().renderEngine.bindTexture(spedom5);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();*/
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(lan_ext);
		 	model.renderPart("winga_plastic2");
		 	model.renderPart("winga_blackwinga_black");
			model.renderPart("trunk_black");
			model.renderPart("trunk_badge");
			model.renderPart("taillightR_black");
			model.renderPart("taillightL_black");
			model.renderPart("skirtRa_black");
		 	model.renderPart("skirtLa_black");
		 	model.renderPart("seatR_interior");
			model.renderPart("seatL_interior");
			model.renderPart("mirrorR_plastic2");
			model.renderPart("mirrorR_chrome");
			model.renderPart("mirrorR_black");
			model.renderPart("mirrorL_plastic2");
			model.renderPart("mirrorL_chrome");
		 	model.renderPart("mirrorL_black");
			model.renderPart("interior_interior");
			model.renderPart("interior_chrome");
			model.renderPart("hooda_plastic2");
			model.renderPart("hooda_black");
			model.renderPart("headlightR_plastic2");
			model.renderPart("headlightR_chrome");
		 	model.renderPart("headlightR_black");
		 	model.renderPart("headlightL_plastic2");
			model.renderPart("headlightL_chrome");
		 	model.renderPart("headlightL_black");
		 	model.renderPart("exhausta_misc");
			model.renderPart("exhausta_chrome");
			model.renderPart("exhausta_black");
		 	model.renderPart("bumperRa_plastic2");
		 	model.renderPart("bumperRa_black");
			model.renderPart("bumperFa_plastic2");
			model.renderPart("bumperFa_misc");
		 	model.renderPart("bumperFa_black");
		 	model.renderPart("bumperFa_badge");
			model.renderPart("body_rubber_trim");
			model.renderPart("body_plastic2");
		 	model.renderPart("body_matte_colors");
		 	model.renderPart("body_frame");
			model.renderPart("body_chrome");
			model.renderPart("body_black");
		 	model.renderPart("body_badge");
			Minecraft.getMinecraft().renderEngine.bindTexture(grill);
			model.renderPart("bumperFa_grille1");
			model.renderPart("body_grille1");
			Minecraft.getMinecraft().renderEngine.bindTexture(lan_lights);
			model.renderPart("trunk_tail_light");
		 	model.renderPart("taillightR_tail_light");
			model.renderPart("taillightR_reverse_light");
			model.renderPart("taillightR_reflector");
			model.renderPart("taillightR_detail_glass_clear");
			model.renderPart("taillightL_tail_light");
			model.renderPart("taillightL_reverse_light");
			model.renderPart("taillightL_reflector");
			model.renderPart("taillightL_detail_glass_clear");
			model.renderPart("headlightR_textured_reflector");
			model.renderPart("headlightR_reflector");
		 	model.renderPart("headlightR_head_light");
		 	model.renderPart("headlightR_detail_glass_clear");
		 	model.renderPart("headlightL_textured_reflector");
			model.renderPart("headlightL_reflector");
		 	model.renderPart("headlightL_head_light");
		 	model.renderPart("headlightL_detail_glass_clear");
		 	model.renderPart("bumperRa_textured_reflector");
		 	model.renderPart("body_reflector");
		 	model.renderPart("body_detail_glass_clear");
		 	model.renderPart("");
		 	model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriagea_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("body_black_glass");
		 	model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
		 	model.renderPart("");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassRR_window");
		 	model.renderPart("glassRM_window");
			model.renderPart("glassRF_window");
			model.renderPart("glassR_window");
			model.renderPart("glassLR_window");
			model.renderPart("glassLM_window");
			model.renderPart("glassLF_window");
			model.renderPart("glassF_window");
			model.renderPart("");
			GL11.glColor4f(0.6F, 0.6F, 0.7F, 0.6F);
			model.renderPart("glassLHL_glass");
			model.renderPart("glassRHL_glass");
			GL11.glColor4f(0.8F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassLTL_glass");
		 	model.renderPart("glassRTL_glass");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
         
	 }
	 /*@Override
	 public void frontWheelsStuff(){
		 Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.3F, 0.3F, 0.3F);
			GL11.glTranslatef(-10F, 0, 0);
			model.renderPart("brakedisc_");
			GL11.glTranslatef(10F, 0, 0);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 */
	 @Override
	 public void renderSteer(){
		model.renderPart("steering_wheel_interior");
	 }
}