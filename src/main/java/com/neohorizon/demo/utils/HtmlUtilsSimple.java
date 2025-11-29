package com.neohorizon.demo.utils;

public class HtmlUtilsSimple {
    // Exemplo simples de sanitização — no mundo real prefira bibliotecas maduras
    public static String escape(String s) {
        if (s == null) return null;
        return s.replace("&", "&amp;")
        .replace("<", "&lt;")
        .replace(">", "&gt;")
        .replace("\"", "&quot;")
        .replace("'", "&#x27;");
    }
}