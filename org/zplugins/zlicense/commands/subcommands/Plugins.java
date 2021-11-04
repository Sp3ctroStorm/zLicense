     package org.zplugins.zlicense.commands.subcommands;
     
     import org.bukkit.command.CommandSender;
     import org.zplugins.zlicense.MainSpigot;
     import org.zplugins.zlicense.commands.subcommands.base.SubCommand;
     
     public class Plugins
       extends SubCommand {
       public Plugins() {
 10      super("plugins", " §a/zlicense plugins §8- §7Para ver todos os plugins ativos.", "zlicense.plugins");
       }
     
     
       
       public void execute(CommandSender sender, String[] args) {
 16      MainSpigot plugin = (MainSpigot)MainSpigot.getPlugin(MainSpigot.class);
         
 18      if (plugin.getHookPlugin().getHookedPlugins().isEmpty()) {
 19        sender.sendMessage("§c§lERRO! §fVocê não tem nenhum plugin ativo, compre um em: §b§nhttps://zplugins.org/");
           
           return;
         } 
 23      sender.sendMessage("");
 24      sender.sendMessage(" §eOs plugins ativos no servidor são");
 25      plugin.getHookPlugin().getHookedPlugins().forEach(p -> sender.sendMessage("  §b" + p));
 26      sender.sendMessage("");
 27      sender.sendMessage(" §fUm total de §a" + plugin.getHookPlugin().getHookedPlugins().size() + "§f plugins ativos.");
       }
     }


 