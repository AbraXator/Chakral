package net.AbraXator.chakral.chakra;

public class ChakraHelper {

    @FunctionalInterface
    public interface ChakraVisitor{
        void accept(Chakra chakra);
    }
}
