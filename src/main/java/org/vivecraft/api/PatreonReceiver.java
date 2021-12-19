// Optifine only
//package org.vivecraft.api;
//
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//import net.minecraft.world.entity.player.Player;
//import net.optifine.Config;
//import net.optifine.http.FileDownloadThread;
//import org.vivecraft.render.PlayerModelController;
//
//public class PatreonReceiver
//{
//    private static final Object lock = new Object();
//    private static List<Player> queuedPlayers = new LinkedList<>();
//    private static Map<String, Integer> cache;
//    private static boolean downloadStarted;
//    private static boolean downloadFailed;
//
//    private static void fileDownloadFinished(String url, byte[] data, Throwable exception)
//    {
//        synchronized (lock)
//        {
//            if (data != null)
//            {
//                try
//                {
//                    HashMap<String, Integer> hashmap = new HashMap<>();
//                    String s = new String(data, StandardCharsets.UTF_8);
//                    String[] astring = s.split("\\r?\\n");
//
//                    for (String s1 : astring)
//                    {
//                        try
//                        {
//                            String[] astring1 = s1.split(":");
//                            int i = Integer.parseInt(astring1[1]);
//                            hashmap.put(astring1[0], i);
//
//                            for (Player player : queuedPlayers)
//                            {
//                                if (astring1[0].equalsIgnoreCase(player.getGameProfile().getName()))
//                                {
//                                    PlayerModelController.getInstance().setHMD(player.getUUID(), i);
//                                }
//                            }
//                        }
//                        catch (Exception exception1)
//                        {
//                            System.out.println("error with donors txt " + exception1.getMessage());
//                        }
//                    }
//
//                    cache = hashmap;
//                }
//                catch (Exception exception1)
//                {
//                    Config.dbg("Error parsing data: " + url + ", " + exception1.getClass().getName() + ": " + exception1.getMessage());
//                    downloadFailed = true;
//                }
//            }
//            else
//            {
//                downloadFailed = true;
//            }
//
//            queuedPlayers.clear();
//        }
//    }
//
//    public static void addPlayerInfo(Player p)
//    {
//        if (!downloadFailed)
//        {
//            synchronized (lock)
//            {
//                if (cache == null)
//                {
//                    queuedPlayers.add(p);
//                    PlayerModelController.getInstance().setHMD(p.getUUID(), 0);
//
//                    if (!downloadStarted)
//                    {
//                        downloadStarted = true;
//                        String s = "http://www.vivecraft.org/patreon/current.txt";
//                        FileDownloadThread filedownloadthread = new FileDownloadThread(s, PatreonReceiver::fileDownloadFinished);
//                        filedownloadthread.start();
//                    }
//                }
//                else
//                {
//                    PlayerModelController.getInstance().setHMD(p.getUUID(), cache.getOrDefault(p.getGameProfile().getName(), 0));
//                }
//            }
//        }
//    }
//}
