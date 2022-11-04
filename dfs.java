//package HW2;

import java.util.*;

/**
 * Depth First Search
 * Assignment: HW 2 CS577
 * Author: Jason Lee (jlee967@wisc.edu)
 * 
 */

 public class dfs
 {
    public static class graph
    {
        private List <String> vertexData;
        private LinkedHashMap <String, Vertex> vertices;

        public graph() // Graph constructor
        {
            vertexData = new ArrayList <String>();
            vertices = new LinkedHashMap <String, Vertex>();
        }

        public class Vertex
        {
            List <String> adjacent; 

            Vertex()
            {
                adjacent = new ArrayList <String>();
            }
        }

        private Vertex getVertex(String data) // Gets vertex from graph given a data string
        {
            return vertices.get(data);
        }

        public void addVertex(String v) // Adds a new vertex to the graph.
        {
            Vertex vert = new Vertex();
            vertices.put(v , vert);
            vertexData.add(v);
        }

        public void addEdge(String v1, String v2) // Adds v2 to v1, updates adjacent
        {
            if(v1 == null || v2 == null)
            {
                return;
            }

            Vertex vert1 = getVertex(v1);
            vert1.adjacent.add(v2);
        }

        public List <String> getAdjList(String v)
        {
            Vertex vert = getVertex(v);

            return vert.adjacent;
        }

        public List <String> getAllVert()
        {
            return vertexData;
        }
        
    }
    // Topological order so we can iterate through all the nodes
    private static void topologicalOrder(graph g1)
    {
        StringBuilder r = new StringBuilder(); 
        HashMap <String, String> visited = new HashMap <String, String>();
        List <String> vertices= g1.getAllVert();

        for(String vert : vertices)
        {
            if(!visited.containsKey(vert))
            {
                depthFirstSearch(g1, visited, vert, r);
            }
        }
        String output = r.toString();
        System.out.println(output.substring(0, output.length() - 1));

    }

    private static graph [] graphs;

    private static void readInput()
    {
        Scanner kb = new Scanner(System.in);

        int count = kb.nextInt(); // Reads in num
        graphs = new graph[count];

        for(int i = 0; i < count; i++)
        {
            graphs[i] = new graph();
            int nodeCount = kb.nextInt(); // Reads in number of nodes
            kb.nextLine();

            while(nodeCount > 0)
            {
                String l = kb.nextLine();
                String [] vertex = l.split(" ");

                if(vertex.length > 1)
                {
                    graphs[i].addVertex(vertex[0]);

                    for(int j = 1; j < vertex.length; j++)
                    {
                        graphs[i].addEdge(vertex[0], vertex[j]);
                    }
                }
                else
                {
                    graphs[i].addVertex(vertex[0]);
                }
                nodeCount--;
            }
        }
    }

    public static void depthFirstSearch(graph g1, HashMap <String, String> visited, String vert, StringBuilder r)
    {
        Stack <String> stk = new Stack <String>();

        stk.push(vert);
        while(!stk.isEmpty())
        {
            String str = stk.pop();

            if(!visited.containsKey(str))
            {
                r.append(str + " ");
                visited.put(str, "1");
            }

            List <String> adj = g1.getAdjList(str);
            
            for(int i = adj.size() - 1; i >= 0; i--)
            {
                String adja = adj.get(i);

                if(!visited.containsKey(adja))
                {
                    stk.push(adja);
                }
            }
        }
    }

    public static void main(String [] args)
    {
        readInput();

        for(graph g1 : graphs)
        {
            topologicalOrder(g1);
        }
    }
 }

 