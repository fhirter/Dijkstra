import unittest

from Network import Network


class NetworkTest(unittest.TestCase):
    network = Network("DistanzenNeu.csv")

    def test_get_node(self):
        node_names = ["Albisrieden", "Langenthal", "Zürich", "Malters", "Ittigen", "Urdorf"]

        for name in node_names:
            node = self.network.get_node(name)
            self.assertEqual(name, node.name)

    def test_distances(self):
        self.assertDistance(self.network, "Albisrieden", "Bern", 6)
        self.assertDistance(self.network, "Frauenfeld", "Kirchberg", 5)
        self.assertDistance(self.network, "Luzern", "Malters", 7)
        self.assertDistance(self.network, "Malters", "Wangen", 11)

    def assertDistance(self, network, node_a, node_b, distance):
        distance = network.get_distance(network.get_node(node_a), network.get_node(node_b))
        self.assertEqual(distance, distance)

    def test_get_nodes(self):
        nodes = self.network.get_nodes()
        self.assertEqual(len(nodes), 26)



if __name__ == '__main__':
    unittest.main()
