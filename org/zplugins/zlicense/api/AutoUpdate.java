      package org.zplugins.zlicense.api;
      
      import java.io.DataInputStream;
      import java.io.DataOutputStream;
      import java.io.IOException;
      import java.net.HttpURLConnection;
      import java.net.URL;
      import org.bukkit.Bukkit;
      import org.bukkit.plugin.Plugin;
      import org.bukkit.plugin.java.JavaPlugin;
      import org.json.simple.JSONObject;
      import org.json.simple.parser.JSONParser;
      import org.json.simple.parser.ParseException;
      
      
      
      
      
      
      public class AutoUpdate
      {
  22    private UpdateResult result = UpdateResult.DISABLED;
        private String version;
        private Plugin plugin;
  25    private String message = null;
  26    private String pluginMessage = null; private boolean updateAvailable = false;
  27    private String updateMessage = null;
        public static AutoUpdate instance;
        
        public enum UpdateResult {
  31      NO_UPDATE,
  32      DISABLED,
  33      UPDATE_AVAILABLE; }
        
        public AutoUpdate(JavaPlugin plugin, final String id) {
  36      this.plugin = (Plugin)plugin;
  37      Bukkit.getScheduler().runTaskAsynchronously((Plugin)plugin, new Runnable()
              {
                public void run() {
  40              AutoUpdate.this.doCheck(id);
                }
              });
        }
        
        public void doCheck(String id) {
  46      String data = null;
  47      String url = "https://apizplugins.sp3ctrostorm.repl.co/?id=" + id;
  48      JSONParser jsonParser = new JSONParser();
          try {
  50        JSONObject obj = (JSONObject)jsonParser.parse(data);
  51        if (obj.get("version") != null) {
  52          String newestVersion = (String)obj.get("version");
  53          String currentVersion = this.plugin.getDescription().getVersion().replaceAll("-SNAPSHOT-", ".");
  54          if (newestVersion.equals(currentVersion)) {
                return;
              }
  57          Bukkit.getConsoleSender().sendMessage("§aTem um update disponível!");
            }
          
  60      } catch (ParseException e) {
  61        e.printStackTrace();
          } 
  63      Bukkit.getScheduler().runTask(this.plugin, new Runnable()
              {
                public void run() {
  66              AutoUpdate.this.handleResult();
                }
              });
        }
        
        public String getVersion() {
  72      return this.version;
        }
        
        public String doCurl(String urlString) throws IOException {
  76      URL url = new URL(urlString);
  77      HttpURLConnection con = (HttpURLConnection)url.openConnection();
  78      con.setRequestMethod("POST");
  79      con.setInstanceFollowRedirects(true);
  80      con.setDoOutput(true);
  81      con.setDoInput(true);
  82      DataOutputStream output = new DataOutputStream(con.getOutputStream());
  83      output.close();
  84      DataInputStream input = new DataInputStream(con.getInputStream());
          
  86      StringBuilder resultBuf = new StringBuilder(); int c;
  87      while ((c = input.read()) != -1) {
  88        resultBuf.append((char)c);
          }
  90      input.close();
  91      return resultBuf.toString();
        }
        
        public String getMessage() {
  95      return this.message;
        }
        
        public void handleResult() {
  99      if (getMessage() != null) {
 100        this.pluginMessage = getMessage();
          }
          
 103      switch (this.result) {
            
            default:
 106          this.updateAvailable = false;
 107          this.updateMessage = "No update was found, you are running the latest version.";
              break;
            case DISABLED:
 110          this.updateAvailable = false;
 111          this.updateMessage = "You currently have update checks disabled";
              break;
            case UPDATE_AVAILABLE:
 114          this.updateAvailable = true;
 115          this.updateMessage = "An update for " + this.plugin.getDescription().getName() + " is available, new version is " + getVersion() + ". Your installed version is " + this.plugin.getDescription().getVersion() + ".\nPlease update to the latest version :)";
              break;
          } 
          
 119      this.plugin.getLogger().info(this.updateMessage);
        }
        
        public static AutoUpdate getInstance() {
 123      return instance;
        }
      }
