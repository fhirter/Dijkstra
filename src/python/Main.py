from Dijkstra import Dijkstra
from Network import Network
from Presenter import Presenter
from View import View

network = Network("DistanzenNeu.csv")
dijkstra = Dijkstra(network)
view = View()

presenter = Presenter(view, dijkstra)
