//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33bugatti; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelchiron extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation chiron_cab = new ResourceLocation("minecraft:d33bugatti/textures/model/chiron_cab.png");
	private ResourceLocation chiron_ext = new ResourceLocation("minecraft:d33bugatti/textures/model/chiron_ext.png");
	/*private ResourceLocation r34_badges = new ResourceLocation("minecraft:d33nissan/textures/model/r34_badges2.png");
	private ResourceLocation r34_cab = new ResourceLocation("minecraft:d33nissan/textures/model/r34_cab.png");
	private ResourceLocation r34_ext = new ResourceLocation("minecraft:d33nissan/textures/model/r34_ext.png");
	private ResourceLocation r34_lights = new ResourceLocation("minecraft:d33nissan/textures/model/r34_lights.png");
	private ResourceLocation r34_misc = new ResourceLocation("minecraft:d33nissan/textures/model/r34_misc.png");
	private ResourceLocation carbon_black = new ResourceLocation("minecraft:d33nissan/textures/model/carbon_black.png");
	private ResourceLocation strel = new ResourceLocation("minecraft:d33nissan/textures/model/strel1.png");*/
	public Modelchiron() //Same as Filename
	{	    	
		    steer = chiron_cab; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33bugatti/textures/model/chiron.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -38.443F;
	        steerY = 73.386F;
	        steerZ = -52.988F;
	        steerR = -20F;
	        
	        wheelX = 69F;
	        wheelX1 = 70F;
	        wheelY = 31F;
	        wheelZ = -132F;
	        wheelZ1 = 137F;
	        
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(chiron_ext);
		 	model.renderPart("BODY_extc");
			model.renderPart("DOOR_LEFT_ext");
			model.renderPart("DOOR_RIGHT_ext");
			model.renderPart("MIRROR_LEFT_extc");
			model.renderPart("MIRROR_RIGHT_extc");
			model.renderPart("HOOD_ext");
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
		 GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(46.857F, 82.257F, -63.852F);
			 GL11.glRotatef(-10F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(10F, 1f, 0f, 0f);
			 GL11.glTranslatef(-46.857F, -82.257F, 63.852F);

			 Minecraft.getMinecraft().renderEngine.bindTexture(chiron_cab);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		 
		 
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(chiron_cab);
		 	model.renderPart("DOOR_LEFT_cab");
			model.renderPart("DOOR_RIGHT_cab");
			model.renderPart("INTERIOR_cab");
			Minecraft.getMinecraft().renderEngine.bindTexture(chiron_ext);
			model.renderPart("BODY_ext");
			model.renderPart("BOOT_ext");
			model.renderPart("CHASSIS_ext");
			model.renderPart("EXHAUST_ext");
			model.renderPart("FRONTBUMPER_ext");
			model.renderPart("MIRROR_GLASS_LEFT_ext");
			model.renderPart("MIRROR_GLASS_RIGHT_ext");
			model.renderPart("MIRROR_LEFT_ext");
			model.renderPart("MIRROR_RIGHT_ext");
			model.renderPart("REARBUMPER_ext");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("GLASS_FRONT_b");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("GLASS_FRONT_windows");
			model.renderPart("GLASS_LEFT_windows");
			model.renderPart("GLASS_REAR_windows");
			model.renderPart("GLASS_RIGHT_windows");
			GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.6F);
			model.renderPart("HEADLIGHT_LENS_LEFT");
			model.renderPart("HEADLIGHT_LENS_RIGHT");
			GL11.glColor4f(0.6F, 0.05F, 0.05F, 0.6F);
			model.renderPart("TAILLIGHT_LENS_LEFT");
			model.renderPart("TAILLIGHT_LENS_RIGHT");
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
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
		model.renderPart("steer");
	 }*/
}