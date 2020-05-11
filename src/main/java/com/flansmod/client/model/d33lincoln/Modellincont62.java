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

public class Modellincont62 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation lights = new ResourceLocation("minecraft:d33lincoln/textures/model/lights.png");
	private ResourceLocation lincont = new ResourceLocation("minecraft:d33lincoln/textures/model/lincont.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33lincoln/textures/model/undercarriage.png");
	public Modellincont62() //Same as Filename
	{	    	
		    steer = lincont; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33lincoln/textures/model/lincont62.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -37.819F;
	        steerY = 78.571F;
	        steerZ = -46.419F;
	        steerR = -19.691F;
	        
	        wheelX = 69F;
	        wheelX1 = 69F;
	        wheelY = 31F;
	        wheelZ = -162F;
	        wheelZ1 = 131F;
	        
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
		 model.renderPart("lin_continental_62_body_body_");
			model.renderPart("lin_continental_62_trunk_body_");
			model.renderPart("lin_continental_62_hooda_body_");
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(lincont);
		 model.renderPart("lin_continental_62_hooda_black_");
			model.renderPart("lin_continental_62_bumperRa_frame_");
			model.renderPart("lin_continental_62_body_chrome_");
			model.renderPart("lin_continental_62_bumperRa_black_");
			model.renderPart("lin_continental_62_body_frame_");
			model.renderPart("lin_continental_62_bumperFa_frame_");
			model.renderPart("lin_continental_62_hooda_chrome_");
			model.renderPart("lin_continental_62_bumperFa_black_");
			model.renderPart("lin_continental_62_bumperRa_badge_");
			model.renderPart("lin_continental_62_trunk_misc_");
			model.renderPart("lin_continental_62_mirrorL_badge_");
			model.renderPart("lin_continental_62_bumperRa_chrome_");
			model.renderPart("lin_continental_62_body_rubber_trim_");
			model.renderPart("lin_continental_62_body_emblem_");
			model.renderPart("lin_continental_62_exhaustRa_black_");
			model.renderPart("lin_continental_62_trunk_black_");
			model.renderPart("lin_continental_62_hooda_emblem_");
			model.renderPart("lin_continental_62_interior_interior_");
			model.renderPart("lin_continental_62_exhaustLa_misc_");
			model.renderPart("lin_continental_62_seatR_interior_");
			model.renderPart("lin_continental_62_exhaustRa_misc_");
			model.renderPart("lin_continental_62_bumperFa_chrome_");
			model.renderPart("lin_continental_62_interior_chrome_");
			model.renderPart("lin_continental_62_bumperFa_badge_");
			model.renderPart("lin_continental_62_seatL_interior_");
			model.renderPart("lin_continental_62_body_misc_");
			model.renderPart("lin_continental_62_body_black_");
			model.renderPart("lin_continental_62_exhaustLa_black_");
			model.renderPart("lin_continental_62_hooda_frame_");
			model.renderPart("lin_continental_62_trunk_chrome_");
			model.renderPart("lin_continental_62_trunk_frame_");
			model.renderPart("lin_continental_62_mirrorL_chrome_");
			model.renderPart("lin_continental_62_body_badge_");
			Minecraft.getMinecraft().renderEngine.bindTexture(lights);
			model.renderPart("lin_continental_62_headlightL_head_light_");
			model.renderPart("lin_continental_62_headlightR_textured_reflector_");
			model.renderPart("lin_continental_62_body_textured_reflector_");
			model.renderPart("lin_continental_62_taillightR_tail_light_");
			model.renderPart("lin_continental_62_bumperRa_reverse_light_");
			model.renderPart("lin_continental_62_headlightR_head_light_");
			model.renderPart("lin_continental_62_headlightL_textured_reflector_");
			model.renderPart("lin_continental_62_taillightL_tail_light_");
			model.renderPart("lin_continental_62_bumperFa_textured_reflector_");
			model.renderPart("lin_continental_62_glassLTL_lights_glass_");
			model.renderPart("lin_continental_62_glassLTL_reflector_");
			model.renderPart("lin_continental_62_glassRTL_lights_glass_");
			model.renderPart("lin_continental_62_glassRTL_reflector_");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("lin_continental_62_undercarriagea_undercarriage_");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("lin_continental_62_glassLF_window_");
			model.renderPart("lin_continental_62_glassRR_window_");
			model.renderPart("lin_continental_62_glassRF_window_");
			model.renderPart("lin_continental_62_glassF_window_");
			model.renderPart("lin_continental_62_glassR_window_");
			model.renderPart("lin_continental_62_glassLR_window_");
			GL11.glColor4d(1, 1, 1, 0.4);
			Minecraft.getMinecraft().renderEngine.bindTexture(lights);
			model.renderPart("lin_continental_62_glassLHL_detail_glass_clear_");
			model.renderPart("lin_continental_62_bumperFa_lights_glass_");
			model.renderPart("lin_continental_62_bumperRa_lights_glass_");
			model.renderPart("lin_continental_62_glassRHL_detail_glass_clear_");
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