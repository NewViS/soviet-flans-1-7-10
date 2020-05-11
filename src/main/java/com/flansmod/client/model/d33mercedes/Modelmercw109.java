//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33mercedes; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelmercw109 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation w109_ext = new ResourceLocation("minecraft:d33mercedes/textures/model/w109_ext.png");
	private ResourceLocation w109_lights = new ResourceLocation("minecraft:d33mercedes/textures/model/w109_lights.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33mercedes/textures/model/undercarriage.png");
	public Modelmercw109() //Same as Filename
	{	    	
		    steer = w109_ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33mercedes/textures/model/w109.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -34.09F;
	        steerY = 82.231F;
	        steerZ = -50.181F;
	        steerR = -29.0F;
	        
	        wheelX = 65F;
	        wheelX1 = 65F;
	        wheelY = 29F;
	        wheelZ = -150F;
	        wheelZ1 = 128F;
	        
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
		 	model.renderPart("trunk_body");
		 	model.renderPart("hooda_body");
			model.renderPart("bumperFa_body");
			model.renderPart("body_body_2");
			model.renderPart("body_body");
			//model.renderAll();
	 }
	 @Override
	 public void renderWheels(){
		 GL11.glScaled(1.05, 1.05, 1.05);
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(w109_ext);
		 	model.renderPart("trunk_rubber_trim");
		 	model.renderPart("trunk_black");
			model.renderPart("taillightR_black");
			model.renderPart("taillightL_black");
			model.renderPart("steering_wheel_interior");
			model.renderPart("seatR_interior");
			model.renderPart("seatL_interior");
			model.renderPart("interior_interior");
			model.renderPart("hooda_misc");
			model.renderPart("hooda_black");
		 	model.renderPart("headlightR_black");
			model.renderPart("headlightL_black");
			model.renderPart("exhausta_misc");
			model.renderPart("exhausta_black");
			model.renderPart("bumperRa_plastic2");
			model.renderPart("bumperRa_misc");
			model.renderPart("bumperRa_black");
			model.renderPart("bumperFa_plastic2");
			model.renderPart("bumperFa_misc");
			model.renderPart("bumperFa_black");
		 	model.renderPart("body_rubber_trim");
			model.renderPart("body_plastic2");
			model.renderPart("body_misc");
			model.renderPart("body_frame");
			model.renderPart("body_black");
			Minecraft.getMinecraft().renderEngine.bindTexture(w109_lights);
			model.renderPart("taillightR_textured_reflector");
		 	model.renderPart("taillightR_tail_light");
			model.renderPart("taillightR_reverse_light");
			model.renderPart("taillightL_textured_reflector");
			model.renderPart("taillightL_tail_light");
			model.renderPart("taillightL_reverse_light");
			model.renderPart("headlightR_head_light");
			model.renderPart("headlightL_head_light");
			model.renderPart("bumperFa_reflector");
			model.renderPart("body_textured_reflector");
			model.renderPart("body_reflector");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriagea_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			model.renderPart("trunk_chrome");
		 	model.renderPart("mirrorL_chrome");
			model.renderPart("interior_chrome");
			model.renderPart("hooda_chrome");
			model.renderPart("headlightR_chrome");
			model.renderPart("headlightL_chrome");
			model.renderPart("exhausta_chrome");
			model.renderPart("bumperRa_chrome");
			model.renderPart("bumperFa_chrome");
			model.renderPart("body_chrome");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassRR_window");
		 	model.renderPart("glassRM_window");
			model.renderPart("glassRF_window");
			model.renderPart("glassR_window");
			model.renderPart("glassLR_window");
			model.renderPart("glassLM_window");
			model.renderPart("glassLF_window");
			model.renderPart("glassF_window");
			Minecraft.getMinecraft().renderEngine.bindTexture(w109_lights);
			GL11.glColor4f(1F, 1F, 1F, 0.5F);
			model.renderPart("glassRTL_detail_glass_red");
		 	model.renderPart("glassRTL_detail_glass_clear");
			model.renderPart("glassRTL_detail_glass_amber");
			model.renderPart("glassRHL_detail_glass_clear");
			model.renderPart("glassLTL_detail_glass_red");
			model.renderPart("glassLTL_detail_glass_clear");
			model.renderPart("glassLTL_detail_glass_amber");
			model.renderPart("glassLHL_detail_glass_clear");
			model.renderPart("body_detail_glass_clear");
			GL11.glColor4f(1F, 1F, 1F, 1.0F);
			Minecraft.getMinecraft().renderEngine.bindTexture(w109_ext);
			model.renderPart("trunk_badge");
			model.renderPart("hooda_emblem");
			model.renderPart("hooda_badge");
			model.renderPart("body_badge");
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