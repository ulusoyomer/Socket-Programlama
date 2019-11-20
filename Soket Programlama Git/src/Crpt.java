
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author qq
 */
public class Crpt {
    
    static String alfabe = "abcdefghijklmnopqrstuvwxyzabcde";
    static HashMap<String, String> polybius = new HashMap<>();
    
    public static void SetPolybius() {
        int k = 11;
        char c = 'a';
        for (int i = 0; i < 26; i++) {
            if ((k - 1) % 5 == 0 && k != 11) {
                k += 5;
            }
            polybius.put(String.valueOf(c), String.valueOf(k));
            k++;
            c++;
            
        }
        polybius.put(" ","63");
        polybius.put(",","64");
    }
    
    public static String Ceasar(String message) {
        
        message = message.toLowerCase();
        char[] message_array = message.toCharArray();
        char[] message_array2 = message.toCharArray();
        char[] alfabe_array = alfabe.toCharArray();
        int k = 0;
        for (int i = 0; i < message_array.length; i++) {
            if (message_array[i] == 'ü') {
                message_array[i] = 'u';
            }
            if (message_array[i] == 'ı') {
                message_array[i] = 'i';
            }
            if (message_array[i] == 'ğ') {
                message_array[i] = 'g';
            }
            if (message_array[i] == 'ş') {
                message_array[i] = 's';
            }
            if (message_array[i] == 'ç') {
                message_array[i] = 'c';
            }
            if (message_array[i] == 'ö') {
                message_array[i] = 'o';
            }
        }
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < alfabe.length(); j++) {
                if (message_array[i] == alfabe_array[j]) {
                    message_array[i] = alfabe_array[j + 5];
                    break;
                }
            }
        }
        for (int i = 0; i < message_array2.length; i++) {
            if (message_array2.equals(' ')) {
                continue;
            }
            message_array2[i] = message_array[k];
            k++;
            
        }
        message = String.valueOf(message_array2);
        return message;
    }
    
    public static String encrypt_Ceasar(String message) {
        char[] message_array = message.toCharArray();
        char[] alfabe_array = alfabe.toCharArray();
        for (int i = 0; i < message.length(); i++) {
            for (int j = alfabe.length() - 1; j >= 0; j--) {
                if (message_array[i] == alfabe_array[j]) {
                    message_array[i] = alfabe_array[j - 5];
                    break;
                }
            }
        }
        message = String.valueOf(message_array);
        return message;
    }

    public static String Polybius(String message) {
        message = message.toLowerCase();
        char[] message_array = message.toCharArray();
        SetPolybius();
        for (int i = 0; i < message_array.length; i++) {
            if (message_array[i] == 'ü') {
                message_array[i] = 'u';
            }
            if (message_array[i] == 'ı') {
                message_array[i] = 'i';
            }
            if (message_array[i] == 'ğ') {
                message_array[i] = 'g';
            }
            if (message_array[i] == 'ş') {
                message_array[i] = 's';
            }
            if (message_array[i] == 'ç') {
                message_array[i] = 'c';
            }
            if (message_array[i] == 'ö') {
                message_array[i] = 'o';
            }
        }
        String a = null;
        for (int i = 0; i < message_array.length; i++) {
                if (i == 0) {
                    a = polybius.get(String.valueOf(message_array[i])) + ",";
                } else {
                    a += polybius.get(String.valueOf(message_array[i])) + ",";
                }
             
        }
        polybius.clear();
        message = a;
        return message;
    }
    
    public static String encrypt_Polybius(String message) {
        String[] message_array = message.split(",");
        String b = null;
        SetPolybius();
        for (int i = 0; i < message_array.length; i++) {
            for (String s : polybius.keySet()) {
                if (message_array[i].equals(polybius.get(s))) {
                    if (b == null) {
                        b = s;
                    } else {
                        b += s;
                    }
                }
            }
        }
        message = b;
        return message;
    }
    
    public static String Picket(String message) {
        int a = 0, t = 0;
        message = message.toLowerCase();
        message = message.replaceAll(" ", ",");
        char[] message_array = message.toCharArray();
        char[] message_array2 = new char[message.length()];
        char[] message_array3 = new char[message.length()];
        for (int i = 0; i < message_array.length; i++) {
            if (message_array[i] == 'ü') {
                message_array[i] = 'u';
            }
            if (message_array[i] == 'ı') {
                message_array[i] = 'i';
            }
            if (message_array[i] == 'ğ') {
                message_array[i] = 'g';
            }
            if (message_array[i] == 'ş') {
                message_array[i] = 's';
            }
            if (message_array[i] == 'ç') {
                message_array[i] = 'c';
            }
            if (message_array[i] == 'ö') {
                message_array[i] = 'o';
            }
        }
        for (int i = 0; i < message_array.length; i++) {
            if (i % 2 == 0) {
                message_array2[a] = message_array[i];
                a++;
            } else {
                message_array3[t] = message_array[i];
                t++;
            }
        }
        message = String.valueOf(message_array2) + " " + String.valueOf(message_array3);
        return message;
    }
    
    public static String encrypt_Picket(String message) {
        int a = 0, t = 0;
        String[] message_bolum = message.split(" ");
        char[] message_array = new char[message.length()];
        for (int i = 0; i < message.length()-1; i++) {
            if (i % 2 == 0) {
                message_array[i] = message_bolum[0].charAt(a);
                a++;
            }
            else{
                message_array[i] = message_bolum[1].charAt(t);
                t++;
            }
        }
        message = String.valueOf(message_array);
        message = message.replaceAll(",", " ");
        return message;
    }
  
}
