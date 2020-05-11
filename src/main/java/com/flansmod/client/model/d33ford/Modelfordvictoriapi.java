//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33ford; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelfordvictoriapi extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation ext = new ResourceLocation("minecraft:d33ford/textures/model/ext.png");
	private ResourceLocation under = new ResourceLocation("minecraft:d33ford/textures/model/under.png");
	//private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33bmw/textures/model/undercarriage.png");
	public Modelfordvictoriapi() //Same as Filename
	{	    	
		    steer = ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33ford/textures/model/fordvictoria.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -35.077F;
	        steerY = 78.889F;
	        steerZ = -38.511F;
	        steerR = -24F;
	        
	        wheelX = 63F;
	        wheelX1 = 63F;
	        wheelY = 29F;
	        wheelZ = -145F;
	        wheelZ1 = 126F;
	        
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
		 	model.renderPart("body1");
		 	model.renderPart("bumpreacol");
			model.renderPart("door_lr_col");
			model.renderPart("trunk_col");
			model.renderPart("trunk_main");
			model.renderPart("trunk_cr");
			model.renderPart("door_rr_col");
			model.renderPart("bonnet");
			model.renderPart("bumpf_col");
			model.renderPart("wingl_col");
			model.renderPart("wingr_col");
			model.renderPart("mord_col");
			model.renderPart("door_fl_col");
			model.renderPart("door_fr_col");
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(ext);
		 	model.renderPart("inter");
			model.renderPart("inter1");
			model.renderPart("inter2");
			model.renderPart("far");
			model.renderPart("door_lr_in");
			model.renderPart("trunk_shild");
			model.renderPart("trunk_light");
			model.renderPart("ant2");
			model.renderPart("door_rr_in");
			model.renderPart("motor_ver");
			model.renderPart("logo");
			model.renderPart("farl_in");
			model.renderPart("farl_pov");
			model.renderPart("farr_in");
			model.renderPart("farr_pov");
			model.renderPart("door_fl_in");
			model.renderPart("door_fr_in");
			model.renderPart("polin1");
			Minecraft.getMinecraft().renderEngine.bindTexture(under);
			model.renderPart("dno");
			model.renderPart("dno1");
			model.renderPart("dno2");
			model.renderPart("door_lr_bl");
			model.renderPart("door_rr_bl");
			model.renderPart("podv_rea");
			model.renderPart("glrea_bl");
			model.renderPart("motor_niz");
			model.renderPart("wingl_in");
			model.renderPart("wingr_in");
			model.renderPart("radiator");
			model.renderPart("farl_bl");
			model.renderPart("farr_bl");
			model.renderPart("door_fl_bl");
			model.renderPart("mirl_bl");
			model.renderPart("door_fr_bl");
		 	model.renderPart("mirr_bl");
			model.renderPart("podv_pered");
			model.renderPart("windscrebl");
			model.renderPart("bunmpf_bl");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.05, 0.05, 0.05);
			model.renderPart("kang");
			model.renderPart("reshet");
			model.renderPart("bumpreabl");
			model.renderPart("trunk_bl");
			model.renderPart("bl");
			GL11.glColor3d(0.6, 0.6, 0.6);
			model.renderPart("door_lr_cr");
			model.renderPart("door_rr_cr");
			model.renderPart("farl_cr");
			model.renderPart("farr_cr");
			model.renderPart("door_fl_cr");
			model.renderPart("mirl_zer");
			model.renderPart("door_fr_cr");
			model.renderPart("mirr_zer");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("door_lr_gl");
			model.renderPart("door_rr_gl");
			model.renderPart("glrea_gl");
			model.renderPart("door_fl_gl");
			model.renderPart("door_fr_gl");
			model.renderPart("windscregl");
			GL11.glColor4f(0.8F, 0.8F, 0.8F, 0.6F);
			model.renderPart("farr_gl");
			model.renderPart("farl_gl");
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