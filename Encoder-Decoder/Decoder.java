import java.io.File;

class Decoder {
    private static byte decodeBytes(byte input) {
        int i1, i2, i4;
        int res = 0;
        i1 = Bits.getBit(input, 2) ^ Bits.getBit(input, 4) ^ Bits.getBit(input, 6);
        i2 = Bits.getBit(input, 2) ^ Bits.getBit(input, 5) ^ Bits.getBit(input, 6);
        i4 = Bits.getBit(input, 4) ^ Bits.getBit(input, 5) ^ Bits.getBit(input, 6);
        if (i1 != Bits.getBit(input, 0)) res += 1;
        if (i2 != Bits.getBit(input, 1)) res += 2;
        if (i4 != Bits.getBit(input, 3)) res += 4;

        input = Bits.setBit(input, Bits.getBit(input, res - 1) ^ 1, res - 1);
        return input;
    }

    public static void decode() {
        File file = new File("received.txt");
        int numBytes = (int) file.length();
        byte[] data = new byte[numBytes];
        byte[] out_data = new byte[numBytes / 2];

        ReadWrite.ReadFromFile("received.txt", data);

        for (int i = 0; i < data.length; ++i) {
            data[i] = decodeBytes(data[i]);

            int ind = i % 2 == 0 ? 0 : 4;
            out_data[i / 2] = Bits.setBit(out_data[i / 2], Bits.getBit(data[i], 2), ind);
            out_data[i / 2] = Bits.setBit(out_data[i / 2], Bits.getBit(data[i], 4), 1 + ind);
            out_data[i / 2] = Bits.setBit(out_data[i / 2], Bits.getBit(data[i], 5), 2 + ind);
            out_data[i / 2] = Bits.setBit(out_data[i / 2], Bits.getBit(data[i], 6), 3 + ind);
        }

        ReadWrite.WriteToFile("decoded.txt", out_data);
    }
}