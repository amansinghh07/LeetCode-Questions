
import java.util.*;

class Solution {
    public String sortVowels(String s) {
        vow[] freqvow = new vow[5];
        char[] vowels = {'a', 'e', 'i', 'o', 'u'};

        int[] freq = new int[5];
        int[] first = new int[5];
        Arrays.fill(first, Integer.MAX_VALUE);

        // Count frequency and record first occurrence
        for (int i = 0; i < s.length(); i++) {
            int idx = "aeiou".indexOf(s.charAt(i));

            if (idx != -1) {
                freq[idx]++;
                first[idx] = Math.min(first[idx], i);
            }
        }

        // Create vowel objects
        List<vow> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            if (freq[i] > 0) {
                freqvow[i] = new vow(vowels[i], freq[i], first[i]);
                list.add(freqvow[i]);
            }
        }

        // Sort: frequency descending, first occurrence ascending
        list.sort((a, b) -> {
            if (a.freq != b.freq) {
                return Integer.compare(b.freq, a.freq);
            }
            return Integer.compare(a.first, b.first);
        });

        // Build sorted vowel sequence
        StringBuilder sortedVowels = new StringBuilder();

        for (vow v : list) {
            for (int i = 0; i < v.freq; i++) {
                sortedVowels.append(v.ch);
            }
        }

        // Replace only vowel positions
        char[] result = s.toCharArray();
        int j = 0;

        for (int i = 0; i < result.length; i++) {
            if ("aeiou".indexOf(result[i]) != -1) {
                result[i] = sortedVowels.charAt(j++);
            }
        }

        return new String(result);
    }
}

class vow {
    char ch;
    int freq;
    int first;

    public vow(char ch, int freq, int first) {
        this.ch = ch;
        this.freq = freq;
        this.first = first;
    }
}
