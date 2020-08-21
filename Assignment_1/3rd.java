package com.company;

import java.util.*;

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

    public static void printAllPath(List<Edge>[] graph, int start, int dis, ArrayList<Integer> temp, boolean[] visited) {
        temp.add(start);
        visited[start] = true;
        if (start == dis) {
            System.out.print("\nPath: ");
            for (int i : temp)
                System.out.print(i + " ");
            visited[start] = false;
            temp.remove(temp.size() - 1);
            return;
        }

        for (Edge i : graph[start]) {
            if (!visited[i.to]) {
                printAllPath(graph, i.to, dis, temp, visited);
            }
        }
        visited[start] = false;
        temp.remove(temp.size() - 1);
    }

    public static double[] bellmanFord(List<Edge>[] graph, int V, int start, int[] parent) {
        double[] dist = new double[V];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[start] = 0;
        for (int i = 0; i < V - 1; i++)
            for (List<Edge> edges : graph)
                for (Edge edge : edges)
                    if (dist[edge.from] + edge.cost < dist[edge.to]) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                        parent[edge.to] = edge.from;
                    }

        for (int i = 0; i < V - 1; i++)
            for (List<Edge> edges : graph)
                for (Edge edge : edges)
                    if (dist[edge.from] + edge.cost < dist[edge.to]) dist[edge.to] = Double.NEGATIVE_INFINITY;
        return dist;
    }

    public static void main(String[] args) {

        int E , V, start, from, to, cost, dis;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter No of vertices in graph: ");
        V = scanner.nextInt();
        System.out.print("Enter No of Edges in graph: ");
        E = scanner.nextInt();

        List<Edge>[] graph = new List[V];
        for (int i = 0; i < V; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i<E; ++i)
        {
            System.out.print("Enter edge " + i+1 + " (from, to, cost) : ");
            from = scanner.nextInt();
            to = scanner.nextInt();
            cost = scanner.nextInt();
            graph[from].add(new Edge(from, to, cost));
        }

        System.out.print("Enter source vertex: ");
        start = scanner.nextInt();
        System.out.print("Enter dest vertex: ");
        dis = scanner.nextInt();

        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        double[] d = bellmanFord(graph, V, start, parent);

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
            System.out.println("\nhaving cost "+ d[dis] +"\n\n");

            System.out.println("All path from src to dis : ");
            ArrayList<Integer> temp = new ArrayList<Integer>();
            boolean[] visited = new boolean[V];
            printAllPath(graph,start,dis,temp, visited);

        }
        else
            System.out.println("Path doesn't exist");
    }
}