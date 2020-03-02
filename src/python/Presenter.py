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
        self.__dijkstra.subscribe(self.run_complete_handler)

        self.mainloop()

    def button_event_handler(self, event):
        start = self.__view.start_text.get()
        destination = self.__view.destination_text.get()
        print("running algorithm. From: " + start + "To: " + destination)
        self.__dijkstra.run(start, destination)

    def run_complete_handler(self, event):
        print(event.type + ": algorithm completed!")


presenter = Presenter()
