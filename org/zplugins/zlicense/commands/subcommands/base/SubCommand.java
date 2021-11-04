     package org.zplugins.zlicense.commands.subcommands.base;
     import org.bukkit.command.CommandSender;
     
     public abstract class SubCommand {
       private final String name;
       
       public SubCommand(String name, String usage, String permission) {
  8      this.name = name; this.usage = usage; this.permission = permission;
       } private final String usage; private final String permission;
       public String getName() {
 11      return this.name; }
 12    public String getUsage() { return this.usage; } public String getPermission() {
 13      return this.permission;
       }
       
       public abstract void execute(CommandSender paramCommandSender, String[] paramArrayOfString);
     }


