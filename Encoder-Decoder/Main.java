import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.print("Write a mode: ");
        Scanner s = new Scanner(System.in);

        String choice = s.next();
        if (choice.equals("encode")) {
            Encoder.encode();
        }
        if (choice.equals("decode")) {
            Decoder.decode();
        }
        if (choice.equals("send")) {
            CreateMistakes.send();
        }
    }
}