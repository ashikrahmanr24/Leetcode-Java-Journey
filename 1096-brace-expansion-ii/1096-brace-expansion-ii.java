import java.util.*;

class Solution {
    private int ptr;

    public List<String> braceExpansionII(String expression) {
        this.ptr = 0;
        Set<String> resultSet = parseExpr(expression);

        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }
    private Set<String> parseExpr(String s) {
        Set<String> res = new HashSet<>();
        while (ptr < s.length()) {
            Set<String> termSet = parseTerm(s);
            res.addAll(termSet);
            if (ptr < s.length() && s.charAt(ptr) == ',') {
                ptr++; 
            } else {
                break;
            }
        }
        return res;
    }

    private Set<String> parseTerm(String s) {
        Set<String> res = new HashSet<>();
        res.add(""); 
        
        while (ptr < s.length() && (Character.isLetter(s.charAt(ptr)) || s.charAt(ptr) == '{')) {
            Set<String> factorSet = parseFactor(s);
            Set<String> newRes = new HashSet<>();
            for (String r : res) {
                for (String f : factorSet) {
                    newRes.add(r + f);
                }
            }
            res = newRes;
        }
        return res;
    }

    private Set<String> parseFactor(String s) {
        Set<String> res = new HashSet<>();
        if (Character.isLetter(s.charAt(ptr))) {
            res.add(String.valueOf(s.charAt(ptr)));
            ptr++;
        } else if (s.charAt(ptr) == '{') {
            ptr++; 
            res = parseExpr(s);
            ptr++; 
        }
        return res;
    }
}
