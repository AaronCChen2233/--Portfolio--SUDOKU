package MVVM.Parts.Model;

import Bootstrap.Tools.CSVReaderWriter;
import Bootstrap.Tools.RandomTools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SUDOKUGeneraterModel {
    public SUDOKUGeneraterModel() {

    }

    public static String[][] generate(int difficulty) {
        String[][] SUDOKUTable = new String[9][9];
        String[][] SUDOKUQuestionTable = new String[9][9];
        ArrayList<String> numberList = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        int difficultyPercent = 46 + (difficulty * 4);
        String choseNumber = "";
        int tryTime = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                numberList = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
                do {
                    if (numberList.size() != 0) {
                        choseNumber = numberList.get(RandomTools.getRandomIntbetween(numberList.size() - 1, 0));
                        /*chosen number remove*/
                        numberList.remove(choseNumber);
                    } else {
                        tryTime++;
                        /*if numberList.size() is 0 mean it try all number and all number couldn't allow so this line generate again*/
                        j = 0;
                        numberList = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
                        SUDOKUTable[i] = new String[9];

                        /*try too many times this SUDOKUTable generate fail regenerate again*/
                        /*maybe this isn't a good solution but at least now will leave Infinite loop */
                        if (tryTime > 15) {
                            i = 0;
                            j = 0;
                            tryTime = 0;
                            SUDOKUTable = new String[9][9];
                        }
                        continue;
                    }
                } while (!allowPutChecker(SUDOKUTable, i, j, choseNumber));

                /*after allowPutChecker put number*/
                SUDOKUTable[i][j] = choseNumber;
            }
            tryTime = 0;
        }

        ArrayList<String> list1;
        String csvPath = System.getProperty("user.dir") + "\\SUDOKUTable.csv";

        /*Generate the diagonal boolean array*/
        boolean[] Blanks = new boolean[81];
        for (int i = 0; i < 40; i++) {
            boolean isBlank = RandomTools.getRandomBooleanByPercent(difficultyPercent);
            Blanks[i] = isBlank;
            Blanks[(Blanks.length - 1) - i] = isBlank;
        }
        /*The center block*/
        Blanks[40] = RandomTools.getRandomBooleanByPercent(difficultyPercent);

        /*Take out numbers to become question*/
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SUDOKUQuestionTable[i][j] = Blanks[(i * 9) + j] ? "" : SUDOKUTable[i][j];
            }
        }

        /*Create file*/
        /*Write Question first*/
        String dataString = "Question\n";
        for (int i = 0; i < 9; i++) {
            list1 = new ArrayList<String>();
            Collections.addAll(list1, SUDOKUQuestionTable[i]);
            dataString += String.join(",", list1) + "\n";
        }
        dataString += "\n";

        /*Write Answer*/
        dataString += "Answer\n";
        for (int i = 0; i < 9; i++) {
            list1 = new ArrayList<String>();
            Collections.addAll(list1, SUDOKUTable[i]);
            dataString += String.join(",", list1) + "\n";
        }

        CSVReaderWriter.writer(csvPath, dataString);
        return SUDOKUQuestionTable;
    }

    public static boolean allowPutChecker(String[][] table, int checki, int checkj, String checkNumberString) {
        try {
            /*check Row and Col*/
            for (int j = 0; j < 8; j++) {
                /*Skip it self*/
                if (j == checkj) {
                    continue;
                }

                if (checkNumberString.equals(table[checki][j])) {
                    return false;
                }
            }

            for (int i = 0; i < 8; i++) {
                /*Skip it self*/
                if (i == checki) {
                    continue;
                }

                if (checkNumberString.equals(table[i][checkj])) {
                    return false;
                }
            }

            /*Check in SmallBlock*/
            int indexInSmallBlocki = (checki / 3);
            int indexInSmallBlockj = (checkj / 3);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    /*Skip it self*/
                    if ((indexInSmallBlocki * 3 + i) == checki && (indexInSmallBlockj * 3 + j) == checkj) {
                        continue;
                    }


                    if (checkNumberString.equals(table[indexInSmallBlocki * 3 + i][indexInSmallBlockj * 3 + j])) {
                        return false;
                    }
                }
            }
        } catch (Exception ex) {
            return false;
        }

        return true;
    }
}
