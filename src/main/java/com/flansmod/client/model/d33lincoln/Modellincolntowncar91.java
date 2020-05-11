//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33lincoln; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modellincolntowncar91 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation carpet = new ResourceLocation("minecraft:d33lincoln/textures/model/carpet.png");
	private ResourceLocation detail = new ResourceLocation("minecraft:d33lincoln/textures/model/detail.png");
	private ResourceLocation detail1 = new ResourceLocation("minecraft:d33lincoln/textures/model/detail1.png");
	private ResourceLocation doordetail = new ResourceLocation("minecraft:d33lincoln/textures/model/doordetail.png");
	private ResourceLocation enginemesh = new ResourceLocation("minecraft:d33lincoln/textures/model/enginemesh.png");
	private ResourceLocation headlight = new ResourceLocation("minecraft:d33lincoln/textures/model/headlight.png");
	private ResourceLocation leather = new ResourceLocation("minecraft:d33lincoln/textures/model/leather.png");
	private ResourceLocation leather_grey = new ResourceLocation("minecraft:d33lincoln/textures/model/leather_grey.png");
	private ResourceLocation leatherdark = new ResourceLocation("minecraft:d33lincoln/textures/model/leatherdark.png");
	private ResourceLocation logo = new ResourceLocation("minecraft:d33lincoln/textures/model/logo.png");
	private ResourceLocation radiator = new ResourceLocation("minecraft:d33lincoln/textures/model/radiator.png");
	private ResourceLocation rearlight = new ResourceLocation("minecraft:d33lincoln/textures/model/rearlight.png");
	private ResourceLocation steelwheelr = new ResourceLocation("minecraft:d33lincoln/textures/model/steelwheelr.png");
	private ResourceLocation underbody = new ResourceLocation("minecraft:d33lincoln/textures/model/underbody.png");
	private ResourceLocation wood = new ResourceLocation("minecraft:d33lincoln/textures/model/wood.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33lincoln/textures/model/vehiclelights.png");
	public Modellincolntowncar91() //Same as Filename
	{	    	
		    steer = steelwheelr; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33lincoln/textures/model/towncar91.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -37.905F;
	        steerY = 80.107F;
	        steerZ = -76.005F;
	        steerR = -18.456F;
	        
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
	        
		 //Minecraft.getMinecraft().renderEngine.bindTexture(chiron_ext);
	        model.renderPart("door_lr_col");
			model.renderPart("door_lr_colin");
			model.renderPart("door_lf_col");
			model.renderPart("door_lf_colin");
			model.renderPart("door_rr_col");
			model.renderPart("door_rr_colin");
			model.renderPart("door_rf_col");
			model.renderPart("door_rf_colin");
			model.renderPart("col");
			model.renderPart("bump_frocol");
			model.renderPart("bump_reacol");
			model.renderPart("bonnet_in");
			model.renderPart("bonnet_col");
			model.renderPart("boot_col");
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(logo);
		 model.renderPart("bot_logo");
			Minecraft.getMinecraft().renderEngine.bindTexture(detail);
			model.renderPart("detail");
			Minecraft.getMinecraft().renderEngine.bindTexture(underbody);
			model.renderPart("underbody");
			Minecraft.getMinecraft().renderEngine.bindTexture(wood);
			model.renderPart("wood");
			Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			model.renderPart("door_lr_vehl");
			model.renderPart("door_lf_vehl");
			model.renderPart("door_rr_vehl");
			model.renderPart("door_rf_vehl");
			model.renderPart("vehl1");
			model.renderPart("vehlr");
			model.renderPart("vehll");
			model.renderPart("vehlc");
			GL11.glColor3d(0.5, 0.5, 0.5);
			Minecraft.getMinecraft().renderEngine.bindTexture(leather_grey);
			model.renderPart("door_lr_cr");
			model.renderPart("door_lf_cr");
			model.renderPart("door_rr_cr");
			model.renderPart("door_rf_cr");
			Minecraft.getMinecraft().renderEngine.bindTexture(leatherdark);
			model.renderPart("door_lr_lead");
			model.renderPart("door_lf_lead");
			model.renderPart("door_rf_lead");
			model.renderPart("door_rr_lead");
			model.renderPart("lead");
			Minecraft.getMinecraft().renderEngine.bindTexture(leather);
			model.renderPart("door_lr_ok");
			model.renderPart("door_lf_lea");
			model.renderPart("door_rr_lea");
			model.renderPart("door_rf_lea");
			model.renderPart("lea");
			Minecraft.getMinecraft().renderEngine.bindTexture(carpet);
			model.renderPart("door_lr_lea");
			model.renderPart("door_lf_lea2");
			model.renderPart("door_rr_lea2");
			model.renderPart("door_rf_lea2");
			model.renderPart("carpet");
			model.renderPart("boot_in");
			
			Minecraft.getMinecraft().renderEngine.bindTexture(doordetail);
			model.renderPart("door_lr_detail");
			model.renderPart("door_lf_detail");
			model.renderPart("door_rr_detail");
			model.renderPart("door_rf_detail");
			GL11.glColor3d(1, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(detail1);
			model.renderPart("spedom");
			Minecraft.getMinecraft().renderEngine.bindTexture(radiator);
			model.renderPart("radiator");
			Minecraft.getMinecraft().renderEngine.bindTexture(enginemesh);
			model.renderPart("motor");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("windscrebl");
			model.renderPart("glass_bl");
			model.renderPart("door_lr_bl");
			model.renderPart("door_lf_bl");
			model.renderPart("door_rr_bl");
			model.renderPart("door_rf_bl");
			model.renderPart("bl");
			model.renderPart("black");
			model.renderPart("bump_frobl");
			model.renderPart("bump_reabl");
			model.renderPart("bonnet_bl");
			model.renderPart("boot_bl");
			GL11.glColor3f(0.6F, 0.6F, 0.6F);
			model.renderPart("windscrezerc");
			model.renderPart("door_lr_ruk");
			model.renderPart("door_lf_ruk");
			model.renderPart("door_rr_ruk");
			model.renderPart("door_rf_ruk");
			model.renderPart("cr");
			model.renderPart("bump_frocr");
			model.renderPart("bump_reacr");
			model.renderPart("bonnet_sup");
			model.renderPart("boot_cr");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glass_gl");
			model.renderPart("windscregl");
			model.renderPart("door_lr_gl");
			model.renderPart("door_lf_gl");
			model.renderPart("door_rr_gl");
			model.renderPart("door_rf_gl");
			GL11.glColor4f(1f, 1f, 1f, 1f);
			Minecraft.getMinecraft().renderEngine.bindTexture(headlight);
			model.renderPart("lightglass_fro");
			Minecraft.getMinecraft().renderEngine.bindTexture(rearlight);
			model.renderPart("lightglass_rea");
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
		GL11.glColor3d(0.5, 0.5, 0.5);
		model.renderPart("steer");
		Minecraft.getMinecraft().renderEngine.bindTexture(leather);
		model.renderPart("steer_lea");
		GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
}