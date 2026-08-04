class Solution {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEnd;
    }

    TrieNode root = new TrieNode();

    private void insert(String word) {
        TrieNode curr = root;

        for (char ch : word.toCharArray()) {
            int idx = ch - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }

            curr = curr.children[idx];
        }

        curr.isEnd = true;
    }

    private String search(String word) {

        TrieNode curr = root;
        StringBuilder prefix = new StringBuilder();

        for (char ch : word.toCharArray()) {

            int idx = ch - 'a';

            if (curr.children[idx] == null) {
                return word;
            }

            prefix.append(ch);
            curr = curr.children[idx];

            if (curr.isEnd) {
                return prefix.toString();
            }
        }

        return word;
    }

    public String replaceWords(List<String> dictionary, String sentence) {

        for (String word : dictionary) {
            insert(word);
        }

        StringBuilder ans = new StringBuilder();

        String[] words = sentence.split(" ");

        for (String word : words) {
            ans.append(search(word)).append(" ");
        }

        ans.setLength(ans.length() - 1);

        return ans.toString();
    }
}