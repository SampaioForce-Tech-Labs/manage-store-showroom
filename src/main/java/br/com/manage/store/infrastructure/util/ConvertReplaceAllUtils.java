package br.com.manage.store.infrastructure.util;

public class ConvertReplaceAllUtils {

    public static String replaceAll(String value){
        return value
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("\\s+", "")
                .toUpperCase();
    }
}
