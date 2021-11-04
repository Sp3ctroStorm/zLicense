     package org.zplugins.zlicense;
     
     import org.bukkit.Bukkit;
     import org.bukkit.command.CommandExecutor;
     import org.bukkit.plugin.java.JavaPlugin;
     import org.zplugins.zlicense.commands.LicenseCMD;
     import org.zplugins.zlicense.hookplugin.HookPlugin;
     
     public class MainSpigot
       extends JavaPlugin {
       public HookPlugin getHookPlugin() {
 12      return this.hookPlugin;
       }
       private HookPlugin hookPlugin;
       public void onEnable() {
 16      this.hookPlugin = new HookPlugin();
         
 18      getCommand("zlicense").setExecutor((CommandExecutor)new LicenseCMD());
         
 20      Runtime runtime = Runtime.getRuntime();
         
 22      long usedRam = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
 23      long totalRam = runtime.totalMemory() / 1048576L;
         
 25      Bukkit.getConsoleSender().sendMessage("§a[zLicense] §fRAM usada §e" + usedRam + "§f de §e" + totalRam);
       }
     }


 