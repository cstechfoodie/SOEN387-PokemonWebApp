package application.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testMethod {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher("/Poke/Player/6/sfsd");
        while(m.find()) {

            System.out.println(m.group());
        }
	}

}
