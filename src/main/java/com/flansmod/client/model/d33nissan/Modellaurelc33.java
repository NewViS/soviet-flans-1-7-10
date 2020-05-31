//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33nissan; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import jp.amrex.modelloader.MQO_MetasequoiaObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modellaurelc33 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation farz = new ResourceLocation("minecraft:d33nissan/textures/model/farz.png");
	private ResourceLocation generic33 = new ResourceLocation("minecraft:d33nissan/textures/model/generic33.png");
	private ResourceLocation inter = new ResourceLocation("minecraft:d33nissan/textures/model/inter.png");
	private ResourceLocation light = new ResourceLocation("minecraft:d33nissan/textures/model/light.png");
	public Modellaurelc33() //Same as Filename
	{	    	
		    steer = inter; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33nissan/textures/model/laurelc33.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = 37.057F;
	        steerY = 78.392F;
	        steerZ = -55.063F;
	        steerR = -25.729F;
	        
	        wheelX = 62F;
	        wheelX1 = 62F;
	        wheelY = 29F;
	        wheelZ = -142F;
	        wheelZ1 = 123F;
	        
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
		 wheelX = 64F;
	        wheelX1 = 64F;
	        wheelY = 27F;
	        wheelZ = -145F;
	        wheelZ1 = 117F;
		 	model.renderPart("color");
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
	 
	 /*@Override
	 public void renderSpedo(){
		 GL11.glPushMatrix();
		 	 
		 GL11.glTranslatef(46.857F, 82.257F, -63.852F);
			 GL11.glRotatef(-10F, 1f, 0f, 0f);
			
			 if(vet>0)
			GL11.glRotatef(-vet*maxSpedoAngle, 0f, 0f, 1f);
			 
			 GL11.glRotatef(10F, 1f, 0f, 0f);
			 GL11.glTranslatef(-46.857F, -82.257F, 63.852F);

			 Minecraft.getMinecraft().renderEngine.bindTexture(strel);
			model.renderPart("spedo");
			GL11.glColor3f(1F, 1F, 1F);
			GL11.glPopMatrix();
	 }*/
	 
	 @Override
	 public void renderTexturedParts(){
		 //GL11.glTranslatef(0.0F, 1.0F, 0.0F);	
		 Minecraft.getMinecraft().renderEngine.bindTexture(farz);
		 	model.renderPart("farz");
			Minecraft.getMinecraft().renderEngine.bindTexture(generic33);
			model.renderPart("vihl");
		 	model.renderPart("cr");
			model.renderPart("dno");
			model.renderPart("gluh");
			model.renderPart("rezin");
		 	model.renderPart("uplot");
			model.renderPart("radiator");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(light);
			model.renderPart("farp");
			Minecraft.getMinecraft().renderEngine.bindTexture(inter);
			model.renderPart("inter");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.7, 0.7, 0.75);
			model.renderPart("zerc");
			model.renderPart("");
			GL11.glColor3d(0.1, 0.1, 0.1);
			model.renderPart("");
			model.renderPart("");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("glass");
			GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.6F);
			model.renderPart("");
			model.renderPart("");
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
	 /*@Override
	 public void renderSteer(){
		model.renderPart("steer");
	 }*/
}