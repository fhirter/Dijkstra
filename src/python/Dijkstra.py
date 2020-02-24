from Network import Network
from node import Node


class Dijkstra:
    __start: Node
    __destination: Node
    __network: Network

    def __init__(self, network):
        self.__network = network

    def run(self, start: Node, destination: Node):
        self.__start = self.__network.get_node(start)
        self.__destination = self.__network.get_node(destination)
