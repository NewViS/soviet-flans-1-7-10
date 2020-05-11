//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33toyota; //Path where the model is located

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;
import com.flansmod.common.driveables.DriveableData;
import com.flansmod.common.driveables.EntityVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelsupra84 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	//private ResourceLocation badging = new ResourceLocation("minecraft:d33nissan/textures/model/badging.png");
	private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33toyota/textures/model/undercarriage.png");
	private ResourceLocation sup_ext = new ResourceLocation("minecraft:d33toyota/textures/model/sup_ext.png");
	private ResourceLocation sup_lights = new ResourceLocation("minecraft:d33toyota/textures/model/sup_lights.png");
	private ResourceLocation grille = new ResourceLocation("minecraft:d33toyota/textures/model/grill.png");
	//private double angle=0;
	public Modelsupra84() //Same as Filename
	{	    	
		    steer = sup_ext; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33toyota/textures/model/supra84.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -32.731F;
	        steerY = 76.564F;
	        steerZ = -30.997F;
	        steerR = -29.336F;
	        
	        wheelX = 60F;
	        wheelX1 = 60F;
	        wheelY = 29F;
	        wheelZ = -135F;
	        wheelZ1 = 118F;
	        
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
		 	model.renderPart("trunk_body");
		 	model.renderPart("hooda_body");
		 	model.renderPart("bumperRa_body");
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
		 GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(46.857F, 82.257F, -63.852F);
			 GL11.glRotatef(-10F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(10F, 1f, 0f, 0f);
			 GL11.glTranslatef(-46.857F, -82.257F, 63.852F);

			 //Minecraft.getMinecraft().renderEngine.bindTexture(strel);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		 
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(sup_ext);
		 model.renderPart("winga_misc");
		 	model.renderPart("trunk_rubber_trim");
		 	model.renderPart("trunk_plastic2");
		 	model.renderPart("trunk_misc");
		 	model.renderPart("trunk_interior");
		 	model.renderPart("trunk_black");
		 	model.renderPart("trunk_badge");
		 	model.renderPart("taillightR_misc");
		 	model.renderPart("taillightR_frame");
		 	model.renderPart("taillightR_black");
		 	model.renderPart("taillightL_misc");
		 	model.renderPart("taillightL_frame");
		 	model.renderPart("taillightL_black");
		 	model.renderPart("steering_wheel_interior");
		 	model.renderPart("seatR_interior");
		 	model.renderPart("seatL_interior");
		 	model.renderPart("mirrorR_misc");
		 	model.renderPart("mirrorR_chrome");
		 	model.renderPart("mirrorR_black");
		 	model.renderPart("mirrorL_misc");
		 	model.renderPart("mirrorL_chrome");
		 	model.renderPart("mirrorL_black");
		 	model.renderPart("interior_interior");
		 	model.renderPart("interior_chrome");
		 	model.renderPart("hooda_plastic2");
		 	model.renderPart("hooda_black");
		 	model.renderPart("exhausta_misc");
		 	model.renderPart("exhausta_chrome");
		 	model.renderPart("exhausta_black");
		 	model.renderPart("bumperRa_misc");
		 	model.renderPart("bumperRa_frame");
		 	model.renderPart("bumperFrameR_bumper_frame");
		 	model.renderPart("bumperFrameF_bumper_frame");
		 	model.renderPart("bumperFa_rubber_trim");
		 	model.renderPart("bumperFa_misc");
		 	model.renderPart("bumperFa_frame");
		 	model.renderPart("bumperFa_chrome");
		 	model.renderPart("bumperFa_badge");
		 	model.renderPart("body_rubber_trim");
		 	model.renderPart("body_plastic2");
		 	model.renderPart("body_misc");
		 	model.renderPart("body_frame");
		 	model.renderPart("body_emblem");
		 	model.renderPart("body_chrome");
		 	model.renderPart("body_black");
		 	model.renderPart("body_badge");
		 	Minecraft.getMinecraft().renderEngine.bindTexture(grille);
		 	model.renderPart("bumperFa_grille2");
			Minecraft.getMinecraft().renderEngine.bindTexture(sup_lights);
			/*model.renderPart("taillightR_textured_reflector");
		 	model.renderPart("taillightR_tail_light");
		 	model.renderPart("taillightR_reverse_light");
		 	model.renderPart("taillightL_textured_reflector");
		 	model.renderPart("taillightL_tail_light");
		 	model.renderPart("taillightL_reverse_light");
		 	model.renderPart("bumperFa_textured_reflector");*/
		 	model.renderPart("bumperFa_reflector");
		 	model.renderPart("body_reflector");
		 	model.renderPart("glassRTL_lights_glass");
		 	model.renderPart("glassLTL_lights_glass");
		 	model.renderPart("bumperFa_detail_glass_clear");
			Minecraft.getMinecraft().renderEngine.bindTexture(undercarriage);
			model.renderPart("undercarriagea_undercarriage");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glassRR_window");
		 	model.renderPart("glassRF_window");
		 	model.renderPart("glassR_window");
		 	model.renderPart("glassLR_window");
		 	model.renderPart("glassLF_window");
		 	model.renderPart("glassF_window");
		 	GL11.glColor4d(1F, 1F, 1F, 1F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
			
			
	 }
	 @Override
	 public void render(float f5, EntityVehicle vehicle, float f){
		 super.render(f5, vehicle, f);
		 DriveableData data = vehicle.getDriveableData();
		 if(vehicle.varDoor&&vehicle.angle[0]<45){
			 vehicle.angle[0]+=1.0;
		 } else if(!vehicle.varDoor&&vehicle.angle[0]>0)vehicle.angle[0]-=1.0;
 
		 GL11.glPushMatrix();
		 GL11.glColor3d(1, 1, 1);
		 GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glScalef(0.015F, 0.015F, 0.015F);
			GL11.glRotatef(-90F, 0.0f, 1.0f, 0.0f);
			GL11.glTranslatef(0.0F, -40.0F, 0.0F);
			
			pre();
		GL11.glPushMatrix();
		 GL11.glTranslated(0, 74.112, -182.894);
		 GL11.glRotated(vehicle.angle[0], 1f, 0f, 0f);
		 GL11.glTranslated(0, -74.112, 182.894);
		 
		 Minecraft.getMinecraft().renderEngine.bindTexture(sup_ext);
		 model.renderPart("headlightRdown_misc");
		 model.renderPart("headlightRdown_chrome");
		 model.renderPart("headlightRdown_black");
		 model.renderPart("headlightLdown_misc");
		 model.renderPart("headlightLdown_chrome");
		 model.renderPart("headlightLdown_black");
		 Minecraft.getMinecraft().renderEngine.bindTexture(sup_lights);
			model.renderPart("headlightLdown_detail_glass_clear");
		 	model.renderPart("headlightRdown_detail_glass_clear");
			 Minecraft.getMinecraft().renderEngine.bindTexture(color); 
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glColor3d(data.fr, data.fg, data.fb);
			 GL11.glTranslated(0, 74.112, -182.894);
			 GL11.glRotated(vehicle.angle[0], 1f, 0f, 0f);
			GL11.glTranslated(0, -74.112, 182.894);
			model.renderPart("headlightRdown_body");
			model.renderPart("headlightLdown_body");
			 
			GL11.glColor3d(1, 1, 1);
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
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