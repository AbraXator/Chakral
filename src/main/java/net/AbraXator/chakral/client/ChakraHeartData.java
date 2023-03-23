package net.AbraXator.chakral.client;

public class ChakraHeartData {
    private static float health;
    private static boolean enabled;
    private static boolean blink;

    public static void setHealth(float health){
        ChakraHeartData.health = health;
    }

    public static float getHealth(){
        return health;
    }

    public static void setEnabled(boolean b){
        ChakraHeartData.enabled = b;
    }

    public static boolean getEnabled(){
        return enabled;
    }

    public static boolean isBlink() {
        return blink;
    }

    public static void setBlink(boolean blink) {
        ChakraHeartData.blink = blink;
    }
}
