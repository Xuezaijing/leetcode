package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// no negative weight
public class Dijkstra {

    public static HashMap<Node,Integer> dijkstra1(Node head){
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(head,0);
        HashSet<Node> selectedNodes = new HashSet<>();

        Node minNode = getMinDistanceAndUnselectedNode(distanceMap,selectedNodes);
    }

    private static Node getMinDistanceAndUnselectedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> selectedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for(Map.Entry<Node,Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();

        }
    }
}
