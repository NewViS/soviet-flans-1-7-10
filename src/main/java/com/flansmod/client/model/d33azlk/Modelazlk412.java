package com.flansmod.client.model.d33azlk;

import org.lwjgl.opengl.GL11;

import com.flansmod.client.model.ModelWrapperDisplayList;
import com.flansmod.client.model.SovietModelVehicle;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.obj.WavefrontObject;

public class Modelazlk412 extends SovietModelVehicle //как и название файла класса
{
	int textureX = 512;
	int textureY = 512;
	private ResourceLocation text1 = new ResourceLocation("minecraft:d33azlk/textures/model/bump.png");
	private ResourceLocation text2 = new ResourceLocation("minecraft:d33azlk/textures/model/dirt.png");
	private ResourceLocation text3 = new ResourceLocation("minecraft:d33azlk/textures/model/divan.png");
	private ResourceLocation text4 = new ResourceLocation("minecraft:d33azlk/textures/model/f412.png");
	private ResourceLocation text5 = new ResourceLocation("minecraft:d33azlk/textures/model/obivka_3.png");
	private ResourceLocation text6 = new ResourceLocation("minecraft:d33azlk/textures/model/optika.png");
	private ResourceLocation text7 = new ResourceLocation("minecraft:d33azlk/textures/model/prib.png");
	private ResourceLocation text8 = new ResourceLocation("minecraft:d33azlk/textures/model/pribor412.png");
	private ResourceLocation text9 = new ResourceLocation("minecraft:d33azlk/textures/model/radiator.png");
	private ResourceLocation azlk_logo = new ResourceLocation("minecraft:d33azlk/textures/model/num.png");
	private ResourceLocation vehiclelights128 = new ResourceLocation("minecraft:d33azlk/textures/model/vehiclelights128.png");
	public Modelazlk412() //как и название файла
	{	    	
		steer = text1;
		    ResourceLocation ArbolPlataform = new ResourceLocation("minecraft:d33azlk/textures/model/azlk412.obj");
		    model = AdvancedModelLoader.loadModel(ArbolPlataform);
	        model = new ModelWrapperDisplayList((WavefrontObject) model);

	        ismqo=true;
			 
			steerX = -29.233F;
		    steerY = 89.123F;
		    steerZ = -44.108F;
		    steerR = -33.963F;
	        
	        wheelX = 56F;
	        wheelX1 = 56F;
	        wheelY = 25F;
	        wheelZ = -134F;
	        wheelZ1 = 107F;
	        
	        translateAll(0F, 0F, 0F);
	        
	}
	 @Override
	 public void renderColoredParts(){
	        
		model.renderPart("col");
		model.renderPart("door_rr_col");
		model.renderPart("door_rf_col");
		model.renderPart("door_lr_col");
		model.renderPart("door_lf_col");
		model.renderPart("boot_cr");
		model.renderPart("bonnet");
		model.renderPart("colo");
		model.renderPart("");
		model.renderPart("");
		model.renderPart("");
	 }
	 @Override
	 public void renderSteer(){
		 Minecraft.getMinecraft().renderEngine.bindTexture(color);
		 GL11.glColor3f(0.7F, 0.7F, 0.7F);
		model.renderPart("rul_cr");
		GL11.glColor3f(0.2F, 0.2F, 0.2F);
		model.renderPart("rul");
		GL11.glColor3f(1F, 1F, 1F);
	 }
	
	 @Override
	 public void renderTexturedParts(){
		//text3 = new ResourceLocation("minecraft:d33azlk/textures/model/divan.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(text9);
			model.renderPart("rad");
			Minecraft.getMinecraft().renderEngine.bindTexture(vehiclelights128);
			model.renderPart("body_far");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(text2);
			model.renderPart("dno");
			model.renderPart("dvig");
			Minecraft.getMinecraft().renderEngine.bindTexture(text8);
			model.renderPart("prib");
			Minecraft.getMinecraft().renderEngine.bindTexture(text7);
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(text1);
			model.renderPart("bump_reacr");
			model.renderPart("bump_fro");
			model.renderPart("");
			model.renderPart("");
			Minecraft.getMinecraft().renderEngine.bindTexture(text6);
			model.renderPart("body_pov");
			Minecraft.getMinecraft().renderEngine.bindTexture(azlk_logo);
			model.renderPart("logo");
			Minecraft.getMinecraft().renderEngine.bindTexture(text5);
			model.renderPart("potol");
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("pol");
			GL11.glColor3f(0.2F, 0.2F, 0.2F);
			model.renderPart("panel");
			GL11.glColor3f(1.6F, 1.6F, 1.6F);
			model.renderPart("sedzad");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			GL11.glColor3f(95/255F, 160/255F, 198/255F);
			model.renderPart("door_rr_ob");
			model.renderPart("door_rf_ob");
			model.renderPart("door_lr_ob");
			model.renderPart("door_lf_ob");
			model.renderPart("panelup");
			Minecraft.getMinecraft().renderEngine.bindTexture(text3);
			model.renderPart("sed");
			Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(250/255F, 40/255F, 40/255F);
			model.renderPart("akk");
			GL11.glColor3f(237/255F, 187/255F, 66/255F);
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			GL11.glColor3f(0.8F, 0.8F, 0.8F);
			model.renderPart("");
			//хром
			GL11.glColor3f(0.7F, 0.7F, 0.7F);
			model.renderPart("zercin");
			model.renderPart("cr");
			model.renderPart("nom");
			model.renderPart("body_main");
			model.renderPart("bump_rea");
			model.renderPart("door_rr_cr");
			model.renderPart("door_rr_ch");
			model.renderPart("door_rf_cr");
			model.renderPart("door_rf_ch");
			model.renderPart("door_lr_cr");
			model.renderPart("door_lr_ch");
			model.renderPart("door_lf_cr");
			model.renderPart("door_lf_ch");
			model.renderPart("boot");
			model.renderPart("cr1");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			//черный
			GL11.glColor3f(0.1F, 0.1F, 0.1F);
			model.renderPart("bl");
			model.renderPart("door_rr_ruk");
			model.renderPart("door_rf_bl");
			model.renderPart("door_lr_bl");
			model.renderPart("door_lf_bl");
			model.renderPart("kpp");
			model.renderPart("pal");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
			model.renderPart("");
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
            GL11.glEnable(GL11.GL_BLEND);
			GL11.glDepthMask(false);
			GL11.glColor4f(0.1F, 0.1F, 0.1F, 0.6F);
			model.renderPart("steklo_z");
		 	model.renderPart("windscre");
			model.renderPart("door_rr_gl");
			model.renderPart("door_rf_gl");
			model.renderPart("door_lr_gl");
			model.renderPart("door_lf_gl");
			Minecraft.getMinecraft().renderEngine.bindTexture(text4);
			GL11.glColor4f(1F, 1F, 1F, 0.6F);
			model.renderPart("steklo_fp");
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
            GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }
	 /*@Override
	 public void frontWheelsStuff(){
		 Minecraft.getMinecraft().renderEngine.bindTexture(color);
			GL11.glColor3f(0.3F, 0.3F, 0.3F);
			GL11.glTranslatef(-10F, 0, 0);
			model.renderPart("");
			GL11.glTranslatef(10F, 0, 0);
			GL11.glColor3f(1.0F, 1.0F, 1.0F);
	 }*/
}
