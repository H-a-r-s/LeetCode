class Solution {
    class Pair {
        char ch;
        int freq;

        Pair(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((d, e) -> e.freq - d.freq);
        if(a > 0)pq.add(new Pair('a', a));
        if(b > 0)pq.add(new Pair('b', b));
        if(c > 0)pq.add(new Pair('c', c));
        
        
        StringBuilder sb = new StringBuilder(); 
        while (!pq.isEmpty()) {
            Pair first = pq.poll();

            int len = sb.length();
            if (len >= 2 && sb.charAt(len - 1) == first.ch && sb.charAt(len - 2) == first.ch) {
                // If adding this character would make three in a row
                if (pq.isEmpty()) break; // no other choice
                Pair second = pq.poll();
                sb.append(second.ch);
                second.freq--;
                if (second.freq > 0) pq.add(second);
                pq.add(first); // put first back
            } else {
                sb.append(first.ch);
                first.freq--;
                if (first.freq > 0) pq.add(first);
            }
        }

        return sb.toString();
    }
}    