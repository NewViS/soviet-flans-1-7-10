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

public class Modelfmustang71 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation badge = new ResourceLocation("minecraft:d33ford/textures/model/badge.png");
	private ResourceLocation body = new ResourceLocation("minecraft:d33ford/textures/model/body.png");
	private ResourceLocation lights = new ResourceLocation("minecraft:d33ford/textures/model/lights.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33bmw/textures/model/undercarriage.png");
	public Modelfmustang71() //Same as Filename
	{	    	
		    steer = body; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33ford/textures/model/mustang71.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -36.369F;
	        steerY = 72.581F;
	        steerZ = -16.914F;
	        steerR = -21.608F;
	        
	        wheelX = 65F;
	        wheelX1 = 65F;
	        wheelY = 29F;
	        wheelZ = -135F;
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(body);
		 	model.renderPart("trunk_body");
		 	model.renderPart("mirrorR_body");
			model.renderPart("mirrorL_body");
			model.renderPart("hooda_body");
			model.renderPart("bumperFa_body");
			model.renderPart("body_body");
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.1, 1.1, 1.2);
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(badge);
		 	model.renderPart("winga_body_2");
		 	model.renderPart("trunk_stripe_badge");
			model.renderPart("trunk_emblem");
			model.renderPart("trunk_chrome");
			model.renderPart("trunk_black");
			model.renderPart("taillightR_plastic2");
			model.renderPart("taillightR_chrome_2");
			model.renderPart("taillightR_black");
			model.renderPart("taillightL_plastic2");
			model.renderPart("taillightL_chrome_2");
			model.renderPart("taillightL_black");
			model.renderPart("steering_wheel_interior");
			model.renderPart("seatR_interior");
			model.renderPart("seatL_interior");
			model.renderPart("mirrorR_rubber_trim");
		 	model.renderPart("mirrorR_chrome");
			model.renderPart("mirrorR_black");
			model.renderPart("mirrorR_badge");
			model.renderPart("mirrorL_rubber_trim");
			model.renderPart("mirrorL_chrome");
			model.renderPart("mirrorL_black");
			model.renderPart("mirrorL_badge");
			model.renderPart("interior_interior");
			model.renderPart("interior_chrome");
			model.renderPart("hooda_black");
			model.renderPart("hooda_badge");
			model.renderPart("headlightR_plastic2");
			model.renderPart("headlightR_chrome");
			model.renderPart("headlightL_plastic2");
			model.renderPart("headlightL_chrome");
		 	model.renderPart("exhaustRa_misc");
			model.renderPart("exhaustRa_chrome");
			model.renderPart("exhaustRa_black");
			model.renderPart("exhaustLa_misc");
			model.renderPart("exhaustLa_chrome");
			model.renderPart("exhaustLa_black");
			model.renderPart("bumperRa_frame");
			model.renderPart("bumperRa_chrome");
			model.renderPart("bumperFa_frame");
			model.renderPart("bumperFa_black");
			model.renderPart("body_weatherstrip");
			model.renderPart("body_stripe_badge");
			model.renderPart("body_rubber_trim");
			model.renderPart("body_plastic2");
			model.renderPart("body_misc");
			model.renderPart("body_matte_colors");
			model.renderPart("body_frame");
			model.renderPart("body_emblem");
			model.renderPart("body_chrome_2");
			model.renderPart("body_chrome");
			model.renderPart("body_black");
			model.renderPart("body_badge");
			Minecraft.getMinecraft().renderEngine.bindTexture(lights);
			model.renderPart("taillightR_tail_light");
		 	model.renderPart("taillightL_tail_light");
			model.renderPart("headlightR_head_light");
			model.renderPart("headlightL_head_light");
			model.renderPart("body_textured_reflector");
			model.renderPart("body_reflector");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriagea_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassRR_window");
			model.renderPart("glassRF_window");
			model.renderPart("glassR_window");
			model.renderPart("glassLR_window");
			model.renderPart("glassLF_window");
			model.renderPart("glassF_window");
			GL11.glColor4f(1F, 1F, 1F, 1F);
			Minecraft.getMinecraft().renderEngine.bindTexture(lights);
			model.renderPart("glassRTL_reverse_light");
			model.renderPart("glassRTL_detail_glass_red");
			model.renderPart("glassRHL_detail_glass_clear");
			model.renderPart("glassLTL_reverse_light");
			model.renderPart("glassLTL_detail_glass_red");
			model.renderPart("glassLHL_detail_glass_clear");
			model.renderPart("body_detail_glass_amber");
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