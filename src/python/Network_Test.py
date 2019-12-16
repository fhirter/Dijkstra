import unittest

from Network import Network


class NetworkTest(unittest.TestCase):
    """
    Albisrieden,Bern,6
    Frauenfeld,Kirchberg,5
    Luzern,Malters,7
    Malters,Wangen,11
    """

    def test_network_creation(self):
        network = Network("DistanzenNeu.csv")

    def test_distances(self):
        network = Network("DistanzenNeu.csv")

        self.assertDistance(network, "Albisrieden", "Bern", 6)
        self.assertDistance(network, "Frauenfeld", "Kirchberg", 5)
        self.assertDistance(network, "Luzern", "Malters", 7)
        self.assertDistance(network, "Malters", "Wangen", 11)

    def assertDistance(self, network, node_a, node_b, distance):
        distance = network.get_distance(network.get_node(node_a), network.get_node(node_b))
        self.assertEqual(distance, distance)


if __name__ == '__main__':
    unittest.main()
