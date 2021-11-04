     package org.zplugins.zlicense.api;
     
     import java.net.URL;
     import java.net.URLConnection;
     import java.util.Scanner;
     
     public class API {
       private static final String SITE = "https://zplugins.org/api/GetPlugin.php?";
       
       public PluginActivationStatus test(String plugin, String key, String ip) {
         try {
 12        String link = String.valueOf("https://zplugins.org/api/GetPlugin.php?") + "key=" + key + "&plugin_id=" + plugin + "&ip=" + ip;
 13        URLConnection connect = (new URL(link)).openConnection();
 14        connect.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
 15        Scanner scan = new Scanner(connect.getInputStream());
 16        StringBuilder b = new StringBuilder();
 17        while (scan.hasNext()) {
 18          String text = scan.next();
 19          b.append(text);
           } 
 21        scan.close();
 22        if (b.toString().isEmpty())
 23          return PluginActivationStatus.PLUGIN_ACTIVATED; 
 24        return PluginActivationStatus.valueOf(b.toString().toUpperCase().replace(" ", "_"));
 25      } catch (Exception e) {
 26        e.printStackTrace();
 27        return PluginActivationStatus.PLUGIN_EXPIRED;
         } 
       }
       
       public enum PluginActivationStatus {
 32      INVALID_KEY("§c[zLicense] §fNão foi encontrada esta licença no sistema."),
 33      WRONG_KEY("§c[zLicense] §fEsta licença não bate com a do sistema."),
 34      KEY_TO_WRONG_PLUGIN("§c[zLicense] §fLicença usada não para este plugin."),
 35      KEY_TO_WRONG_OWNER("§c[zLicense] §fLicença usada não para este dono."),
 36      INVALID_IP("§c[zLicense] §fIP usado nao corresponde a licença"),
 37      INVALID_PORT("§c[zLicense] §fPorta nao corresponde a da licença."),
 38      PLUGIN_EXPIRED("§c[zLicense] §fEste plugin expirou."),
 39      PLUGIN_ACTIVATED("§a[zLicense] §fPlugin ativado com sucesso.", true);
         
         private String message;
         
         private boolean active;
         
         PluginActivationStatus(String message) {
 46        setMessage(message);
         }
         
         PluginActivationStatus(String message, boolean active) {
 50        setMessage(message);
 51        setActive(active);
         }
         
         public String getMessage() {
 55        return this.message;
         }
         
         public void setMessage(String message) {
 59        this.message = message;
         }
         
         public boolean isActive() {
 63        return this.active;
         }
         
         public void setActive(boolean active) {
 67        this.active = active;
         }
       }
     }


 