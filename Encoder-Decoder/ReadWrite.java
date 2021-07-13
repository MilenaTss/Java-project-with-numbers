import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ReadWrite {
    public static void ReadFromFile(String file, byte[] data) {
        try (FileInputStream inputStream = new FileInputStream(file)) {
            inputStream.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void WriteToFile(String file, byte[] data) {
        try (OutputStream outputStream = new FileOutputStream(file, false)) {
            outputStream.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
