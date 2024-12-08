public class Huntingtons {
    // Возвращает максимальное количество последовательных повторов CAG в строке ДНК.
    public static int maxRepeats(String dna) {
        int count = 0;
        int max = 0;
        for (int i = 0; i < dna.length() - 2; i++) {
            if ((dna.substring(i, i + 3)).equals("CAG")) {
                count = count + 1;
                if ((i >= dna.length() - 5) || ((!(dna.substring(i + 3, i + 6)).equals(
                        "CAG")))) {
                    if (count > max) {
                        max = count;
                    }
                    count = 0;
                }
            }

        }
        return max;
    }

    // Возвращает копию s, из которой удалены все пробелы (пробелы, табуляции и новые строки).
    public static String removeWhitespace(String s) {
        String s1 = s.replace(" ", "");
        String s2 = s1.replace("\t", "");
        String s3 = s2.replaceAll("\\n|\\r\\n", "");
        return s3;
    }

    // Возвращает один из этих диагнозов, соответствующий максимальному количеству повторов:
    public static String diagnose(int maxRepeats) {
        if ((maxRepeats >= 10) && (maxRepeats <= 35)) return "normal";
        if ((maxRepeats >= 36) && (maxRepeats <= 39)) return "high risk";
        if ((maxRepeats >= 40) && (maxRepeats <= 180)) return "Huntington's";
        return "not human";
    }

    public static void main(String[] args) {
        In in = new In(args[0]); // берем имя файла из командной строки
        String s = in.readAll();  // записываем в s содержимое файла
        // StdOut.println(removeWhitespace(s));
        StdOut.println("max repeats = " + maxRepeats(removeWhitespace(s)));
        StdOut.println(diagnose(maxRepeats(removeWhitespace(s))));
        in.close();  // закрывакт входной поток
    }
}
