import java.io.File;

class Encoder {
    public static void encode() {
        File file = new File("send.txt");

        int numBytes = (int) file.length();
        int numOutBytes = numBytes * 2;

        byte[] in_data = new byte[numBytes];
        byte[] out_data = new byte[numOutBytes];

        ReadWrite.ReadFromFile("send.txt", in_data);

        for (int i = 0; i < numOutBytes; ++i) {
            out_data[i] = 0;
            int ind = i % 2 == 0 ? 0 : 4;
            out_data[i] = Bits.setBit(out_data[i], Bits.getBit(in_data[i / 2], ind), 2);
            out_data[i] = Bits.setBit(out_data[i], Bits.getBit(in_data[i / 2], ind + 1), 4);
            out_data[i] = Bits.setBit(out_data[i], Bits.getBit(in_data[i / 2], ind + 2), 5);
            out_data[i] = Bits.setBit(out_data[i], Bits.getBit(in_data[i / 2], ind + 3), 6);
            int i1 = Bits.getBit(out_data[i], 2) ^ Bits.getBit(out_data[i], 4) ^ Bits.getBit(out_data[i], 6);
            out_data[i] = Bits.setBit(out_data[i], i1, 0);
            int i2 = Bits.getBit(out_data[i], 2) ^ Bits.getBit(out_data[i], 5) ^ Bits.getBit(out_data[i], 6);
            out_data[i] = Bits.setBit(out_data[i], i2, 1);
            int i3 = Bits.getBit(out_data[i], 4) ^ Bits.getBit(out_data[i], 5) ^ Bits.getBit(out_data[i], 6);
            out_data[i] = Bits.setBit(out_data[i], i3, 3);
        }

        ReadWrite.WriteToFile("encoded.txt", out_data);
    }
}