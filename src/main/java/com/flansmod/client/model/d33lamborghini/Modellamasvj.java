//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33lamborghini; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modellamasvj extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation svj_badges = new ResourceLocation("minecraft:d33lamborghini/textures/model/svj_badges.png");
	private ResourceLocation svj_cab = new ResourceLocation("minecraft:d33lamborghini/textures/model/svj_cab.png");
	private ResourceLocation svj_ext = new ResourceLocation("minecraft:d33lamborghini/textures/model/svj_ext.png");
	//private ResourceLocation grill = new ResourceLocation("minecraft:d33lamborghini/textures/model/grill.png");
	public Modellamasvj() //Same as Filename
	{	    	
		    steer = svj_cab; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33lamborghini/textures/model/aventador_svj.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -38.266F;
	        steerY = 72.514F;
	        steerZ = -47.337F;
	        steerR = -18.564F;
	        
	        wheelX = 69F;
	        wheelX1 = 74F;
	        wheelY = 31F;
	        wheelZ = -135F;
	        wheelZ1 = 140F;
	        
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
	        model.renderPart("LOD_C_BODY_mm_ext");
			model.renderPart("LOD_C_BOOT_mm_ext");
			model.renderPart("LOD_C_DOOR_LEFT_mm_ext");
			model.renderPart("LOD_C_DOOR_RIGHT_mm_ext");
			model.renderPart("LOD_C_FRONTBUMPER_mm_ext");
		 	model.renderPart("LOD_C_HOOD_mm_ext");
			model.renderPart("LOD_C_REARBUMPER_mm_ext");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.2, 1.2, 1.6);
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(svj_ext);
		 	//model.renderPart("LOD_C_BODY_mm_svj");
		 	model.renderPart("LOD_C_BODY_mm_extt");
			model.renderPart("LOD_C_BOOT_mm_extt");
			/*model.renderPart("LOD_C_BRAKES_LEFT_mm_ext");
			model.renderPart("LOD_C_BRAKES_REARBUMPER_mm_ext");
			model.renderPart("LOD_C_BRAKES_RIGHT_mm_ext");*/
		 	model.renderPart("LOD_C_CHASSIS_mm_ext");
			model.renderPart("LOD_C_DOOR_LEFT_mm_extt");
			model.renderPart("LOD_C_DOOR_RIGHT_mm_extt");
			model.renderPart("LOD_C_EXHAUST_mm_ext");
			model.renderPart("LOD_C_FRONTBUMPER_mm_extt");
		 	model.renderPart("LOD_C_HOOD_mm_extt");
			model.renderPart("LOD_C_MIRROR_GLASS_LEFT_mm_ext");
			model.renderPart("LOD_C_MIRROR_GLASS_RIGHT_mm_ext");
			model.renderPart("LOD_C_MIRROR_LEFT_mm_ext");
			model.renderPart("LOD_C_MIRROR_RIGHT_mm_ext");
		 	model.renderPart("LOD_C_REARBUMPER_mm_extt");
			Minecraft.getMinecraft().renderEngine.bindTexture(svj_cab);
			model.renderPart("LOD_C_DOOR_LEFT_mm_cab");
		 	model.renderPart("LOD_C_DOOR_RIGHT_mm_cab");
			model.renderPart("LOD_C_INTERIOR_mm_cab");
			Minecraft.getMinecraft().renderEngine.bindTexture(svj_badges);
			model.renderPart("LOD_C_FRONTBUMPER_mm_badges");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.05, 0.05, 0.05);
			model.renderPart("LOD_C_GLASS_FRONT_mm_windowsb");
		 	model.renderPart("LOD_C_GLASS_LEFT_QUARTER_mm_windowsb");
			model.renderPart("LOD_C_GLASS_REAR_mm_windowsb");
			model.renderPart("LOD_C_GLASS_RIGHT_QUARTER_mm_windowsb");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("LOD_C_GLASS_FRONT_mm_windows");
		 	model.renderPart("LOD_C_GLASS_LEFT_QUARTER_mm_windows");
			model.renderPart("LOD_C_GLASS_LEFT_mm_windows");
			model.renderPart("LOD_C_GLASS_REAR_mm_windows");
			model.renderPart("LOD_C_GLASS_RIGHT_QUARTER_mm_windows");
			model.renderPart("LOD_C_GLASS_RIGHT_mm_windows");
			GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.6F);
			model.renderPart("LOD_C_HEADLIGHT_LENS_RIGHT_mm_windows");
			model.renderPart("LOD_C_HEADLIGHT_LENS_LEFT_mm_windows");
			GL11.glColor4f(0.8F, 0.05F, 0.05F, 0.6F);
			model.renderPart("LOD_C_TAILLIGHT_LENS_RIGHT_mm_windows");
			model.renderPart("LOD_C_TAILLIGHT_LENS_LEFT_mm_windows");
			GL11.glColor4f(1F, 1F, 1F, 1F);
			Minecraft.getMinecraft().renderEngine.bindTexture(svj_ext);
			model.renderPart("LOD_C_BODY_mm_svj");
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
		model.renderPart("LOD_C_STEERING_WHEEL_mm_cab");
	 }
}