class Solution {
    public class Pair {
        char c;
        int freq;

        public Pair(char c, int freq) {
            this.c = c;
            this.freq = freq;
        }
    }

    public String reorganizeString(String s) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        for (int c : count) {
            if (c > (n + 1) / 2) return "";
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                pq.offer(new Pair((char) (i + 'a'), count[i]));
            }
        }
        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()){
            Pair curr = pq.poll();
            if(sb.length() == 0){
                sb.append(curr.c);
                curr.freq--;
                if(curr.freq > 0)pq.offer(curr);
            }
            else{
                char ch = sb.charAt(sb.length()-1);
                if(ch != curr.c){
                    sb.append(curr.c);
                    curr.freq--;
                    if(curr.freq > 0)pq.offer(curr);
                }
                else{
                    if(pq.isEmpty())return "";
                    else{
                        Pair next = pq.poll();
                        sb.append(next.c);
                        next.freq--;
                        if(next.freq > 0)pq.offer(next);
                        if(curr.freq > 0)pq.offer(curr);
                    }
                }
            }
        }
        return sb.toString();
    }
}