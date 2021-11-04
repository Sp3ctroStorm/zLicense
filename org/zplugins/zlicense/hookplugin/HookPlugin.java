     package org.zplugins.zlicense.hookplugin;
     
     import com.google.common.collect.Lists;
     import java.net.InetAddress;
     import java.util.List;
     import net.md_5.bungee.api.plugin.Plugin;
     import net.md_5.bungee.config.Configuration;
     import org.bukkit.Bukkit;
     import org.bukkit.configuration.file.FileConfiguration;
     import org.bukkit.plugin.Plugin;
     import org.zplugins.zlicense.api.API;
     
     
     
     
     public class HookPlugin
     {
       private API api;
 19    private final List<String> hookedPlugins = Lists.newLinkedList(); public List<String> getHookedPlugins() { return this.hookedPlugins; }
     
     
       
       public HookPlugin(Plugin plugin, String id, Configuration config)
       {
         try {
 26        this.api = new API();
           
 28        InetAddress IP = InetAddress.getLocalHost();
 29        String name = plugin.getDescription().getName();
 30        String key = config.getString("licenseKey");
           
 32        plugin.getLogger().info("§a[zLicense] §fAutenticando o plugin §e" + name);
 33        API.PluginActivationStatus result = this.api.test(id, key, IP.getHostAddress());
           
 35        if (!result.isActive()) {
 36          plugin.getLogger().info(result.getMessage());
 37          plugin.getProxy().stop();
           } else {
 39          plugin.getLogger().info(result.getMessage());
 40          this.hookedPlugins.add(name);
           } 
         } catch (Throwable $ex) {
           throw $ex;
         }  } public HookPlugin(Plugin plugin, String id, FileConfiguration config) {
         try {
 46        InetAddress IP = InetAddress.getLocalHost();
 47        String name = plugin.getDescription().getName();
 48        String key = config.getString("licenseKey");
           
 50        Bukkit.getConsoleSender().sendMessage("§a[zLicense] §fAutenticando o plugin §e" + name);
 51        API.PluginActivationStatus result = this.api.test(id, key, IP.getHostAddress());
           
 53        if (!result.isActive()) {
 54          Bukkit.getConsoleSender().sendMessage(result.getMessage());
 55          Bukkit.getPluginManager().disablePlugin(plugin);
           } else {
 57          Bukkit.getConsoleSender().sendMessage(result.getMessage());
 58          this.hookedPlugins.add(name);
           } 
         } catch (Throwable $ex) {
           throw $ex;
         } 
       }
       
       public HookPlugin() {}
     }
