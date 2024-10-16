package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        int[][] adjMatrix = {
                {0, 1, 0, 1, 0, 1},
                {0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0}
        };

        findParallelLevels(adjMatrix);
    }


    public static void findParallelLevels(int[][] adjMatrix) {
        int n = adjMatrix.length;
        int[] inDegree = new int[n]; // Масив для підрахунку кількості вхідних ребер для кожної вершини

        // Обчислення вхідних ступенів для кожної вершини
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (adjMatrix[j][i] == 1) {
                    inDegree[i]++;
                }
            }
        }

        List<List<Integer>> levels = new ArrayList<>(); // Список для зберігання ярусів
        Queue<Integer> currentLevel = new LinkedList<>(); // Поточний ярус S1

        // Заносимо вершини з нульовим вхідним ступенем у початковий ярус
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                currentLevel.offer(i);
            }
        }

        // Основний цикл для знаходження всіх ярусів
        while (!currentLevel.isEmpty()) {
            List<Integer> nextLevel = new ArrayList<>(); // Наступний ярус S2
            List<Integer> currentLayer = new ArrayList<>(currentLevel); // Вершини поточного ярусу
            levels.add(currentLayer);

            // Обробляємо кожну вершину поточного ярусу
            while (!currentLevel.isEmpty()) {
                int node = currentLevel.poll();

                // Для кожної суміжної вершини зменшуємо її вхідний ступінь
                for (int i = 0; i < n; i++) {
                    if (adjMatrix[node][i] == 1) {
                        inDegree[i]--;
                        // Якщо вхідний ступінь вершини став 0, додаємо її до наступного ярусу
                        if (inDegree[i] == 0) {
                            nextLevel.add(i);
                        }
                    }
                }
            }
            // Переходимо до наступного ярусу
            currentLevel.addAll(nextLevel);
        }

        // Виведення результату
        System.out.println("Кількість ярусів: " + levels.size());
        for (int i = 0; i < levels.size(); i++) {
            System.out.println("Ярус " + (i + 1) + ": " + levels.get(i));
        }
    }
}


