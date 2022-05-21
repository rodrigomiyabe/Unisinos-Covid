package br.com.unisinos.estoquecovid.util;

public abstract class StringUtils {

    public static String formata(final String str, Object... params) {
        return String.format(str, params);
    }

}
