     package org.zplugins.zlicense.commands.subcommands;
     
     import org.bukkit.Bukkit;
     import org.bukkit.command.CommandSender;
     import org.bukkit.plugin.Plugin;
     import org.zplugins.zlicense.MainSpigot;
     import org.zplugins.zlicense.commands.subcommands.base.SubCommand;
     
     public class Reload extends SubCommand {
       public Reload() {
 11      super("reload", " §a/zlicense reload [Nome do Plugin] §8- §7Para reiniciar um plugin da ZP", "zlicense.reload");
       }
     
       
       public void execute(CommandSender sender, String[] args) {
 16      Bukkit.getPluginManager().disablePlugin((Plugin)MainSpigot.getPlugin(MainSpigot.class));
 17      Bukkit.getPluginManager().enablePlugin((Plugin)MainSpigot.getPlugin(MainSpigot.class));
 18      sender.sendMessage("§a§l[zLicense] §fPlugin reiniciado com sucesso!");
       }
     }


