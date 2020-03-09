from Dijkstra import Dijkstra
from View import View


class Presenter:
    __dijkstra: Dijkstra
    __view: View

    def __init__(self, view, dijkstra):
        self.__dijkstra = dijkstra
        self.__view = view

        self.__view.button.bind("<Button-1>", self.button_event_handler)
        self.__dijkstra.attach(self.run_complete_handler)

        view.mainloop()

    def button_event_handler(self, event):
        start = self.__view.start_text.get()
        destination = self.__view.destination_text.get()
        print("running algorithm. From: " + start + " To: " + destination)
        self.__dijkstra.run(start, destination)

    def run_complete_handler(self, event):
        print(event.type + ": algorithm completed!")
