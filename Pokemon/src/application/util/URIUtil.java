package application.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URIUtil {
	
	public static int parseForIdInBeteewn(String uri) {
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(uri);
        String match = "";
        while(m.find()) {
            match = m.group();
            break;
        }
        return Integer.parseInt(match);
	}
	
	public static int parseForIdAtEnd(String uri) {
		try {
			Pattern p = Pattern.compile("\\d+");
			Matcher m = p.matcher(uri);
			String match = "";
			while(m.find()) {
				match = m.group();
			}
			return Integer.parseInt(match);	
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	public static int numbersInUrI(String uri) {
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(uri);
        int count = 0;
        while(m.find()) {
            count++;
        }
        return count;
	}
	
	public static boolean wordInPath(String uri, String word) {
		String[] words = uri.split("/");
		for(int i = 0; i < words.length; i++) {
			if(words[i].equals(word)) {
				return true;
			}
		}
        return false;
	}
	
	public static boolean wordsInPath(String uri, String... words) {
		for(int i = 0; i < words.length; i++) {
			if(!uri.contains(words[i])) {
				return false;
			}
		}
        return true;
	}
	
	public static int findInteger(String uri) {
		String[] words = uri.split("/");
		for(int i = 0; i < words.length; i++) {
			try {
				int res = Integer.parseInt(words[i]);
				return res;
			}
			catch (Exception e) {
				continue;
			}
		}
		
		return 0;
	}
	
	public static String getUri(String url) {
		String[] words = url.split("/");
        return "/" + words[words.length-1];
	}
}
