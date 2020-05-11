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

public class Modelplymrr70 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation grill = new ResourceLocation("minecraft:d33plymouth/textures/model/grill.png");
	private ResourceLocation rr_ext = new ResourceLocation("minecraft:d33plymouth/textures/model/rr_ext.png");
	private ResourceLocation rr_lights = new ResourceLocation("minecraft:d33plymouth/textures/model/rr_lights.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33plymouth/textures/model/undercarriage.png");
	public Modelplymrr70() //Same as Filename
	{	    	
		    steer = rr_ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33plymouth/textures/model/runner70.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -35.083F;
	        steerY = 77.379F;
	        steerZ = -31.561F;
	        steerR = -22.696F;
	        
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
		 //translateAll(0F, 20F, 0F);
		 //Minecraft.getMinecraft().renderEngine.bindTexture(body);
		 	model.renderPart("trunk_a_body");
		 	model.renderPart("interior_body");
			model.renderPart("hooda_body");
			model.renderPart("body_body");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.0, 1.0, 1.2);
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(rr_ext);
		 	model.renderPart("trunk_a_black");
		 	model.renderPart("taillightR_plastic2");
			model.renderPart("taillightL_plastic2");
			model.renderPart("seatL_interior");
			model.renderPart("interior_interior");
			model.renderPart("hooda_black");
			model.renderPart("glassRHL_badge");
		 	model.renderPart("glassLHL_badge");
			model.renderPart("bumperRa_plastic2");
			model.renderPart("bumperRa_black");
			model.renderPart("bumperRa_badge");
			model.renderPart("bumperFa_plastic2");
			model.renderPart("bumperFa_frame");
		 	model.renderPart("bumperFa_badge");
			model.renderPart("body_rubber_trim");
			model.renderPart("body_plastic2");
			model.renderPart("body_matte_colors");
			model.renderPart("body_frame");
			model.renderPart("body_black");
		 	model.renderPart("body_badge");
			Minecraft.getMinecraft().renderEngine.bindTexture(grill);
			model.renderPart("body_grille2_alpha");
			model.renderPart("body_grille2");
			Minecraft.getMinecraft().renderEngine.bindTexture(rr_lights);
			model.renderPart("taillightR_textured_reflector");
		 	model.renderPart("taillightR_taillight2S");
			model.renderPart("taillightL_textured_reflector");
			model.renderPart("taillightL_taillight2S");
			model.renderPart("headlightR_oldhead");
			model.renderPart("headlightL_oldhead");
			model.renderPart("bumperRa_reverse_light");
			model.renderPart("bumperFa_oldhead");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriage_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			model.renderPart("mirrorL_mirrorLeft");
		 	model.renderPart("mirrorL_chrome");
			model.renderPart("hooda_chrome");
			model.renderPart("headlightR_chrome");
			model.renderPart("headlightL_chrome");
			model.renderPart("bumperRa_chrome");
			model.renderPart("bumperFa_chrome");
			model.renderPart("body_chrome");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassRR_glass");
		 	model.renderPart("glassRM_glass");
			model.renderPart("glassRF_glass");
			model.renderPart("glassR_glass");
			model.renderPart("glassLR_glass");
			model.renderPart("glassLM_glass");
			model.renderPart("glassLF_glass");
			model.renderPart("glassF_glass");
			GL11.glColor4f(1F, 1F, 1F, 0.8F);
			Minecraft.getMinecraft().renderEngine.bindTexture(rr_lights);
			model.renderPart("glassRTL_detail_glass_red");
		 	model.renderPart("glassRHL_detail_glass_clear");
			model.renderPart("glassLTL_detail_glass_red");
			model.renderPart("glassLHL_detail_glass_clear");
			model.renderPart("bumperRa_detail_glass_clear");
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
	 @Override
	 public void renderSteer(){
		model.renderPart("steering_wheel_interior");
	 }
}