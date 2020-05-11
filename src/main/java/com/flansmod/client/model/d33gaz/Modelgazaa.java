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

public class Modelgazaa extends SovietModelVehicleReaSpar //Same as Filename
{
	int textureX = 512;
	int textureY = 512;
	//private ResourceLocation black_dirt = new ResourceLocation("minecraft:d33vaz/textures/model/black_dirt.png");
	private ResourceLocation h879 = new ResourceLocation("minecraft:d33gaz/textures/model/7h879.png");
	private ResourceLocation aar= new ResourceLocation("minecraft:d33gaz/textures/model/aar.png");
	private ResourceLocation vehiclelights = new ResourceLocation("minecraft:d33gaz/textures/model/vehiclelights128.png");
	private ResourceLocation cveca = new ResourceLocation("minecraft:d33gaz/textures/model/cveca.png");
	private ResourceLocation ewe = new ResourceLocation("minecraft:d33gaz/textures/model/ewe.png");
	private ResourceLocation gazaa_28= new ResourceLocation("minecraft:d33gaz/textures/model/gazaa_28.png");
	private ResourceLocation pribor= new ResourceLocation("minecraft:d33gaz/textures/model/pribor.png");
	private ResourceLocation reshtka1= new ResourceLocation("minecraft:d33gaz/textures/model/reshtka1.png");
	private ResourceLocation rul= new ResourceLocation("minecraft:d33gaz/textures/model/rul.png");
	private ResourceLocation shnyaga= new ResourceLocation("minecraft:d33gaz/textures/model/shnyaga.png");
	public Modelgazaa() //Same as Filename
	{	    	
		    steer = rul; //new ResourceLocation("d33vaz:textures/model/color.png");
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33gaz/textures/model/gazaa.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);
	        
	       // ResourceLocation disk = new ResourceLocation("minecraft:d33vaz/textures/model/disk/statusdisk_circle.obj");
	       // modeldisk = AdvancedModelLoader.loadModel(disk);
	       // modeldisk = new ModelWrapperDisplayList((WavefrontObject) modeldisk);

	        steerX = -23.73F;
	        steerY = 106.163f;
	        steerZ = -143.189F;
	        steerR = -35.746F;
	        
	        wheelX = 77F;
	        wheelX1 = 80F;
	        wheelY = 38f;
	        wheelZ = -251F;
	        wheelZ1 = 95F;
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
		 GL11.glColor3f(1F, 1F, 1F);
		 //GL11.glColor3d(91/255d, 117/255d, 108/255d);
		GL11.glScalef(0.77F, 0.68F, 0.66F);
		GL11.glTranslatef(0, 168F, -64F);
	 }
	
	 @Override
	 public void renderColoredParts(){
		// GL11.glTranslatef(0.0F, -45.0F, 0.0F);	
		model.renderPart("door_rf_col");
		model.renderPart("extra1_pal");
		model.renderPart("panel");
		model.renderPart("inter");
		model.renderPart("col");	
		model.renderPart("wing_lf_ok");
		model.renderPart("door_lf_col");
		model.renderPart("wing_rf_ok");
		//model.renderAll();
	 }
	 @Override
	 public void renderWheelsFro(){
		 GL11.glColor3f(1F, 1F, 1F);
		 	Minecraft.getMinecraft().renderEngine.bindTexture(h879);
			model.renderPart("wheel_rf_sh");
			Minecraft.getMinecraft().renderEngine.bindTexture(gazaa_28);
			model.renderPart("wheel_rf_aa");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("wheel_rf_in");
			model.renderPart("wheel_rf_disk");
			GL11.glColor3f(1F, 1F, 1F);
	 }
	 @Override
	 public void renderWheelsRea(){
		 	Minecraft.getMinecraft().renderEngine.bindTexture(h879);
			model.renderPart("wheel_rb_sh");
			Minecraft.getMinecraft().renderEngine.bindTexture(gazaa_28);
			model.renderPart("wheel_rb_aa");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("wheel_rb_in");
			model.renderPart("wheel_rb_disk");
			GL11.glColor3f(1F, 1F, 1F);
		 }
	 
	 @Override
	 public void renderSteer(){
		model.renderPart("steer_rul");
		Minecraft.getMinecraft().renderEngine.bindTexture(color);
		 GL11.glColor3f(0.1F, 0.1F, 0.1F);
		 model.renderPart("steer_pal");
	 }
	 
	 @Override
	 public void renderGlass(){
		 Minecraft.getMinecraft().renderEngine.bindTexture(color);
		 GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("door_rf_gl");
			model.renderPart("window");
			model.renderPart("door_lf_gl");
			model.renderPart("windscre");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
         GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 
	 @Override
	 public void renderTexturedParts(){
		 GL11.glScaled(0.91, 0.91, 0.91);
			Minecraft.getMinecraft().renderEngine.bindTexture(reshtka1);
			model.renderPart("reshet");
			Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights);
			model.renderPart("faraper");
			model.renderPart("faraper01");
			model.renderPart("farz");
			model.renderPart("farz2");
			model.renderPart("lamp");
			Minecraft.getMinecraft().renderEngine.bindTexture(shnyaga);
			model.renderPart("ress");
			model.renderPart("prf");
			model.renderPart("sidenie_niz");
			model.renderPart("podveska");
			model.renderPart("per_ressor");
			Minecraft.getMinecraft().renderEngine.bindTexture(ewe);
			model.renderPart("znak");
			model.renderPart("sidenie");
			Minecraft.getMinecraft().renderEngine.bindTexture(rul);
			model.renderPart("pedal_rul");
			model.renderPart("rul");
			Minecraft.getMinecraft().renderEngine.bindTexture(pribor);
			model.renderPart("prib");
			Minecraft.getMinecraft().renderEngine.bindTexture(aar);
			model.renderPart("radiator");
			Minecraft.getMinecraft().renderEngine.bindTexture(cveca);
			model.renderPart("dvig_svech");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.7F, 0.7F, 0.7F);
			model.renderPart("extra1_zer");
			GL11.glColor3f(0.3F, 0.3F, 0.3F);	
			model.renderPart("door_rf_ruk");
			model.renderPart("dvornik");
			model.renderPart("dvig_cr");
			model.renderPart("door_lf_ruk");
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("faraper1");
			model.renderPart("faraper011");
			model.renderPart("rama");
			model.renderPart("bamper");
			model.renderPart("dvornik_bl");
			model.renderPart("sidenie_kaima");
			model.renderPart("dvig_bl");
			model.renderPart("dvig_skiv");
			model.renderPart("extra1_bl");
			model.renderPart("derzh");
			model.renderPart("farbl");
			model.renderPart("richag");
			model.renderPart("pedal");
			model.renderPart("zaspin");
			model.renderPart("bl_cab");
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 @Override
	 public void frontWheelsStuff(){
	 }
}