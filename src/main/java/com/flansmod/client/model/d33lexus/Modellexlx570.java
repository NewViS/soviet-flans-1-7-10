//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33lexus; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modellexlx570 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation inter = new ResourceLocation("minecraft:d33lexus/textures/model/inter.png");
	private ResourceLocation engine = new ResourceLocation("minecraft:d33lexus/textures/model/engine.png");
	private ResourceLocation farzad570 = new ResourceLocation("minecraft:d33lexus/textures/model/farzad570.png");
	private ResourceLocation leather_black = new ResourceLocation("minecraft:d33lexus/textures/model/leather_black.png");
	private ResourceLocation vehicle_generic_detail2 = new ResourceLocation("minecraft:d33lexus/textures/model/vehicle_generic_detail2.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33lexus/textures/model/vehiclelights.png");
	private ResourceLocation velur = new ResourceLocation("minecraft:d33lexus/textures/model/velur.png");
	private ResourceLocation denx_kovrolin = new ResourceLocation("minecraft:d33lexus/textures/model/denx_kovrolin.png");
	private ResourceLocation wood = new ResourceLocation("minecraft:d33lexus/textures/model/wood.png");
	private ResourceLocation denx_tkan = new ResourceLocation("minecraft:d33lexus/textures/model/denx_tkan.png");
	public Modellexlx570() //Same as Filename
	{	    	
		    steer = wood; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33lexus/textures/model/lexuslx570.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -32.958F;
	        steerY = 80.678F;
	        steerZ = -66.284F;
	        steerR = -14.656F;
	        
	        wheelX = 60F;
	        wheelX1 = 60F;
	        wheelY = 29F;
	        wheelZ = -132F;
	        wheelZ1 = 95F;
	        
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
		 //translateAll(0F, 20F, 0F);
		 //Minecraft.getMinecraft().renderEngine.bindTexture(body);
		 	model.renderPart("col");
		 	model.renderPart("bump_rear_col");
			model.renderPart("bonnet");
			model.renderPart("boot_col");
			model.renderPart("door_lr_col");
			model.renderPart("chassis_col");
			model.renderPart("chassis_color");
			model.renderPart("door_rf_col");
			model.renderPart("door_lf_col");
			model.renderPart("bump_front_col");
			model.renderPart("door_rr_col");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.15, 1.15, 1.2);
	 }
	 
	 @Override
	 public void pre(){
		 GL11.glScaled(1.05, 1.1, 1.1);
		 GL11.glTranslated(0, 1, 0);
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
		 
		 Minecraft.getMinecraft().renderEngine.bindTexture(inter);
		 model.renderPart("inter");
		 model.renderPart("ekr");
		 Minecraft.getMinecraft().renderEngine.bindTexture(velur);
		 	model.renderPart("pansvetl");
			 Minecraft.getMinecraft().renderEngine.bindTexture(denx_tkan);
		 	model.renderPart("potol");
			model.renderPart("door_lr_wh");
			model.renderPart("door_rf_wh");
			model.renderPart("door_lf_svt");
		 	model.renderPart("door_rr_wh");
			model.renderPart("chassis_ob");
			Minecraft.getMinecraft().renderEngine.bindTexture(denx_kovrolin);
			model.renderPart("pol");
			 Minecraft.getMinecraft().renderEngine.bindTexture(wood);
			model.renderPart("wood");
		 	model.renderPart("door_lr_dr");
			model.renderPart("door_lf_wood");
			model.renderPart("door_rr_dr");
			model.renderPart("door_rf_dr");
			Minecraft.getMinecraft().renderEngine.bindTexture(leather_black);
		 	model.renderPart("panlea");
			model.renderPart("door_rf_obiv");
			model.renderPart("chassis_obund");
			model.renderPart("door_lr_ob");
			model.renderPart("sed");
			model.renderPart("chassis_in");
			model.renderPart("door_rr_ob");
			model.renderPart("door_lf_obiv");
			model.renderPart("bard");
			Minecraft.getMinecraft().renderEngine.bindTexture(engine);
			model.renderPart("engine");
			Minecraft.getMinecraft().renderEngine.bindTexture(farzad570);
			model.renderPart("glass");
		 	model.renderPart("glass3");
		 	model.renderPart("glass2");
		 	Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			model.renderPart("pov");
			Minecraft.getMinecraft().renderEngine.bindTexture(vehicle_generic_detail2);
			model.renderPart("underbod");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			model.renderPart("door_lr_cr");
		 	model.renderPart("door_rf_cr");
			model.renderPart("chrome2");
			model.renderPart("door_rr_cr");
			model.renderPart("door_rf_zer");
		 	model.renderPart("cr");
			model.renderPart("chrome_tum");
			model.renderPart("door_lf_cruk");
			model.renderPart("rog");
			model.renderPart("bump_front_logo");
		 	model.renderPart("door_rf_crk");
		 	model.renderPart("chassis_crome");
			model.renderPart("boot_logo");
			model.renderPart("glush");
		 	model.renderPart("boot_cr");
		 	model.renderPart("door_lf_zer");
			model.renderPart("chrome");
			model.renderPart("bump_front_cr");
		 	model.renderPart("door_lf_cr");
		 	model.renderPart("bump_rear_cr");
			GL11.glColor3d(0.3, 0.3, 0.3);
			model.renderPart("plastic");
			model.renderPart("door_rf_knop");
			model.renderPart("door_rr_knop");
			model.renderPart("door_rr_ruki");
			model.renderPart("door_rr_ruk");
		 	model.renderPart("door_lr_ruk");
		 	model.renderPart("door_lf_pla");
			model.renderPart("door_lf_met");
			model.renderPart("panpla");
		 	model.renderPart("door_lr_knop");
		 	model.renderPart("door_rf_ruk");
			model.renderPart("door_lf_gr");
			model.renderPart("door_lr_gr");
		 	GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("door_lf_rsht");
			model.renderPart("bump_front_blk");
			model.renderPart("blout");
			model.renderPart("door_lr_bl1");
		 	model.renderPart("door_lf_blout");
		 	model.renderPart("bump_front_lbl");
			model.renderPart("bump_front_in");
			model.renderPart("door_rr_bl");
		 	model.renderPart("door_lr_b");
		 	model.renderPart("door_lf_bl");
			model.renderPart("door_rr_bin");
			model.renderPart("door_lr_blin");
		 	model.renderPart("bump_front_bl");
		 	model.renderPart("blin");
		 	model.renderPart("luk");
		 	model.renderPart("door_lr_bl");
		 	model.renderPart("door_rf_blin");
		 	model.renderPart("boot_dvor");
		 	model.renderPart("door_rf_kol");
		 	model.renderPart("boot_bl");
		 	model.renderPart("door_rr_b");
		 	model.renderPart("black");
		 	model.renderPart("door_rf_bl");
		 	model.renderPart("blp");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("door_lf_gl");
		 	model.renderPart("door_rf_gl");
			model.renderPart("d_glass1");
			model.renderPart("windscreen");
			model.renderPart("door_rr_gl");
		 	model.renderPart("d_glass");
		 	model.renderPart("door_lr_gl");
		 	GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.6F);
			model.renderPart("glass1");
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
		 model.renderPart("steer_wood");
		 Minecraft.getMinecraft().renderEngine.bindTexture(inter);
			model.renderPart("steer_inter");
			Minecraft.getMinecraft().renderEngine.bindTexture(leather_black);
			model.renderPart("steer_panlea");
			Minecraft.getMinecraft().renderEngine.bindTexture(velur);
		model.renderPart("steer_pansvetl");
	 }
}