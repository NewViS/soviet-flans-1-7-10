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

public class Modelmercw123 extends SovietModelVehicle //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation w123_steer = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_steer.png");
	private ResourceLocation many = new ResourceLocation("minecraft:d33mercedes/textures/model/many.png");
	private ResourceLocation metal = new ResourceLocation("minecraft:d33mercedes/textures/model/metal.png");
	private ResourceLocation stuff = new ResourceLocation("minecraft:d33mercedes/textures/model/stuff.png");
	private ResourceLocation torec = new ResourceLocation("minecraft:d33mercedes/textures/model/torec.png");
	private ResourceLocation w123_carpet = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_carpet.png");
	private ResourceLocation w123_colors = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_colors.png");
	private ResourceLocation w123_cons = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_cons.png");
	private ResourceLocation w123_engine = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_engine.png");
	private ResourceLocation w123_engine_2 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_engine_2.png");
	private ResourceLocation w123_engine_3 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_engine_3.png");
	private ResourceLocation w123_fara = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_fara.png");
	private ResourceLocation w123_leather_1 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_leather_1.png");
	private ResourceLocation w123_leather_1_dyr = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_leather_1_dyr.png");
	private ResourceLocation w123_leather_2 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_leather_2.png");
	private ResourceLocation w123_leather_dark = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_leather_dark.png");
	private ResourceLocation w123_niz = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_niz.png");
	private ResourceLocation w123_pedals = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_pedals.png");
	private ResourceLocation w123_plast = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_plast.png");
	private ResourceLocation w123_plastic = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_plastic.png");
	private ResourceLocation w123_potolok = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_potolok.png");
	private ResourceLocation w123_sid1 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_sid1.png");
	private ResourceLocation w123_sid2 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_sid2.png");
	private ResourceLocation w123_sid3 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_sid3.png");
	private ResourceLocation w123_sid4 = new ResourceLocation("minecraft:d33mercedes/textures/model/w123_sid4.png");
	public Modelmercw123() //Same as Filename
	{	    	
		    steer = w123_steer; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33mercedes/textures/model/w123.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        ismqo=true;
	        
	        steerX = -33.632F;
	        steerY = 77.675F;
	        steerZ = -53.803F;
	        steerR = -25.446F;
	        
	        wheelX = 62F;
	        wheelX1 = 62F;
	        wheelY = 29F;
	        wheelZ = -148F;
	        wheelZ1 = 130F;
	        
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
		 wheelX = 62F;
	        wheelX1 = 62F;
	        wheelY = 29F;
	        wheelZ = -148F;
	        wheelZ1 = 130F;
		 //Minecraft.getMinecraft().renderEngine.bindTexture(bmw_m5_ext_alpine_white);
		 	model.renderPart("col");
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
		 Minecraft.getMinecraft().renderEngine.bindTexture(many);
		 	model.renderPart("many");
		 Minecraft.getMinecraft().renderEngine.bindTexture(metal);
			 	model.renderPart("metal");
		Minecraft.getMinecraft().renderEngine.bindTexture(stuff);
				 	model.renderPart("stuff");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(torec);
				 	model.renderPart("torec");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_carpet);
				 	model.renderPart("bagins");
				 	model.renderPart("carpet");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_colors);
				 	model.renderPart("colors");
				 	model.renderPart("bl_colors");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_cons);
				 	model.renderPart("cons");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_engine);
				 	model.renderPart("eng");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_engine_2);
				 	model.renderPart("eng2");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_engine_3);
				 	model.renderPart("eng3");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_fara);
				 	model.renderPart("far");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_leather_1);
				 	model.renderPart("lea11");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_leather_1_dyr);
				 	model.renderPart("lea1");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_leather_2);
				 	model.renderPart("lea2");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_leather_dark);
				 	model.renderPart("lead");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_niz);
				 	model.renderPart("niz");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_pedals);
				 	model.renderPart("pedal");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_plastic);
				 	model.renderPart("plastic");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_plast);
				 	model.renderPart("plast");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_potolok);
				 	model.renderPart("potol");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_sid1);
				 	model.renderPart("sed1");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_sid2);
				 	model.renderPart("sed2");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_sid3);
				 	model.renderPart("sid3");
				 	Minecraft.getMinecraft().renderEngine.bindTexture(w123_sid4);
				 	model.renderPart("sed4");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3d(0.6, 0.6, 0.6);
			model.renderPart("chrome");
			model.renderPart("cr");
			GL11.glColor3d(0.9, 0.9, 0.9);
		 	model.renderPart("white");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("gl");
			GL11.glColor4f(0.6F, 0.6F, 0.65F, 0.5F);
			model.renderPart("glfr");
			GL11.glColor4f(1F, 1F, 1F, 1.0F);
			model.renderPart("");
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