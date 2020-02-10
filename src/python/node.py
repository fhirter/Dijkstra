class Node:
    def __init__(self, name):
        self.name = name
        self.__neighbours = []

        self.predecessor = None
        self.current_distance = 2 ** 32
        self.visited = False

    def add_neighbour(self, node):
        self.__neighbours.append(node)
