package com.example.demo.Config;

public final class tempConst
{
    public enum CookieNames {
        sessionCookieName("sessionCookie"),
        persistentCookieName("persistentCookie");

        private final String name;
        CookieNames(String name) {this.name = name;}
        public String getName() {return name;}
    }
}
