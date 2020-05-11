//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33cadillac; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelcadilctsv extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation ctsv_ext = new ResourceLocation("minecraft:d33cadillac/textures/model/ctsv_ext.png");
	private ResourceLocation ctsv_lights = new ResourceLocation("minecraft:d33cadillac/textures/model/ctsv_lights.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33cadillac/textures/model/undercarriage.png");
	private ResourceLocation grill = new ResourceLocation("minecraft:d33cadillac/textures/model/grill.png");
	public Modelcadilctsv() //Same as Filename
	{	    	
		    steer = ctsv_ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33cadillac/textures/model/ctsv.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -36.796F;
	        steerY = 88.128F;
	        steerZ = -42.7F;
	        steerR = -18.0F;
	        
	        wheelX = 70F;
	        wheelX1 = 70F;
	        wheelY = 29F;
	        wheelZ = -155F;
	        wheelZ1 = 136F;
	        
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
		 //Minecraft.getMinecraft().renderEngine.bindTexture(bmw_m5_ext_alpine_white);
		 	model.renderPart("trunk_body");
		 	model.renderPart("mirrorR_body");
			model.renderPart("mirrorL_body");
			model.renderPart("hooda_body");
			model.renderPart("bumperRa_body");
			model.renderPart("bumperFa_body");
			model.renderPart("body_body_2");
			model.renderPart("body_body");
			model.renderPart("");
			model.renderPart("");
			//model.renderAll();
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.15, 1.15, 1.2);
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(ctsv_ext);
		 	model.renderPart("trunk_matte_colors");
		 	model.renderPart("trunk_frame");
			model.renderPart("trunk_badge");
			model.renderPart("seatR_interior");
			model.renderPart("seatL_interior");
			model.renderPart("mirrorR_black");
			model.renderPart("mirrorL_black");
			model.renderPart("interior_interior");
			model.renderPart("hooda_rubber_trim");
			model.renderPart("hooda_frame");
			model.renderPart("headlightR_plastic2");
		 	model.renderPart("headlightL_plastic2");
			model.renderPart("exhaustRa_matte_colors");
			model.renderPart("exhaustRa_black");
			model.renderPart("exhaustLa_matte_colors");
			model.renderPart("exhaustLa_black");
			model.renderPart("bumperRa_plastic2");
			model.renderPart("bumperRa_frame");
			model.renderPart("bumperRa_bottom");
			model.renderPart("bumperRa_black");
		 	model.renderPart("bumperRa_badge");
			model.renderPart("bumperFa_plastic2");
			model.renderPart("bumperFa_matte_colors");
			model.renderPart("bumperFa_frame");
			model.renderPart("bumperFa_bottom");
			model.renderPart("bumperFa_badge");
			model.renderPart("body_weatherstrip");
		 	model.renderPart("body_rubber_trim");
			model.renderPart("body_plastic2");
			model.renderPart("body_frame");
			model.renderPart("body_emblem");
			model.renderPart("body_black");
			model.renderPart("body_badge");
			Minecraft.getMinecraft().renderEngine.bindTexture(grill);
			model.renderPart("body_grille2");
			model.renderPart("bumperRa_grille1");
			model.renderPart("bumperFa_grille1");
			model.renderPart("bumperFa_badge2");
			Minecraft.getMinecraft().renderEngine.bindTexture(ctsv_lights);
			model.renderPart("trunk_textured_reflector");
		 	model.renderPart("trunk_tail_light");
			model.renderPart("taillightR_textured_reflector");
			model.renderPart("taillightR_tail_light");
			model.renderPart("taillightR_reverse_light");
			model.renderPart("taillightL_textured_reflector");
			model.renderPart("taillightL_tail_light");
			model.renderPart("taillightL_reverse_light");
			model.renderPart("headlightR_textured_reflector");
			model.renderPart("headlightR_head_light");
			model.renderPart("headlightL_textured_reflector");
			model.renderPart("headlightL_head_light");
			model.renderPart("bumperRa_textured_reflector");
			model.renderPart("bumperFa_textured_reflector");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriagea_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.05, 0.05, 0.05);
			model.renderPart("glassT_black_glass");
			model.renderPart("glassR_black_glass");
			model.renderPart("glassF_black_glass");
			model.renderPart("bumperFa_black");
			GL11.glColor3d(0.6, 0.6, 0.6);
			model.renderPart("trunk_chrome");
		 	model.renderPart("mirrorR_chrome");
			model.renderPart("mirrorL_chrome");
			model.renderPart("interior_chrome");
			model.renderPart("hooda_chrome");
			model.renderPart("exhaustRa_chrome");
			model.renderPart("exhaustLa_chrome");
			model.renderPart("bumperFa_chrome");
			model.renderPart("body_chrome");
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
			GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.6F);
			model.renderPart("glassLHL_glass");
			model.renderPart("glassRHL_glass");
			Minecraft.getMinecraft().renderEngine.bindTexture(ctsv_lights);
			GL11.glColor4f(1F, 1F, 1F, 1F);
			model.renderPart("glassRTL_detail_glass_red");
		 	model.renderPart("glassRTL_detail_glass_clear");
			model.renderPart("glassLTL_detail_glass_red");
			model.renderPart("glassLTL_detail_glass_clear");
			model.renderPart("bumperFa_detail_glass_clear");
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
	 /*@Override
	 public void renderSteer(){
		model.renderPart("steer");
	 }*/
}