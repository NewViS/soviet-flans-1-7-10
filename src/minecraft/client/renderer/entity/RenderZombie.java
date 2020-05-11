package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombie extends RenderBiped {

   private static final ResourceLocation zombiePigmanTextures = new ResourceLocation("textures/entity/zombie_pigman.png");
   private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
   private static final ResourceLocation zombieVillagerTextures = new ResourceLocation("textures/entity/zombie/zombie_villager.png");
   private ModelBiped field_82434_o;
   private ModelZombieVillager zombieVillagerModel;
   protected ModelBiped field_82437_k;
   protected ModelBiped field_82435_l;
   protected ModelBiped field_82436_m;
   protected ModelBiped field_82433_n;
   private int field_82431_q = 1;


   public RenderZombie() {
      super(new ModelZombie(), 0.5F, 1.0F);
      this.field_82434_o = super.modelBipedMain;
      this.zombieVillagerModel = new ModelZombieVillager();
   }

   protected void func_82421_b() {
      super.field_82423_g = new ModelZombie(1.0F, true);
      super.field_82425_h = new ModelZombie(0.5F, true);
      this.field_82437_k = super.field_82423_g;
      this.field_82435_l = super.field_82425_h;
      this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
      this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
   }

   protected int shouldRenderPass(EntityZombie var1, int var2, float var3) {
      this.func_82427_a(var1);
      return super.shouldRenderPass((EntityLiving)var1, var2, var3);
   }

   public void doRender(EntityZombie var1, double var2, double var4, double var6, float var8, float var9) {
      this.func_82427_a(var1);
      super.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   protected ResourceLocation getEntityTexture(EntityZombie var1) {
      return var1 instanceof EntityPigZombie?zombiePigmanTextures:(var1.isVillager()?zombieVillagerTextures:zombieTextures);
   }

   protected void renderEquippedItems(EntityZombie var1, float var2) {
      this.func_82427_a(var1);
      super.renderEquippedItems((EntityLiving)var1, var2);
   }

   private void func_82427_a(EntityZombie var1) {
      if(var1.isVillager()) {
         if(this.field_82431_q != this.zombieVillagerModel.func_82897_a()) {
            this.zombieVillagerModel = new ModelZombieVillager();
            this.field_82431_q = this.zombieVillagerModel.func_82897_a();
            this.field_82436_m = new ModelZombieVillager(1.0F, 0.0F, true);
            this.field_82433_n = new ModelZombieVillager(0.5F, 0.0F, true);
         }

         super.mainModel = this.zombieVillagerModel;
         super.field_82423_g = this.field_82436_m;
         super.field_82425_h = this.field_82433_n;
      } else {
         super.mainModel = this.field_82434_o;
         super.field_82423_g = this.field_82437_k;
         super.field_82425_h = this.field_82435_l;
      }

      super.modelBipedMain = (ModelBiped)super.mainModel;
   }

   protected void rotateCorpse(EntityZombie var1, float var2, float var3, float var4) {
      if(var1.isConverting()) {
         var3 += (float)(Math.cos((double)var1.ticksExisted * 3.25D) * 3.141592653589793D * 0.25D);
      }

      super.rotateCorpse(var1, var2, var3, var4);
   }

   // $FF: synthetic method
   protected void renderEquippedItems(EntityLivingBase var1, float var2) {
      this.renderEquippedItems((EntityZombie)var1, var2);
   }

}
