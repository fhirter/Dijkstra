from Network import Network
from Observable import Observable
from node import Node


class Dijkstra(Observable):
    __network: Network

    __start: Node
    __destination: Node

    __current_node: Node
    __unvisited_nodes: []

    def __init__(self, network):
        super().__init__()
        self.__network = network

        self.__unvisited_nodes = network.get_nodes().values()

    def run(self, start: str, destination: str) -> None:
        self.__start = self.__network.get_node(start)
        self.__destination = self.__network.get_node(destination)
        self._notify(self, "run_completed")

    def is_destination_closest(self) -> bool:
        for node in self.__unvisited_nodes:
            if self.__destination.current_distance > node.current_distance:
                return False
        return True

    def get_closest_node(self) -> Node:
        neighbours = self.__current_node.get_neighbours()
        min_node = None
        for neighbour in neighbours:
            if min_node.current_distance > neighbour.current_distance:
                min_node = neighbour
        return min_node
