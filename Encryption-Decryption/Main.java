import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String choice = "enc";
    static String text = "";
    static int key = 0;
    static String file_to_read = "";
    static String file_to_write = "";
    static String type_of_coder = "shift";

    private static void setArguments(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            switch (args[i]) {
                case "-mode":
                    choice = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    text = args[i + 1];
                    break;
                case "-in":
                    file_to_read = args[i + 1];
                    break;
                case "-out":
                    file_to_write = args[i + 1];
                    break;
                case "-alg":
                    type_of_coder = args[i + 1];
            }
        }
    }

    private static void readFromFile(String nameOfFile) {
        File in = new File(file_to_read);
        try (Scanner file_s = new Scanner(in)) {
            text = file_s.nextLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(String nameOfFile, String res) {
        File out = new File(file_to_write);
        try (FileWriter writer = new FileWriter(out)) {
            writer.write(res);
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

    public static void main(String[] args) {
        setArguments(args);

        if (text.isEmpty() && file_to_read.isEmpty()) {
            System.out.print("Error");
        }

        if (text.isEmpty()  && !file_to_read.isEmpty()) {
            readFromFile(file_to_read);
        }

        Coder coder = CoderFactory.make(type_of_coder);

        assert coder != null;
        String res = coder.findEncoding(choice, text, key);

        if (!file_to_write.isEmpty()) {
            writeToFile(file_to_write, res);
        } else {
            System.out.println(res);
        }
    }
}