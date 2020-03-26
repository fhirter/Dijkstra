from Network import Network
from Observable import Observable
from Node import Node


class Dijkstra(Observable):
    __network: Network

    __start: Node
    __destination: Node

    __unvisited_nodes: list

    __distance: int
    __route: list

    def __init__(self, network):
        super().__init__()
        self.__network = network
        self.__route = []

    def run(self, start: str, destination: str) -> None:
        nodes = self.__network.get_nodes()

        self.__start = nodes.get(start)
        self.__destination = nodes.get(destination)

        self.set_unvisited_nodes(list(nodes.values()))

        self.set_distances_to_infinite()
        self.__start.current_distance = 0

        while not self.is_destination_closest():
            current_node = self.get_closest_node(self.__unvisited_nodes)
            self.calc_distances_to_neighbours_and_set_smaller(current_node)
            self.__set_as_visited(current_node)

        self.__distance = self.__destination.current_distance

        self.calculate_route()

        self._notify(self, "success")

    def set_unvisited_nodes(self, nodes: list) -> None:
        for node in nodes:
            node.visited = False
        self.__unvisited_nodes = nodes

    def set_distances_to_infinite(self) -> None:
        for node in self.__unvisited_nodes:
            node.current_distance = 2**32

    def is_destination_closest(self) -> bool:
        for node in self.__unvisited_nodes:
            if self.__destination.current_distance > node.current_distance:
                return False
        return True

    def get_closest_node(self, nodes) -> Node:
        min_node = None
        for node in nodes:
            if min_node is None:
                min_node = node
            elif min_node.current_distance > node.current_distance:
                min_node = node
        return min_node

    def calc_distances_to_neighbours_and_set_smaller(self, node: Node) -> None:
        neighbours = node.get_neighbours()
        for neighbour in neighbours:
            # Für den aktuellen Knoten berechne für alle benachbarten Knoten die vorläufige Distanz
            # über den aktuellen Knoten.
            distance_to_neighbour = self.__network.get_distance(neighbour.name, node.name)
            distance_over_node = distance_to_neighbour + node.current_distance
            # Vergleiche die berechnete Distanz mit der im Knoten gespeicherten Distanz...
            if distance_over_node < neighbour.current_distance:
                # ...und setze  die kleinere.
                neighbour.current_distance = distance_over_node
                # Wenn die im Knoten gespeicherte Distanz ersetzt wurde,
                # speichere den aktuellen Knoten als Vorgänger im benachbarten Knoten.
                neighbour.predecessor = node

    def __set_as_visited(self, node: Node) -> None:
        node.visited = True
        self.__unvisited_nodes.remove(node)

    def calculate_route(self) -> None:
        node: Node = self.__destination
        while node != self.__start:
            self.__route.insert(0,node)
            node = node.predecessor
        self.__route.insert(0, self.__start)

    def get_route(self) -> str:
        node_names = []

        if len(self.__route) == 0:
            self.calculate_route()

        for node in self.__route:
            node_names.append(node.name)
        return "->".join(node_names)

    def get_distance(self) -> int:
        return self.__distance
