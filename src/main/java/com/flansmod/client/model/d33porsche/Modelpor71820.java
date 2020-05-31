//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33porsche; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelpor71820 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation inter718 = new ResourceLocation("minecraft:d33porsche/textures/model/inter718.png");
	private ResourceLocation light718 = new ResourceLocation("minecraft:d33porsche/textures/model/light718.png");
	private ResourceLocation logos718 = new ResourceLocation("minecraft:d33porsche/textures/model/logos718.png");
	private ResourceLocation grille71 = new ResourceLocation("minecraft:d33porsche/textures/model/grille71.png");
	//private ResourceLocation undercarriage = new ResourceLocation("minecraft:d33porsche/textures/model/undercarriage.png");
	public Modelpor71820() //Same as Filename
	{	    	
		    steer = inter718; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33porsche/textures/model/p718_20.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -28.575F;
	        steerY = 66.025F;
	        steerZ = -23.963F;
	        steerR = -15.293F;
	        
	        wheelX = 58F;
	        wheelX1 = 58F;
	        wheelY = 26F;
	        wheelZ = -106F;
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
		 wheelX = 56F;
	        wheelX1 = 56F;
		 //translateAll(0F, 20F, 0F);
		 //Minecraft.getMinecraft().renderEngine.bindTexture(body);
		 model.renderPart("Paint_Geo_lodB");
		 	 model.renderPart("Paint_Geo_lodA");
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
		 	Minecraft.getMinecraft().renderEngine.bindTexture(inter718);
		 	model.renderPart("Interior_Geo_lodA");
			Minecraft.getMinecraft().renderEngine.bindTexture(light718);
			model.renderPart("LightEmissive_Geo_lodB");
			Minecraft.getMinecraft().renderEngine.bindTexture(logos718);
			model.renderPart("Badge_Geo_lodB");
			Minecraft.getMinecraft().renderEngine.bindTexture(grille71);
			model.renderPart("Grille1_Geo_lodB");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("Base_Geo_lodB");
		 	model.renderPart("Coloured_Geo_lodB");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("Window_Geo_lodB");
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
		model.renderPart("steering_wheel_interior");
	 }*/
}