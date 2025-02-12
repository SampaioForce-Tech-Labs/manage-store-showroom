package br.com.manage.store.infrastructure.util;

import java.text.Normalizer;

public class ConvertReplaceAllUtils {

    public static String replaceAll(String value) {
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);
        return normalized.replaceAll("\\s+", "").replaceAll("\\p{M}", "").toUpperCase();
    }
}
