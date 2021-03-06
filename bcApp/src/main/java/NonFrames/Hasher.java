/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package NonFrames;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/**
 *
 * @author xavyr
 */
public class Hasher {
    public static String hashString(String input){
        try{
            MessageDigest complete = MessageDigest.getInstance("MD5");
            complete.update(input.getBytes("UTF-8"), 0, input.getBytes("UTF-8").length);
            
            byte[] b =  complete.digest();
            
            String result = "";
        
            for(int i = 0; i < b.length; i++){
                result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
            }

            return result;
            //return new String(MessageDigest.getInstance("MD5").digest(input.getBytes("UTF-8")), "UTF-8");
        } catch(Exception e){
            return "";
        }
    }
    
    public static String mergeHashes(String hash1, String hash2){
       String input = hash1 + hash2;
       return hashString(input);
    }
    
    private static byte[] createChecksum(File file) throws Exception{
        InputStream fis = new FileInputStream(file);
        
        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance("MD5");
        int numRead;
        do{
            numRead = fis.read(buffer);
            if(numRead > 0){
                complete.update(buffer, 0, numRead);
            }
        } while(numRead != -1);
        
        fis.close();
        return complete.digest();
    }
    
    public static String getMD5Checksum(File file) throws Exception{
        byte[] b = createChecksum(file);
        String result = "";
        
        for(int i = 0; i < b.length; i++){
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        
        return result;
    }
}
