//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33chevrolet; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelimpalass extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33chevrolet/textures/model/undercarriage.png");
	private ResourceLocation impala_ext = new ResourceLocation("minecraft:d33chevrolet/textures/model/impala_ext.png");
	private ResourceLocation impala_lights = new ResourceLocation("minecraft:d33chevrolet/textures/model/impala_lights.png");
	public Modelimpalass() //Same as Filename
	{	    	
		    steer = impala_ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33chevrolet/textures/model/impalass.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -40.551F;
	        steerY = 72.414F;
	        steerZ = -43.352F;
	        steerR = -24.581F;
	        
	        wheelX = 68F;
	        wheelX1 = 68F;
	        wheelY = 29F;
	        wheelZ = -138F;
	        wheelZ1 = 123F;
	        
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
		 wheelX = 71F;
	        wheelX1 = 71F;
	        wheelY = 29F;
	        wheelZ = -143F;
	        wheelZ1 = 150F;
	        
		 	model.renderPart("winga_body_2");
			model.renderPart("trunk_body_2");
			model.renderPart("trunk_body");
			model.renderPart("mirrorR_body");
			model.renderPart("mirrorL_body");
			model.renderPart("hooda_body_2");
			model.renderPart("hooda_body");
			model.renderPart("bumperRa_body");
			model.renderPart("bumperFa_body");
			model.renderPart("body_body");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.1, 1.1, 1.4);
	 }
	 
	 @Override
	 public void pre(){
		 //GL11.glTranslatef(0.0F, 0.0F, 0.0F);	
		 //GL11.glShadeModel(GL11.GL_SMOOTH);
	 }
	 
	 /*@Override
	 public void renderSpedo(){
		 GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(46.857F, 82.257F, -63.852F);
			 GL11.glRotatef(-10F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(10F, 1f, 0f, 0f);
			 GL11.glTranslatef(-46.857F, -82.257F, 63.852F);

			 Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();
	 }*/
	 
	 @Override
	 public void renderTexturedParts(){
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(impala_ext);
		 	model.renderPart("winga_black");
		 	model.renderPart("trunk_frame");
			model.renderPart("trunk_emblem");
			model.renderPart("trunk_badge");
			model.renderPart("taillightR_plastic2");
		 	model.renderPart("taillightR_black");
			model.renderPart("taillightL_plastic2");
			model.renderPart("taillightL_black");
			model.renderPart("seatR_interior");
			model.renderPart("seatL_interior");
			model.renderPart("mirrorR_chrome");
		 	model.renderPart("mirrorR_black");
			model.renderPart("mirrorL_chrome");
			model.renderPart("mirrorL_black");
			model.renderPart("interior_interior");
		 	model.renderPart("interior_chrome");
			model.renderPart("hooda_rubber_trim");
			model.renderPart("hooda_misc");
			model.renderPart("hooda_emblem");
			model.renderPart("hooda_black");
			model.renderPart("headlightR_rubber_trim");
		 	model.renderPart("headlightR_frame");
			model.renderPart("headlightR_chrome");
			model.renderPart("headlightL_rubber_trim");
			model.renderPart("headlightL_frame");
		 	model.renderPart("headlightL_chrome");
			model.renderPart("exhaustRa_misc");
			model.renderPart("exhaustRa_black");
			model.renderPart("exhaustLa_misc");
			model.renderPart("exhaustLa_black");
			model.renderPart("bumperRa_frame");
			model.renderPart("bumperFrameR_bumper_frame");
		 	model.renderPart("bumperFrameF_bumper_frame");
			model.renderPart("bumperFa_plastic2");
			model.renderPart("bumperFa_frame");
			model.renderPart("bumperFa_black");
			model.renderPart("body_weatherstrip");
			model.renderPart("body_rubber_trim");
			model.renderPart("body_plastic2");
			model.renderPart("body_misc");
			model.renderPart("body_frame");
			model.renderPart("body_chrome");
		 	model.renderPart("body_black");
			model.renderPart("body_badge");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(impala_lights);
			model.renderPart("trunk_textured_reflector");
		 	model.renderPart("taillightR_textured_reflector");
			model.renderPart("taillightR_tail_light");
			model.renderPart("taillightR_reverse_light");
			model.renderPart("taillightL_textured_reflector");
			model.renderPart("taillightL_tail_light");
			model.renderPart("taillightL_reverse_light");
			model.renderPart("interior_tail_light");
		 	model.renderPart("headlightR_reflector");
			model.renderPart("headlightR_head_light");
			model.renderPart("headlightL_reflector");
			model.renderPart("headlightL_head_light");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
		 	model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriagea_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.9, 0.9, 0.9);
			model.renderPart("");
			model.renderPart("");
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("body_black_glass");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
		 	model.renderPart("");
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
			Minecraft.getMinecraft().renderEngine.bindTexture(impala_lights);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			model.renderPart("glassRTL_lights_glass");
			model.renderPart("glassRHL_lights_glass");
			model.renderPart("glassRHL_detail_glass_amber");
			model.renderPart("glassLTL_lights_glass");
			model.renderPart("glassLHL_lights_glass");
			model.renderPart("glassLHL_detail_glass_amber");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
         
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
	/* @Override
	 public void renderSteer(){
			Minecraft.getMinecraft().renderEngine.bindTexture(camaro_exterior);
		model.renderPart("steer_ext");
		Minecraft.getMinecraft().renderEngine.bindTexture(w220_leather1);
		model.renderPart("steer_int");
	 }*/
}