//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33tesla; //Path where the model is located

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;
import com.flansmod.client.tmt.ModelRendererTurbo;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.EntitySeat;
import com.flansmod.common.driveables.EntityVehicle;
import com.flansmod.common.driveables.EnumDriveablePart;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelteslax extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation teslax = new ResourceLocation("minecraft:d33tesla/textures/model/teslax.png");
	private ResourceLocation teslab = new ResourceLocation("minecraft:d33tesla/textures/model/teslab.png");
	/*private ResourceLocation led = new ResourceLocation("minecraft:d33tesla/textures/model/led.png");
	private ResourceLocation vehicle_generic_smallspecmap = new ResourceLocation("minecraft:d33tesla/textures/model/vehicle_generic_smallspecmap.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33tesla/textures/model/vehiclelights.png");*/
	public Modelteslax() //Same as Filename
	{	    	
		    steer = teslax; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33tesla/textures/model/teslax.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
		    //model = (MQO_MetasequoiaObject) model;
		    model = new ModelWrapperDisplayList((WavefrontObject) model);
	        maxSpedoAngle = 145F;
	        
	        steerX = -46.021F;
	        steerY = 93.543F;
	        steerZ = -47.298F;
	        steerR = -13.127F;
	        ismqo=true;
	        
	        wheelX = 62F;
	        wheelX1 = 62F;
	        wheelY = 26F;
	        wheelZ = -175F;
	        wheelZ1 = 142F;
	        
	        GL11.glTranslatef(0F, 100F, 0F);
	        
	}
	 @Override
	 public void renderColoredParts(){
	        
		 	wheelX = 74F;
	        wheelX1 = 74F;
	        wheelY = 30F;
	        wheelZ = -143F;
	        wheelZ1 = 152F;
	        Minecraft.getMinecraft().renderEngine.bindTexture(teslax);
			//model.renderPart("LED");
			model.renderPart("color");
	 }
	 /*@Override
	 public void renderSpedo(){
		 GL11.glTranslatef(-33.7F, 105.4F, -103.3F);
		 GL11.glRotatef(-24F, 0f, 0f, 1f);
		 GL11.glRotatef(-14F, 1f, 0f, 0f);
	 }*/
	
	 /*@Override
	 public void renderSteer(){
		 //GL11.glScaled(100, 100, 100);
	        model.renderPart("steer");
	 }*/
	 
	 @Override
	 public void pre(){
		 GL11.glTranslatef(0, 4F, 0);
		 GL11.glScaled(0.9, 0.9, 0.9);
	 }
	 
	 @Override
	 public void renderWheels(){	
		//GL11.glTranslatef(0F, 0.09F, 0F);
		GL11.glScalef(1.28F, 1.28F, 1.45F);
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		
		 //GL11.glScaled(100, 100, 100);
		 //GL11.glTranslatef(0F, 0.09F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(teslax);
		//model.renderPart("LED");
		model.renderPart("body");
		model.renderPart("seat");

			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
		    model.renderPart("glass");
			Minecraft.getMinecraft().renderEngine.bindTexture(teslab);
			GL11.glColor3f(1F, 1F, 1F);
			model.renderPart("badge");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 //@Override
	 
}