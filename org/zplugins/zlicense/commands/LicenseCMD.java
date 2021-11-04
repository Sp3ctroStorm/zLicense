     package org.zplugins.zlicense.commands;
     
     import java.util.Arrays;
     import java.util.List;
     import org.bukkit.command.Command;
     import org.bukkit.command.CommandExecutor;
     import org.bukkit.command.CommandSender;
     import org.zplugins.zlicense.commands.subcommands.Plugins;
     import org.zplugins.zlicense.commands.subcommands.Reload;
     import org.zplugins.zlicense.commands.subcommands.base.SubCommand;
     
     public class LicenseCMD
       implements CommandExecutor
     {
 15    private final List<SubCommand> subCommands = Arrays.asList(new SubCommand[] { (SubCommand)new Reload(), (SubCommand)new Plugins() });
     
       
       public boolean onCommand(CommandSender sender, Command cmd, String lb, String[] args) {
 19      if (!sender.hasPermission("zlicense.use")) return true;
         
 21      if (args.length == 0) {
 22        sender.sendMessage("");
 23        this.subCommands.forEach(s -> sender.sendMessage(s.getUsage()));
 24        sender.sendMessage("");
 25        return true;
         } 
         
 28      this.subCommands.forEach(s -> {
               if (args[0].equalsIgnoreCase(s.getName())) {
                 if (sender.hasPermission(s.getPermission()) || s.getPermission().isEmpty()) {
                   s.execute(sender, args);
                 } else {
                   sender.sendMessage("§cVocê não tem permissão para isto!");
                 } 
               }
             });
 37      return false;
       }
     }

