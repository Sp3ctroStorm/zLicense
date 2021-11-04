     package org.zplugins.zlicense;
     
     import net.md_5.bungee.api.plugin.Plugin;
     
     public class MainBungee
       extends Plugin
     {
       public void onEnable() {
  9      Object runtime = Runtime.getRuntime();
         
 11      long usedRam = (((Runtime)runtime).totalMemory() - ((Runtime)runtime).freeMemory()) / 1048576L;
 12      long totalRam = ((Runtime)runtime).totalMemory() / 1048576L;
         
 14      getLogger().info("§a[zLicense] §fRAM usada §e" + usedRam + "§f de §e" + totalRam);
       }
     }
 