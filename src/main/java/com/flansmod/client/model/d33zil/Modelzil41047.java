//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33zil; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelzil41047 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation dirt = new ResourceLocation("minecraft:d33zil/textures/model/dirt.png");
	private ResourceLocation engine = new ResourceLocation("minecraft:d33zil/textures/model/engine.png");
	private ResourceLocation obivka = new ResourceLocation("minecraft:d33zil/textures/model/obivka.png");
	private ResourceLocation panel1 = new ResourceLocation("minecraft:d33zil/textures/model/panel1.png");
	private ResourceLocation panel2 = new ResourceLocation("minecraft:d33zil/textures/model/panel2.png");
	private ResourceLocation peno = new ResourceLocation("minecraft:d33zil/textures/model/leather_black.png");
	private ResourceLocation  rezin = new ResourceLocation("minecraft:d33zil/textures/model/obivka2.png");
	private ResourceLocation wood1 = new ResourceLocation("minecraft:d33zil/textures/model/wood1.png");
	private ResourceLocation vehiclelights128 = new ResourceLocation("minecraft:d33zil/textures/model/vehiclelights128.png");
	/*private ResourceLocation logo = new ResourceLocation("minecraft:d33zil/textures/model/logo.png");
	private ResourceLocation radiator = new ResourceLocation("minecraft:d33zil/textures/model/radiator.png");
	private ResourceLocation rearlight = new ResourceLocation("minecraft:d33zil/textures/model/rearlight.png");
	private ResourceLocation steelwheelr = new ResourceLocation("minecraft:d33zil/textures/model/steelwheelr.png");
	private ResourceLocation underbody = new ResourceLocation("minecraft:d33zil/textures/model/underbody.png");
	private ResourceLocation wood = new ResourceLocation("minecraft:d33zil/textures/model/wood.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33zil/textures/model/vehiclelights.png");*/
	public Modelzil41047() //Same as Filename
	{	    	
		    steer = peno; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33zil/textures/model/zil41047.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -40.088F;
	        steerY = 87.898F;
	        steerZ = -71.423F;
	        steerR = -29.108F;
	        
	        wheelX = 67F;
	        wheelX1 = 67F;
	        wheelY = 31F;
	        wheelZ = -178F;
	        wheelZ1 = 96F;
	        
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
		 wheelX = 75F;
	        wheelX1 = 75F;
	        wheelY = 31F;
	        wheelZ = -198F;
	        wheelZ1 = 140F;
		 //Minecraft.getMinecraft().renderEngine.bindTexture(chiron_ext);
	        model.renderPart("col");
			model.renderPart("wing_rf_col");
			model.renderPart("wing_lf_col");
			model.renderPart("bonnet_col");
			model.renderPart("boot_col");
			model.renderPart("door_rf_col");
			model.renderPart("door_rr_col");
			model.renderPart("door_lf_col");
			model.renderPart("door_lr_col");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.1, 1.1, 1.1);
	 }
	 
	 @Override
	 public void pre(){
		 //GL11.glTranslatef(0.0F, 0.0F, 0.0F);	
		 //GL11.glShadeModel(GL11.GL_SMOOTH);
	 }
	 
	 @Override
	 public void renderSpedo(){
		/* GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(46.857F, 82.257F, -63.852F);
			 GL11.glRotatef(-10F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(10F, 1f, 0f, 0f);
			 GL11.glTranslatef(-46.857F, -82.257F, 63.852F);

			 Minecraft.getMinecraft().renderEngine.bindTexture(lincont);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();*/
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		 
		 
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights128);
		 model.renderPart("farz");
			model.renderPart("stop");
			model.renderPart("fl");
			model.renderPart("fr");
			model.renderPart("wing_rf_vhl");
			model.renderPart("wing_lf_vhl");
			model.renderPart("bump_front_tum");
			Minecraft.getMinecraft().renderEngine.bindTexture(peno);
			model.renderPart("panel");
			 Minecraft.getMinecraft().renderEngine.bindTexture(panel2);
			model.renderPart("pan2");
			 Minecraft.getMinecraft().renderEngine.bindTexture(panel1);
				model.renderPart("pan1");
			Minecraft.getMinecraft().renderEngine.bindTexture(wood1);
			model.renderPart("derev");
			model.renderPart("door_rf_dr");
			model.renderPart("door_rr_dr");
			 model.renderPart("door_lf_dr");
				model.renderPart("door_lr_dr");
				Minecraft.getMinecraft().renderEngine.bindTexture(engine);
				model.renderPart("rad");
				Minecraft.getMinecraft().renderEngine.bindTexture(dirt);
				model.renderPart("dno");
				Minecraft.getMinecraft().renderEngine.bindTexture(rezin);
				GL11.glColor3d(53/256F, 47/256F, 51/256F);
				model.renderPart("pol");
				GL11.glColor3d(203/256F, 181/256F, 144/256F);
				Minecraft.getMinecraft().renderEngine.bindTexture(obivka);
				model.renderPart("sed");
				model.renderPart("door_rf_ob");
				model.renderPart("door_rr_ob");
				model.renderPart("door_lr_ob");
				model.renderPart("door_lf_ob");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			 model.renderPart("bla");
				model.renderPart("wing_rf_bl");
				model.renderPart("wing_lf_bl");
				model.renderPart("bump_front_bl");
				model.renderPart("bump_rear_bl");
				model.renderPart("door_rf_bl");
				model.renderPart("door_rr_bl");
				model.renderPart("door_lr_bl");
				model.renderPart("door_lf_bl");
			GL11.glColor3f(0.6F, 0.6F, 0.6F);
			 model.renderPart("cr");
				model.renderPart("cro");
				model.renderPart("wing_rf_cr");
				model.renderPart("wing_lf_cr");
				model.renderPart("bump_front_cr");
				model.renderPart("bump_rear_cr");
				model.renderPart("bonnet_cr");
				model.renderPart("boot_cr");
				model.renderPart("door_rf_cr");
				model.renderPart("door_rr_cr");
				model.renderPart("door_lf_cr");
				model.renderPart("door_lr_cr");
				model.renderPart("door_rf_cro");
				model.renderPart("door_rr_cro");
				model.renderPart("door_lf_cro");
				model.renderPart("door_lr_cro");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("gl");
			model.renderPart("door_rf_gl");
			model.renderPart("door_rr_gl");
			model.renderPart("door_lf_gl");
			model.renderPart("door_lr_gl");
			model.renderPart("windscreen");
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
	 @Override
	 public void renderSteer(){
		model.renderPart("str_panel");
		Minecraft.getMinecraft().renderEngine.bindTexture(color);
		GL11.glColor3d(0.1, 0.1, 0.1);
		model.renderPart("str_bla");
		GL11.glColor3d(0.6, 0.6, 0.6);
		model.renderPart("str_cro");
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
}