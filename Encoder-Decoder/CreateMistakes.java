import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class CreateMistakes {
    static Random r = new Random();

    public static void send() {
        File file = new File("encoded.txt");
        int numBytes = (int) file.length();
        byte[] data = new byte[numBytes];

        ReadWrite.ReadFromFile("encoded.txt", data);

        for (int i = 0; i < numBytes; ++i) {
            int pos = r.nextInt(8);
            data[i] ^= (1 << pos);
        }

        ReadWrite.WriteToFile("received.txt", data);
    }
}
