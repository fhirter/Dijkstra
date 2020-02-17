from Network import Network
from node import Node


class Dijkstra:
    __start: Node
    __destination: Node
    __network: Network

    def __init__(self, network):
        self.__network = network

    def run(self, start: Node, destination: Node):
        self.__start = start
        self.__destination = destination
