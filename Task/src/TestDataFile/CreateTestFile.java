package TestDataFile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateTestFile {
    public static void main(String[] args)
    {
        String fileName = "Test File Finance.txt";
        String filePath = "src/" + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            Random random = new Random();
            int minWords = 9;
            int maxWords = 19;
            String finance = "finance";
            String Finance = "Finance";

            for (int i = 1; i <= 155; i++) {
                StringBuilder lineBuilder = new StringBuilder();
                int numWords = random.nextInt(maxWords - minWords + 1) + minWords;
                int financeIndex = -1;
                if (i == 1) {
                    financeIndex = random.nextInt(numWords);
                }
                if (i > 5 && i % 5 == 0) {
                    financeIndex = random.nextInt(numWords);
                }
                for (int j = 1; j <= numWords; j++) {
                    boolean isFinance = false;
                    if (financeIndex == j - 1) {
                        lineBuilder.append(Finance);
                        isFinance = true;
                    } else if (i > 5 && i % 5 == 0 && financeIndex == -1 && j == numWords - 1) {
                        lineBuilder.append(finance);
                        isFinance = true;
                    } else {
                        int wordLength = random.nextInt(10) + 6;
                        StringBuilder wordBuilder = new StringBuilder();
                        for (int k = 1; k <= wordLength; k++) {
                            char c = (char) (random.nextInt(26) + 'a');
                            wordBuilder.append(c);
                        }
                        if (j == 1) {
                            wordBuilder.setCharAt(0, Character.toUpperCase(wordBuilder.charAt(0)));
                        }
                        lineBuilder.append(wordBuilder.toString());
                    }
                    if (j < numWords) {
                        if (isFinance) {
                            lineBuilder.append(" ");
                        } else {
                            int separator = random.nextInt(2);
                            if (separator == 0) {
                                lineBuilder.append(" ");
                            } else {
                                lineBuilder.append("\t");
                            }
                        }
                    }
                }
                lineBuilder.append("\r");
                writer.write(lineBuilder.toString());
            }
            writer.write("<EOF>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}