import java.util.List;

class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String value = null;
    }

    public String evaluate(String s, List<List<String>> knowledge) {
        TrieNode root = new TrieNode();
        
        for (List<String> pair : knowledge) {
            String key = pair.get(0);
            String val = pair.get(1);
            TrieNode curr = root;
            
            int len = key.length();
            for (int i = 0; i < len; i++) {
                int idx = key.charAt(i) - 'a';
                if (curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
            }
            curr.value = val; 
        }
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                i++; 
                TrieNode curr = root;
                
                while (s.charAt(i) != ')') {
                    if (curr != null) {
                        curr = curr.children[s.charAt(i) - 'a'];
                    }
                    i++;
                }
                
                
                if (curr != null && curr.value != null) {
                    sb.append(curr.value);
                } else {
                    sb.append('?');
                }
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}
