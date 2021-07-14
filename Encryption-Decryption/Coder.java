class Coder {
    private final CoderInterface coder;

    public Coder(CoderInterface coder) {
        this.coder = coder;
    }

    public String findEncoding(String type, String text, int key) {
        if (type.equals("enc")) {
            return coder.encode(text, key);
        }
        if (type.equals("dec")) {
            return coder.decode(text, key);
        }
        return null;
    }
}

class CoderFactory {
    public static Coder make(String type) {
        switch (type) {
            case "shift":
                return new Coder(new ShiftCoderInterface());
            case "unicode":
                return new Coder(new UnicodeCoderInterface());
        }
        return null;
    }
}

interface CoderInterface {
    String encode(String text, int key);
    String decode(String text, int key);
}

class UnicodeCoderInterface implements CoderInterface {
    @Override
    public String encode(String text, int key) {
        StringBuilder x = new StringBuilder(text);
        for (int i = 0; i < x.length(); ++i) {
            x.setCharAt(i, (char) (x.charAt(i) + key));
        }
        return x.toString();
    }

    @Override
    public String decode(String text, int key) {
        StringBuilder x = new StringBuilder(text);
        for (int i = 0; i < x.length(); ++i) {
            x.setCharAt(i, (char) (x.charAt(i) - key));
        }
        return x.toString();
    }
}

class ShiftCoderInterface implements CoderInterface {
    @Override
    public String encode(String text, int key) {
        StringBuilder x = new StringBuilder(text);
        for (int i = 0; i < x.length(); ++i) {
            char new_ch = x.charAt(i);
            if (new_ch >= 'a' && new_ch <= 'z') {
                new_ch += + key;
                if (new_ch > 'z') new_ch -= 26;
            }
            if (new_ch >= 'A' && new_ch <= 'Z') {
                new_ch += key;
                if (new_ch > 'Z') new_ch -= 26;
            }
            x.setCharAt(i, new_ch);
        }
        return x.toString();
    }

    @Override
    public String decode(String text, int key) {
        StringBuilder x = new StringBuilder(text);
        for (int i = 0; i < x.length(); ++i) {
            char new_ch = x.charAt(i);
            if (new_ch >= 'a' && new_ch <= 'z') {
                new_ch -= key;
                if (new_ch < 'a') new_ch += 26;
            }
            if (new_ch >= 'A' && new_ch <= 'Z') {
                new_ch -= key;
                if (new_ch < 'A') new_ch += 26;
            }
            x.setCharAt(i, new_ch);
        }
        return x.toString();
    }
}
