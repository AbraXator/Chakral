package net.AbraXator.chakral.client;

public class ChakraHeartData {
    private static float health;
    private static boolean enabled;

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
}
