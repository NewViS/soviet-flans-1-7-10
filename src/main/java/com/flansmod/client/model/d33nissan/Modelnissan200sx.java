//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33nissan; //Path where the model is located

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

public class Modelnissan200sx extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	//private ResourceLocation badging = new ResourceLocation("minecraft:d33nissan/textures/model/badging.png");
	private ResourceLocation badging1 = new ResourceLocation("minecraft:d33nissan/textures/model/badging1.png");
	private ResourceLocation car_chassis = new ResourceLocation("minecraft:d33nissan/textures/model/car_chassis.png");
	private ResourceLocation carpet_grey = new ResourceLocation("minecraft:d33nissan/textures/model/carpet_grey.png");
	private ResourceLocation cloth_brightgrey = new ResourceLocation("minecraft:d33nissan/textures/model/cloth_brightgrey.png");
	private ResourceLocation cloth02_mgrey = new ResourceLocation("minecraft:d33nissan/textures/model/cloth02_mgrey.png");
	private ResourceLocation cockpit = new ResourceLocation("minecraft:d33nissan/textures/model/cockpit.png");
	private ResourceLocation engine = new ResourceLocation("minecraft:d33nissan/textures/model/engine.png");
	private ResourceLocation gauges = new ResourceLocation("minecraft:d33nissan/textures/model/gauges.png");
	private ResourceLocation interior = new ResourceLocation("minecraft:d33nissan/textures/model/nadpis.png");
	private ResourceLocation dash_plastic = new ResourceLocation("minecraft:d33nissan/textures/model/dash_plastic.png");
	private ResourceLocation lights = new ResourceLocation("minecraft:d33nissan/textures/model/lights.png");
	private ResourceLocation misc = new ResourceLocation("minecraft:d33nissan/textures/model/misc.png");
	private ResourceLocation steeringwheel = new ResourceLocation("minecraft:d33nissan/textures/model/steeringwheel.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33nissan/textures/model/vehiclelights.png");
	//private double angle=0;
	public Modelnissan200sx() //Same as Filename
	{	    	
		    steer = steeringwheel; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33nissan/textures/model/200sx.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -34.853F;
	        steerY = 76.72F;
	        steerZ = -40.847F;
	        steerR = -14.273F;
	        
	        wheelX = 58F;
	        wheelX1 = 58F;
	        wheelY = 29F;
	        wheelZ = -142F;
	        wheelZ1 = 108F;
	        
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
		 	GL11.glShadeModel(GL11.GL_SMOOTH);
		 	model.renderPart("door_lf_ok3");
			model.renderPart("bonnet_ok");
			model.renderPart("door_rf_ok6");
			model.renderPart("chassis3");
			model.renderPart("bump_front_ok2");
			model.renderPart("door_rr_Ok");
			model.renderPart("bump_rear_ok1");
			model.renderPart("boot_ok4");
			
				
				 /*GL11.glRotated(-angle, 1f, 0f, 0f);
				 GL11.glTranslated(0, -0.65474, 1.98264);
				 
				GL11.glPopMatrix();*/
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(cloth_brightgrey);
		 	model.renderPart("interior6");
		 	model.renderPart("interior2");
		 	model.renderPart("door_rf_ok4");
		 	model.renderPart("door_lf_ok5");
		 	
		 	/*GL11.glPushMatrix();
		 	GL11.glDisable(GL11.GL_LIGHTING);
		 	OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);*/
			 Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			model.renderPart("bump_front_ok4");
			model.renderPart("bump_front_ok3");
			/*GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPopMatrix();*/
			
			Minecraft.getMinecraft().renderEngine.bindTexture(carpet_grey);
			model.renderPart("interior5");
			model.renderPart("interior11");
			Minecraft.getMinecraft().renderEngine.bindTexture(cockpit);
			model.renderPart("interior9");
			model.renderPart("door_lf_ok4");
			model.renderPart("door_rf_ok5");
			Minecraft.getMinecraft().renderEngine.bindTexture(badging1);
			model.renderPart("bump_front_ok1");
			model.renderPart("boot_ok6");
			model.renderPart("door_lf_ok7");
			model.renderPart("door_rf_ok2");
			Minecraft.getMinecraft().renderEngine.bindTexture(misc);
			model.renderPart("chassis");
			model.renderPart("bump_front_ok");
			model.renderPart("door_lf_ok2");
			model.renderPart("door_rf_ok7");
			model.renderPart("boot_ok");
			model.renderPart("bump_rear_ok");
			Minecraft.getMinecraft().renderEngine.bindTexture(cloth02_mgrey);
			model.renderPart("interior1");
			Minecraft.getMinecraft().renderEngine.bindTexture(dash_plastic);
			model.renderPart("interior8");
			model.renderPart("interior4");
			model.renderPart("interior7");
			model.renderPart("door_rf_ok3");
			model.renderPart("door_lf_ok6");
			Minecraft.getMinecraft().renderEngine.bindTexture(gauges);
			model.renderPart("interior10");
			Minecraft.getMinecraft().renderEngine.bindTexture(lights);
			model.renderPart("lights");
			model.renderPart("boot_ok1");
			model.renderPart("lights1");
			Minecraft.getMinecraft().renderEngine.bindTexture(engine);
			model.renderPart("chassis5");
			Minecraft.getMinecraft().renderEngine.bindTexture(car_chassis);
			model.renderPart("chassis4");
			Minecraft.getMinecraft().renderEngine.bindTexture(interior);
			model.renderPart("chassis7");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.7, 0.7, 0.7);
			model.renderPart("door_lf_ok");
			model.renderPart("interior");
			model.renderPart("interior3");
			model.renderPart("door_rf_ok1");
			model.renderPart("boot_ok5");
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("chassis2");
			model.renderPart("winds1");
			model.renderPart("windscreen_ok");
			model.renderPart("bonnet_ok1");
			model.renderPart("boot_ok3");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("boot_ok2");
			model.renderPart("winds");
			model.renderPart("door_rf_ok");
			model.renderPart("windscreen_ok1");
			model.renderPart("door_lf_ok1");
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
		 GL11.glTranslated(0, 65.474, -198.264);
		 GL11.glRotated(vehicle.angle[0], 1f, 0f, 0f);
		 GL11.glTranslated(0, -65.474, 198.264);
			 
			 Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			 model.renderPart("misc_a3");
			 Minecraft.getMinecraft().renderEngine.bindTexture(color);
			 GL11.glColor3d(0.1, 0.1, 0.1);
			 model.renderPart("misc_a1");
			  
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			GL11.glColor3d(data.fr, data.fg, data.fb);
			 GL11.glTranslated(0, 65.474, -198.264);
			 GL11.glRotated(vehicle.angle[0], 1f, 0f, 0f);
			GL11.glTranslated(0, -65.474, 198.264);
			 model.renderPart("misc_a2");
			 
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