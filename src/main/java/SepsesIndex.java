import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class SepsesIndex {
    private static Map<Integer, List<String>> entryVariables = new HashMap<>();

    public static void main(String[] args) {
        LCSMap map = new LCSMap();


        File f = new File("auth.log");
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line;
            while ((line = br.readLine()) != null) {
                map.insert(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(f));

            String line;
            int lineId = 0;
            while ((line = br.readLine()) != null) {
                for (LCSObject obj : map.getLCSObjects()) {
                    if (obj.getLineIds().contains(lineId)) {
                        foo(lineId, obj, line);
                    }
                }
                lineId++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Test Results:");
        System.out.println(map.toString());
        System.out.println("Entry variables");
    }

    private static void foo(int lineId, LCSObject obj, String line) {
        List<String> tokens = new ArrayList<>(Arrays.asList(line.trim().split("[\\s]+")));
        tokens.subList(0, 4).clear();
        entryVariables.put(lineId, tokens.stream()
                .filter(variable -> !obj.getLCSseq().contains(variable))
                .collect(Collectors.toList()));
    }

}