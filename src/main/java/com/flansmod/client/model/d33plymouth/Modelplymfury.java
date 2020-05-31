//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33plymouth; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelplymfury extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation christine_gauges_512 = new ResourceLocation("minecraft:d33plymouth/textures/model/christine_gauges_512.png");
	private ResourceLocation christine_metal128 = new ResourceLocation("minecraft:d33plymouth/textures/model/christine_metal128.png");
	private ResourceLocation DMD_log_256 = new ResourceLocation("minecraft:d33plymouth/textures/model/DMD_log_256.png");
	private ResourceLocation oceanic_badge512 = new ResourceLocation("minecraft:d33plymouth/textures/model/oceanic_badge512.png");
	private ResourceLocation oceanic58interior128 = new ResourceLocation("minecraft:d33plymouth/textures/model/oceanic58interior128.png");
	private ResourceLocation vehiclegeneric = new ResourceLocation("minecraft:d33plymouth/textures/model/vehiclegeneric.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33plymouth/textures/model/vehiclelights.png");
	public Modelplymfury() //Same as Filename
	{	    	
		    steer = christine_gauges_512; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33plymouth/textures/model/fury.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -37.126F;
	        steerY = 69.517F;
	        steerZ = -45.79F;
	        steerR = -22.477F;
	        
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
		 wheelX = 61F;
	        wheelX1 = 61F;
	        wheelY = 27F;
	        wheelZ = -142F;
	        wheelZ1 = 123F;
		 //translateAll(0F, 20F, 0F);
		 //Minecraft.getMinecraft().renderEngine.bindTexture(body);
		 model.renderPart("door_rf_col");
		 	model.renderPart("door_lf_col");
			model.renderPart("bonnet_col");
			model.renderPart("boot_col");
			model.renderPart("colin");
		 	model.renderPart("color");
			model.renderPart("");
			model.renderPart("");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.0, 1.0, 1.0);
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(oceanic58interior128);
		GL11.glColor3d(219/255d, 30/255d, 39/255d);
		 model.renderPart("door_rf_in");
		 	model.renderPart("door_lf_in");
			model.renderPart("inter2");
			GL11.glColor3d(236/255d, 228/255d, 217/255d);
			model.renderPart("inter");
			GL11.glColor3d(1, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(vehiclegeneric);
			 model.renderPart("misc_b");
			 	model.renderPart("misc_a");
				model.renderPart("dragon_V8");
				model.renderPart("gener");
				model.renderPart("dno");
			Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
				 model.renderPart("front_ligh");
				 	model.renderPart("farz");
					model.renderPart("farzl");
			Minecraft.getMinecraft().renderEngine.bindTexture(oceanic_badge512);
					 model.renderPart("bonnet_log");
					 	model.renderPart("oceanic");
						model.renderPart("");
						Minecraft.getMinecraft().renderEngine.bindTexture(christine_metal128);
						model.renderPart("metal");
						Minecraft.getMinecraft().renderEngine.bindTexture(christine_gauges_512);
						model.renderPart("prib");
					 	model.renderPart("");
						model.renderPart("");
						model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			 model.renderPart("cr");
			 	model.renderPart("exhaust_cr");
				model.renderPart("exhaustpip");
				model.renderPart("bump_rea01");
				model.renderPart("bump_fro01");
			 	model.renderPart("door_rf_cr");
				model.renderPart("door_rf_ruk");
				model.renderPart("door_lf_cr");
			 	model.renderPart("door_lf_ruk");
			 	model.renderPart("bonnet_cr");
				model.renderPart("boot_cr");
			 	model.renderPart("zer");
			 	model.renderPart("chrome");
				model.renderPart("crin");
			 	model.renderPart("");
			 	GL11.glColor3d(0.9, 0.9, 0.9);
			 	model.renderPart("");
				model.renderPart("col2");
			 	model.renderPart("door_lf_wh");
				model.renderPart("door_rf_wh");
				GL11.glColor3d(0.1, 0.1, 0.1);
				model.renderPart("");
				model.renderPart("");
				model.renderPart("");
				model.renderPart("");
			 	model.renderPart("");
				model.renderPart("");
				model.renderPart("exhaust_bl");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			 model.renderPart("glass");
			 	model.renderPart("windscre01");
				model.renderPart("glass_rf_o");
				model.renderPart("glass_lf_o");
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
		model.renderPart("steer_met");
		Minecraft.getMinecraft().renderEngine.bindTexture(oceanic58interior128);
		GL11.glColor3d(219/255d, 30/255d, 39/255d);
		model.renderPart("steer_inter");
		GL11.glColor3d(236/255d, 228/255d, 217/255d);
		model.renderPart("steer_inter2");
		 Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			 model.renderPart("steer_crin");
			 GL11.glColor3d(1, 1, 1);
	 }
}