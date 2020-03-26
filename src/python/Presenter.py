from tkinter import END, StringVar

from Dijkstra import Dijkstra
from Observable import Event
from View import View


class Presenter:
    __dijkstra: Dijkstra
    __view: View

    def __init__(self, view, dijkstra: Dijkstra):
        self.__dijkstra = dijkstra
        self.__view = view

        self.__view.button.bind("<Button-1>", self.button_event_handler)
        self.__dijkstra.attach(self.run_complete_handler)



        view.mainloop()

    def button_event_handler(self, event: Event) -> None:
        start = self.__view.start_input.get()
        destination = self.__view.destination_input.get()
        print("running algorithm. From: " + start + " To: " + destination)
        self.__dijkstra.run(start, destination)

    def run_complete_handler(self, event: Event) -> None:
        dijkstra: Dijkstra = event.source
        distance = dijkstra.get_distance()
        route = dijkstra.get_route()

        self.__view.distance["text"] = "Distanz: " + str(distance)
        self.__view.route["text"] = "Route: " + route
