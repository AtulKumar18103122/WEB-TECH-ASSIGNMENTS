package com.company;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static class Edge {
        double cost;
        int from, to;

        public Edge(int from, int to, double cost) {
            this.to = to;
            this.from = from;
            this.cost = cost;
        }
    }
    public static double[] bellmanFord(Edge[] edges, int V, int start, int[] parent) {

        double[] dist = new double[V];
        java.util.Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;
        boolean flag = true;
        for (int v = 0; v < V - 1 && flag; v++) {
            flag = false;
            for (Edge edge : edges) {
                if (dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                    parent[edge.to] = edge.from;
                    flag = true;
                }
            }
        }

        flag = true;
        for (int v = 0; v < V - 1 && flag; v++) {
            flag = false;
            for (Edge edge : edges) {
                if (dist[edge.from] + edge.cost < dist[edge.to]) {
                    dist[edge.to] = Double.NEGATIVE_INFINITY;
                    flag = true;
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {

        int E , V, start, from, to, cost, dis;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter No of vertices in graph: ");
        V = scanner.nextInt();
        System.out.print("Enter No of Edges in graph: ");
        E = scanner.nextInt();

        Edge[] edges = new Edge[E];
        for(int i = 0; i<E; ++i)
        {
            System.out.print("Enter edge " + i+1 + " (from, to, cost) : ");
            from = scanner.nextInt();
            to = scanner.nextInt();
            cost = scanner.nextInt();
            edges[i] = new Edge(from, to, cost);
        }
        System.out.print("Enter source vertex: ");
        start = scanner.nextInt();
        System.out.print("Enter dest vertex: ");
        dis = scanner.nextInt();
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        double[] d = bellmanFord(edges, V, start, parent);
        if(d[dis] == Double.NEGATIVE_INFINITY )
            System.out.println("Negative cycle exist");
        else if(d[dis] != Double.POSITIVE_INFINITY) {
            System.out.println("\nPath from scr to dis : ");
            Stack stack = new Stack();
            int i = dis;
            while(parent[i]!=-1)
            {
                stack.push(i);
                i = parent[i];
            }
            stack.push(i);
            while(stack.size()>0){
                System.out.print(stack.pop() + " ");
            }
            System.out.println("\nhaving cost "+ d[dis]);
        }
        else
            System.out.println("Path doesn't exist");
    }
}