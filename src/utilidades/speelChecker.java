/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author 182220058
 */
public class speelChecker {
  public static Set<String> dictionary;
    
     private void loadDictionary() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\182220058\\Documents\\NetBeansProjects\\PI\\src\\utilidades\\pt.txt"));
        String word;
        while ((word = reader.readLine()) != null) {
            dictionary.add(word.toLowerCase());
        }
        reader.close();
    }

    

    public boolean checkSpelling(JTextField textArea,JTextArea resultArea) throws IOException {
        dictionary = new HashSet<>();
        loadDictionary();
        String text = textArea.getText();
        List<String> misspelledWords = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        String[] words = text.split("\\W+");
        for (String word : words) {
            if (!word.isEmpty() && !dictionary.contains(word.toLowerCase())) {
                misspelledWords.add(word);
                List<String> suggestions = getSuggestions(word.toLowerCase());
                result.append("Erros ortográficos: ").append(word).append("\nSugestões: ").append(String.join(", ", suggestions)).append("\n\n");
            }
        }

        if (misspelledWords.isEmpty()) {
            resultArea.setText("Todas as palavras estão corretas");
           return true;
        }
        
        resultArea.setText(result.toString());
        
        return false;
    }

    private List<String> getSuggestions(String word) {
        List<String> suggestions = new ArrayList<>();
        Map<String, Integer> wordDistances = new HashMap<>();

        for (String dictWord : dictionary) {
            int distance = levenshteinDistance(word, dictWord);
            wordDistances.put(dictWord, distance);
        }

        wordDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .limit(5)
                .forEach(entry -> suggestions.add(entry.getKey()));

        return suggestions;
    }

    private int levenshteinDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = Math.min(Math.min(
                            dp[i - 1][j] + 1, 
                            dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + (word1.charAt(i - 1) == word2.charAt(j - 1) ? 0 : 1));
                }
            }
        }

        return dp[word1.length()][word2.length()];
    }
}
