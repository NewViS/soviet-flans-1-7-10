package ru.csgomod.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.client.model.obj.WavefrontObject;
public class RendererCZ implements IItemRenderer {
	 private int list; 
	 private ResourceLocation tex;
    private ResourceLocation ArbolPlataform= new ResourceLocation("csgomod:textures/model/cz75.obj");
    public RendererCZ(String path) {
    	tex= new ResourceLocation("csgomod:textures/model/cz"+path+".png");
    	
    	IModelCustom model = AdvancedModelLoader.loadModel(ArbolPlataform);
    	model = new ModelWrapperDisplayList((WavefrontObject) model);
    	
    	list = GL11.glGenLists(1);
        GL11.glNewList(list, GL11.GL_COMPILE);
   	    GL11.glDisable(GL11.GL_LIGHTING);
        model.renderAll();
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEndList();
    }
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
    	GL11.glPushMatrix();
    	
            	Minecraft.getMinecraft().renderEngine.bindTexture(tex);
        	
        switch (type) {
      
        case EQUIPPED: // render in third person
        	GL11.glPushMatrix();
        	 GL11.glScalef(0.5F, 0.5F, -0.5F);   		
    		GL11.glRotatef(95F, 0.0f, 1.0f, 0.0f);
    		GL11.glRotatef(-35F, 1.0f, 0.0f, 0.0f);
    		GL11.glRotatef(0F, 0.0f, 0.0f, 1.0f);
    		GL11.glTranslatef(-0.1F, -1.0F, 2.0F);	
    		
    		GL11.glCallList(list);
    	  
    		GL11.glPopMatrix();
            break;

        case EQUIPPED_FIRST_PERSON:

//rince and repeat the rendering. adjust axis' and translation as needed
            GL11.glPushMatrix();
         
            GL11.glScalef(0.6F, 0.6F, 0.6F);
    		GL11.glTranslatef(1.4F, 0.1F, -0.1F);	
    		GL11.glRotatef(75F, 0.0f, 1.0f, 0.0f);
    		GL11.glRotatef(-30F, 1.0f, 0.0f, 0.0f);
    		GL11.glRotatef(7F, 0.0f, 0.0f, 1.0f);
    		GL11.glCallList(list);
            GL11.glPopMatrix();
            break;

        case ENTITY:
            GL11.glPushMatrix();
            GL11.glScalef(0.9F, 0.9F, 0.9F);
            GL11.glRotatef(90F, 0.0f, 0.0f, 1.0f);
            GL11.glTranslatef( -0.2F, -1F, 0F);
            GL11.glCallList(list);
            GL11.glPopMatrix();
            break;

        case INVENTORY:
            GL11.glPushMatrix();
            GL11.glScalef(0.6F, 0.6F, 0.6F);
            GL11.glRotatef(0F, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(0F, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(0.0F, -0.5F, 0F);
//this is a method made by me in my model class to render only the modelparts, without an entity argument, because in your inventory, //the entity is always null.
            GL11.glCallList(list);
            GL11.glPopMatrix();
            break;

        default:
            break;
        }
        GL11.glPopMatrix();
    }
@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
            ItemRendererHelper helper) {

        switch (type) {
        case INVENTORY:
            return true;
        default:
            break;
        }
        return false;

    }

}