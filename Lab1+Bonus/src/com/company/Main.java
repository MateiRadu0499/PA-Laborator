package com.company;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        //--------Compulsory--------
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1000000);
        int result = (n * 3 + 0b1010 + 0xFF) * 6;

        int index = result;
        while (index > 10) {
            int newResult = 0;

            while (index > 0) {
                newResult += (result % 10);
                index /= 10;
            }
            index = newResult;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[index]);

        //--------Optional--------
        boolean matrix[][] = optional(args);


        //--------Bonus--------
        int p = matrix.length;
        boolean visited[] = new boolean[p];
        int component = 0;

        for (int i = 0; i < p; i++) {
            System.out.println(visited[i]);
            if (visited[i] == false) {
                component++;
                System.out.print("Componenta nr. " + component + ": ");
                dfs(i, matrix, visited);
                System.out.println();
            }
        }

        if (component == 1) {
            System.out.println("Graful este conex.");
        } else {
            System.out.println("Graful nu este conex.");
        }
    }

    public static boolean[][] optional(String args[]) {
        long nanoStart = System.nanoTime();

        if (args.length < 3) {
            System.out.println("Input eronat. Hint: numar,numar,una sau mai multe litere.");
            System.exit(-1);
        }

        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        int m = args.length - 2;

        char[] alphabet = new char[m];
        for (int i = 0; i < m; i++) {
            alphabet[i] = args[i + 2].charAt(0);
        }

        String[] words = new String[n];
        System.out.println("Cuvintele generate sunt:");

        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int j = 0; j < k; j++) {
                int r = (int) (Math.random() * m);
                stringBuilder.append(alphabet[r]);
            }

            words[i] = stringBuilder.toString();
            System.out.println(words[i]);
        }

        boolean[][] matrix = new boolean[n][n];
        int neighbours[] = new int[n];
        int maxNeighbours = 0;
        int minNeighbours = n;

        System.out.println("Matricea:");

        for (int i = 0; i < n; i++) {
            HashMap<Character, Boolean> hashMap = new HashMap<Character, Boolean>();
            int count = 0;

            for (int x = 0; x < k; x++) {
                hashMap.put(words[i].charAt(x), true); // marcam literele folosite
            }

            for (int j = i + 1; j < n; j++) {
                for (int x = 0; x < k; x++) {
                    if (hashMap.containsKey(words[j].charAt(x)) == true) { // al i-lea si al j-lea cuvant au litere in comun
                        matrix[i][j] = true;
                        matrix[j][i] = true;
                        break;
                    }
                }
            }

            for (int j = 0; j < n; j++) { // linia curenta
                if (matrix[i][j] == true) {
                    count++;
                }
                if (n <= 30_000) {
                    if (matrix[i][j] == true) {
                        System.out.print(1);
                    } else {
                        System.out.print(0);
                    }
                }
            }
            System.out.println();

            neighbours[i] = count;

            if (count > maxNeighbours) maxNeighbours = count;
            if (count < minNeighbours) minNeighbours = count;
        }

        if (minNeighbours == maxNeighbours) {
            System.out.println("Toate cuvintele au un numar egal de vecini.");
        } else {
            System.out.println("Cuvintele cu cei mai multi vecini:");
            for (int i = 0; i < n; i++)
                if (neighbours[i] == maxNeighbours) System.out.println(words[i]);

            System.out.println("Cuvintele cu cei mai putini vecini:");
            for (int i = 0; i < n; i++)
                if (neighbours[i] == minNeighbours) System.out.println(words[i]);
        }

        if (n > 30_000) {
            long nanoEnd = System.nanoTime();
            System.out.println("Timp rulare: " + (nanoEnd - nanoStart) + " nanoseconds");
        }

        return matrix;
    }

    public static void dfs(int node, boolean matrix[][], boolean visited[]) {
        visited[node] = true;
        System.out.print(node + " ");

        for (int i = 0; i < matrix.length; i++) {
            if (visited[i] == false && matrix[i][node] == true) {
                dfs(i, matrix, visited);
            }
        }
    }
}
