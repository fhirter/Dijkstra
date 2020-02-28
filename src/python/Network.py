import copy
import csv

from node import Node


class Network:
    __nodes = {}
    __distances = {}

    def __init__(self, path):
        self.__generate_network(path)

    def __generate_network(self, path, encoding="utf-8"):
        with open(path, 'r', encoding='utf-8') as csvfile:
            reader = csv.reader(csvfile)

            data = csv.reader(csvfile, delimiter=',')
            for row in data:
                a = self.__get_node_create_if_not_existing(row[0])
                b = self.__get_node_create_if_not_existing(row[1])

                a.add_neighbour(b)
                b.add_neighbour(a)

                distance = int(row[2])

                node_distance_dictionary = self.__distances.get(a)
                if node_distance_dictionary != None:
                    node_distance_dictionary[b] = distance
                else:
                    entry = {b: distance}
                    self.__distances[a] = entry

    def __get_node_create_if_not_existing(self, name):
        if name in self.__nodes:
            return self.__nodes[name]
        else:
            node = Node(name)
            self.__nodes[name] = node
            return node

    def get_node(self, name):
        return self.__nodes[name]

    def get_distance(self, from_node, to_node):
        if from_node == to_node:
            return 0

        if from_node in self.__distances and to_node in self.__distances[from_node]:
            return self.__distances[from_node][to_node]

        if to_node in self.__distances and from_node in self.__distances[to_node]:
            return self.__distances[to_node][from_node]

    def get_nodes(self):
        return copy.deepcopy(self.__nodes)
