import java.util.*;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    enum Properties {
        BUZZ {
            public boolean check(long number) {
                return number % 10 == 7 || number % 7 == 0;
            }
        },
        DUCK{
            public boolean check(long number) {
                return String.valueOf(number).contains("0");
            }
        },
        PALINDROMIC {
            public boolean check(long number) {
                String input = String.valueOf(number);
                StringBuilder firstHalf = new StringBuilder(input.substring(0, input.length() / 2));
                return firstHalf.reverse().toString().equals(input.substring((input.length() + 1) / 2));
            }
        },
        GAPFUL {
            public boolean check(long number) {
                String input = String.valueOf(number);
                int sec = (input.charAt(0) - '0') * 10 + input.charAt(input.length() - 1) - '0';
                return input.length() > 2 && number % sec == 0;
            }
        },
        SPY {
            public boolean check(long number) {
                long sum = 0, production = 1;
                while (number > 0) {
                    sum += number % 10;
                    production *= number % 10;
                    number /= 10;
                }
                return sum == production;
            }
        },
        SQUARE {
            public boolean check(long number) {
                int s = (int) Math.sqrt(number);
                return (long) s * s == number;
            }
        },
        SUNNY {
            public boolean check(long number) {
                return Properties.SQUARE.check(number + 1);
            }
        },
        JUMPING {
            public boolean check(long number) {
                String num = String.valueOf(number);
                for (int i = 0; i < num.length() - 1; ++i) {
                    if (Math.abs(num.charAt(i) - num.charAt(i + 1)) != 1) {
                        return false;
                    }
                }
                return true;
            }
        },
        EVEN {
            public boolean check(long number) {
                return number % 2 == 0;
            }
        },
        ODD {
            public boolean check(long number) {
                return !Properties.EVEN.check(number);
            }
        },
        HAPPY {
            public boolean check(long number) {
                long inputVal = number;
                while (true) {
                    char[] digits = String.valueOf(number).toCharArray();
                    int new_num = 0;
                    for (var i : digits) {
                        new_num += (i - '0') * (i - '0');
                    }
                    if (new_num == 1) return true;
                    if (new_num == 4) return false;
                    number = new_num;
                }
            }
        },
        SAD {
            public boolean check(long number) {
                return !Properties.HAPPY.check(number);
            }
        };

        public abstract boolean check(long number);

    }

    public static void printInstructions() {
        System.out.print("Welcome to Amazing Numbers!\n\nSupported requests:\n");
        System.out.print("- enter a natural number to know its properties;\n");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.print("* the first parameter represents a starting number;\n");
        System.out.println("* the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.print("- separate the parameters with one space;\n- enter 0 to exit.\n\n");
    }

    public static void printProperties(long number) {
        System.out.printf("Properties of %d\n", number);
        for (var prop : Properties.values()) {
            System.out.printf("%12s: %s%n", prop.name(), prop.check(number));
        }
    }

    public static String getProperties(long number) {
        StringBuilder result_props = new StringBuilder();
        result_props.append(number).append(" is ");
        for (var prop : Properties.values()) {
            if (prop.check(number)) result_props.append(prop.name().toLowerCase()).append(", ");
        }
        return result_props.toString();
    }

    public static String getProperties(long n, long m, HashSet<Properties> good_props, HashSet<Properties> bad_props) {
        StringBuilder res = new StringBuilder();
        int num = 0;
        while (num < m) {
            boolean correct = true;
            for (var s : good_props) {
                if (!s.check(n)) correct = false;
            }
            for (var s : bad_props) {
                if (s.check(n)) correct = false;
            }
            if (correct) {
                res.append(getProperties(n));
                res.append("\n");
                num++;
            }
            n++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printInstructions();

        while (true) {
            System.out.print("Enter a request:\n\n");
            String input = scanner.nextLine();

            if (input.length() == 0) {
                printInstructions();
                continue;
            }

            Long first_num, second_num = 0L;
            boolean IsSecondNum = false;
            Vector<String> all_properties = null;
            HashSet<Properties> good_properties = new HashSet<>();
            HashSet<Properties> bad_properties = new HashSet<>();

            try {
                String[] parse_input = input.split(" ");
                first_num = Long.parseLong(parse_input[0]);
                if (parse_input.length > 1) {
                    IsSecondNum = true;
                    second_num = Long.parseLong(parse_input[1]);
                }
                if (parse_input.length > 2) {
                    all_properties = new Vector<>(Arrays.asList(parse_input).subList(2, parse_input.length));
                }
            } catch (NumberFormatException e) {
                System.out.print("The first parameter should be a natural number or zero.\n\n");
                continue;
            }


            if (first_num < 0) {
                System.out.println("The first parameter should be a natural number or zero.\n");
                continue;
            }

            if (first_num == 0) {
                System.out.println("Goodbye!");
                break;
            }

            if (IsSecondNum && second_num <= 0) {
                System.out.println("The second parameter should be a natural number.\n");
                continue;
            }

            if (all_properties != null) {
                HashSet<String> hashSet = new HashSet<>(Arrays.asList("EVEN", "ODD", "BUZZ", "DUCK", "PALINDROMIC",
                        "GAPFUL", "SPY", "SQUARE", "SUNNY", "JUMPING", "HAPPY", "SAD"));

                for (int i = 0; i < all_properties.size(); ++i) {
                    all_properties.setElementAt(all_properties.elementAt(i).toUpperCase(Locale.ROOT), i);
                }

                Vector<String> wrong = new Vector<>();

                for (int i = 0; i < all_properties.size(); ++i) {
                    boolean isBad = false;
                    StringBuilder element = new StringBuilder(all_properties.elementAt(i));
                    if (element.charAt(0) == '-') {element.deleteCharAt(0); isBad = true;}
                    if (!hashSet.contains(element.toString())) {
                        wrong.add(all_properties.elementAt(i));
                    } else {
                        if (isBad) bad_properties.add(Properties.valueOf(element.toString()));
                        else good_properties.add(Properties.valueOf(element.toString()));
                    }
                }

                if (wrong.size() > 0) {
                    if (wrong.size() == 1) {
                        System.out.printf("The property [%s] is wrong.)", wrong.elementAt(0));
                    } else {
                        System.out.print("The good_properties [");
                        for (int i = 0; i < wrong.size() - 1; ++i) {
                            System.out.print(wrong.elementAt(i) + ", ");
                        }
                        System.out.print(wrong.elementAt(wrong.size() - 1) + "] are wrong\n");
                    }
                    System.out.println("Available good_properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]\n");
                    continue;
                }

                boolean toContinue = false;
                Vector<Properties[]> bad = new Vector<>();
                bad.add(new Properties[]{Properties.EVEN, Properties.ODD});
                bad.add(new Properties[]{Properties.SQUARE, Properties.SUNNY});
                bad.add(new Properties[]{Properties.DUCK, Properties.SPY});

                for (var i : bad) {
                    if (good_properties.contains(i[0]) && good_properties.contains(i[1])) {
                        toContinue = true;
                        System.out.printf("The request contains mutually exclusive good_properties: [%s, %s]\n" +
                                "There are no numbers with these good_properties.", i[0].name(), i[1].name());
                    }
                    if (toContinue) break;
                    if (bad_properties.contains(i[0]) && bad_properties.contains(i[1])) {
                        toContinue = true;
                        System.out.printf("The request contains mutually exclusive good_properties: [-%s, -%s]\n" +
                                "There are no numbers with these good_properties.", i[0].name(), i[1].name());
                    }
                    if (toContinue) break;
                }


                for (var p : bad_properties) {
                    if (good_properties.contains(p)) {
                        toContinue = true;
                        System.out.printf("The request contains mutually exclusive properties: [-%s, %s]\n" +
                                "There are no numbers with these properties.", p.name(), p.name());
                    }
                }

                if (toContinue) continue;
            }


            if (!IsSecondNum) {
                printProperties(first_num);
            } else {
                if (all_properties == null) {
                    for (long i = first_num; i < first_num + second_num; ++i) {
                        System.out.println(getProperties(i));
                    }
                } else {
                    System.out.println(getProperties(first_num, second_num, good_properties, bad_properties));
                }
            }
            System.out.println();
        }
    }
}
