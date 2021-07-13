public class Bits {
    public static int getBit(byte b, int index) {
        return (b & (1 << (7 - index))) == 0 ? 0 : 1;
    }

    public static byte setBit(byte b, int bit, int index) {
        if (bit == 0) {
            if (getBit(b, index) == 0) {
                b = (byte) (b ^ (1 << (7 - index)));
            }
            return (byte) (b ^ (1 << (7 - index)));
        }
        return (byte) (b | (bit << (7 - index)));
    }
}
