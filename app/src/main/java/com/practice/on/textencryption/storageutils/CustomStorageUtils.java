package com.practice.on.textencryption.storageutils;

// Store data in static variables and can be accessed any where
public class CustomStorageUtils {
    public static String encryption_key = "";

    // Return encryption key
    public static String getEncryption_key() {
        return encryption_key;
    }
}
