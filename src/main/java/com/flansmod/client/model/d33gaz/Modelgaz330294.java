//This File was created with the Minecraft-SMP Modelling Toolbox 2.3.0.0
// Copyright (C) 2018 Minecraft-SMP.de
// This file is for Flan's Flying Mod Version 4.0.x+

// Model: vaz2101
// Model Creator: 
// Created on: 01.03.2018 - 00:00:58
// Last changed on: 01.03.2018 - 00:00:58

package com.flansmod.client.model.d33gaz; //Path where the model is located

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicleReaSpar;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelgaz330294 extends SovietModelVehicleReaSpar //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	//private ResourceLocation black_dirt = new ResourceLocation("minecraft:d33vaz/textures/model/black_dirt.png");
	private ResourceLocation bar = new ResourceLocation("minecraft:d33gaz/textures/model/bar.png");
	private ResourceLocation gaz_salon1= new ResourceLocation("minecraft:d33gaz/textures/model/gaz_salon1.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33gaz/textures/model/vehiclelights128.png");
	private ResourceLocation colors = new ResourceLocation("minecraft:d33gaz/textures/model/colors.png");
	private ResourceLocation fary = new ResourceLocation("minecraft:d33gaz/textures/model/fary.png");
	private ResourceLocation kama301= new ResourceLocation("minecraft:d33gaz/textures/model/wheel_kama301.png");
	private ResourceLocation fonzad3302= new ResourceLocation("minecraft:d33gaz/textures/model/fonzad3302.png");
	private ResourceLocation glushak= new ResourceLocation("minecraft:d33gaz/textures/model/glushak.png");
	private ResourceLocation plastik= new ResourceLocation("minecraft:d33gaz/textures/model/plastik.png");
	private ResourceLocation polik= new ResourceLocation("minecraft:d33gaz/textures/model/polik.png");
	private ResourceLocation potolok= new ResourceLocation("minecraft:d33gaz/textures/model/potolok.png");
	private ResourceLocation pribors3302= new ResourceLocation("minecraft:d33gaz/textures/model/pribors3302.png");
	private ResourceLocation sedlo= new ResourceLocation("minecraft:d33gaz/textures/model/sedlo.png");
	private ResourceLocation radiator= new ResourceLocation("minecraft:d33gaz/textures/model/radiator.png");
	private ResourceLocation tdisk= new ResourceLocation("minecraft:d33gaz/textures/model/tdisk.png");
	//public IModelCustom modeldisk;
	public Modelgaz330294() //Same as Filename
	{	    	
		    steer = gaz_salon1; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33gaz/textures/model/gazel_3302.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        
	       // ResourceLocation disk = new ResourceLocation("minecraft:d33vaz/textures/model/disk/statusdisk_circle.obj");
	       // modeldisk = AdvancedModelLoader.loadModel(disk);
	       // modeldisk = new ModelWrapperDisplayList((WavefrontObject) modeldisk);

	        steerX = -55.172F;
	        steerY = 141.208f;
	        steerZ = -194.473F;
	        steerR = -37.043F;
	        
	        wheelX = 100F;
	        wheelX1 = 90F;
	        wheelY = 34f;
	        wheelZ = -231F;
	        wheelZ1 = 83F;
	    	//list = GL11.glGenLists(1);
	        //GL11.glNewList(list, GL11.GL_COMPILE);
	   	  	        
	        //GL11.glEndList();
	    	 ismqo=true;
	    	 isKuzov=true;
	    	 
	        translateAll(0F, 0F, 0F);
	        
	 		//flipAll();	    
	}
	
	 @Override
	 public void renderKuzov(){
		GL11.glScalef(0.865F, 0.9F, 0.86F);
		GL11.glTranslatef(0, 126F, -114F);
	 }
	
	 @Override
	 public void renderColoredParts(){
		model.renderPart("door_rf_col");
		model.renderPart("door_lf_col");
		model.renderPart("bonnet");
		model.renderPart("col");
		model.renderPart("colin");	
		model.renderPart("colst");
	 }
	 @Override
	 public void renderWheelsFro(){
		 	//GL11.glScalef(0.2F, 0.2F, 0.2F);
			Minecraft.getMinecraft().renderEngine.bindTexture(kama301);
			model.renderPart("tyre1");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.9F, 0.9F, 0.9F);
		    model.renderPart("wheel_rf");
		    GL11.glColor3f(1F, 1F, 1F);
	 }
	 @Override
	 public void renderWheelsRea(){
			//GL11.glScalef(0.2F, 0.2F, 0.2F);
		 Minecraft.getMinecraft().renderEngine.bindTexture(kama301);
			model.renderPart("tyre2");
			model.renderPart("tyre21");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.9F, 0.9F, 0.9F);
			model.renderPart("wheel_rb");
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("wheel_rbb");
			GL11.glColor3f(1F, 1F, 1F);
		 }
	 
	 @Override
	 public void renderSteer(){
		model.renderPart("rul");
	 }
	 
	 @Override
	 public void renderGlass(){
		 Minecraft.getMinecraft().renderEngine.bindTexture(color);
		 GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("windscreen");
			model.renderPart("door_rf_gl");
			model.renderPart("door_lf_gl");
			model.renderPart("gl");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		 GL11.glScaled(0.85, 0.85, 0.85);
			Minecraft.getMinecraft().renderEngine.bindTexture(fonzad3302);
			model.renderPart("farz");
			GL11.glColor3d(0.5, 0.5, 0.5);
			Minecraft.getMinecraft().renderEngine.bindTexture(potolok);
			model.renderPart("door_rf_panel");
			model.renderPart("door_lf_panel");
			model.renderPart("obsh");
			GL11.glColor3d(0.3, 0.3, 0.3);
			model.renderPart("sed");
			GL11.glColor3d(1, 1, 1);
			Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			model.renderPart("far");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(colors);
			model.renderPart("gbo_ser");
			model.renderPart("dvig");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(glushak);
			model.renderPart("gluh");
			Minecraft.getMinecraft().renderEngine.bindTexture(radiator);
			model.renderPart("radiator");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(fary);
			model.renderPart("door_rf_logo");
			model.renderPart("door_lf_logo");
			model.renderPart("logo94");
			model.renderPart("pov");
			model.renderPart("pov1");
			model.renderPart("");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(plastik);
			model.renderPart("plast");
			model.renderPart("panel");
			Minecraft.getMinecraft().renderEngine.bindTexture(polik);
			model.renderPart("pol");
			model.renderPart("polik");
			Minecraft.getMinecraft().renderEngine.bindTexture(bar);
			model.renderPart("barab");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(pribors3302);
			model.renderPart("prib");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.7F, 0.7F, 0.7F);
			model.renderPart("door_rf_zerc");
			model.renderPart("door_lf_zerc");
			model.renderPart("");
			GL11.glColor3f(0.3F, 0.3F, 0.3F);	
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("black19");
			model.renderPart("rama");
			model.renderPart("door_rf_zamoc");
			model.renderPart("door_rf_bl");
			model.renderPart("door_lf_zamoc");
			model.renderPart("door_lf_bl");
			model.renderPart("bl");
			model.renderPart("reshetka94");
			model.renderPart("blin");
			model.renderPart("dno");
			model.renderPart("ruk");
			model.renderPart("rez");
			model.renderPart("brizg");
			model.renderPart("brizg1");
			model.renderPart("bump");
			model.renderPart("zacep");
			model.renderPart("stok");
			model.renderPart("povbl");
			model.renderPart("");
			model.renderPart("");
			/*GL11.glColor3f(0.3F, 0.1F, 0.1F);
			model.renderPart("");*/
			GL11.glColor3f(0.5F, 0.1F, 0.1F);
			model.renderPart("gbo_red");
			model.renderPart("");
			//model.renderPart("extra3");
			/*GL11.glColor3f(0.1F, 0.1F, 0.5F);
			model.renderPart("blue1");*/
			//GL11.glColor3f(0.9F, 0.9F, 0.9F);
			//model.renderPart("white");
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.7F, 0.7F, 0.7F, 0.6F);
			model.renderPart("");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 @Override
	 public void frontWheelsStuff(){
		 /*Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.3F, 0.3F, 0.3F);
			GL11.glTranslatef(-10F, 0, 0);
			model.renderPart("brakedisc_");
			GL11.glTranslatef(10F, 0, 0);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);*/
	 }
}