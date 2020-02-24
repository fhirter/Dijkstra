import tkinter as tk

from Dijkstra import Dijkstra
from Network import Network
from View import View


class Presenter(tk.Frame):
    __dijkstra: Dijkstra
    __view: View

    def __init__(self):
        super().__init__(None)
        self.pack()

        self.__network = Network("DistanzenNeu.csv")
        self.__dijkstra = Dijkstra(self.__network)
        self.__view = View(self)

        self.__view.button.bind("<Button-1>", self.button_event_handler)

        self.mainloop()

    def button_event_handler(self, event):
        # self.__network.get_node()
        # self.__dijkstra.run(start, destination)
        start = self.__view.start_text.get()
        destination = self.__view.destination_text.get()
        print("running algorithm. From: " + start + "To: " + destination)
        self.__dijkstra.run(start, destination)


presenter = Presenter()
