import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LCSObject {
    private List<Integer> lineIds = new ArrayList<>(); //Holds line ids
    private List<String> LCSseq; //Token Sequence

    public LCSObject(List<String> seq, int lineId) {
        LCSseq = seq;
        lineIds.add(lineId);
    }

    public List<String> getLCSseq() {
        return LCSseq;
    }

    public List<Integer> getLineIds() {
        return lineIds;
    }

    //Get the length of the LCS between sequences
    public int getLCS(List<String> seq) {
        int count = 0;

        //Loop through current sequence using the simple loop approach described in the paper
        int lastMatch = -1;
        for (String token : LCSseq) {
            if (token.equals("*")) {
                continue;
            }

            for (int j = lastMatch + 1; j < seq.size(); j++) {
                if (token.equals(seq.get(j))) {
                    lastMatch = j;
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    //Insert a line into the LCSObject
    public void insert(List<String> seq, int lineId) {
        lineIds.add(lineId);
        String temp = "";

        //Create the new sequence by looping through it
        int lastMatch = -1;
        boolean placeholder = false; //Decides whether or not to add a * depending if there is already one preceding or not
        for (String token : LCSseq) {
            if (token.equals("*")) {
                if (!placeholder) {
                    temp = temp + "* ";
                }
                placeholder = true;
                continue;
            }

            for (int j = lastMatch + 1; j < seq.size(); j++) {
                if( token.equals(seq.get(j))) {
                    placeholder = false;
                    temp = temp + token + " ";
                    lastMatch = j;
                    break;
                } else if (!placeholder) {
                    temp = temp + "* ";
                    placeholder = true;
                }
            }
        }

        //Set sequence based of the common sequence found
        LCSseq = Arrays.asList(temp.trim().split("[\\s]+"));
    }

    //Length for pruning
    public int length() {
        return LCSseq.size();
    }

    //Count of lineIds in this LCSObject
    public int count() {
        return lineIds.size();
    }

    //To String method for testing
    public String toString() {
        String temp = "";

        for (String s : LCSseq) {
            temp = temp + s + " ";
        }

        temp = temp + "\n\t\t{";

        for (int i : lineIds) {
            temp = temp + i + ", ";
        }

        return temp.substring(0, temp.length() - 2) + "}";
    }
}