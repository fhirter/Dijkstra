import tkinter as tk
from tkinter import Label, Button, Entry, LEFT, RIGHT


class View(tk.Frame):
    button: Button

    distance: Label
    route: Label

    def __init__(self, **kw):
        super().__init__(**kw)
        self.grid()

        self.master.title("Dijkstra Algorithmus")
        self.master.maxsize(1000, 400)

        Label(self, text="Dijkstra Algorithmus").grid(row=0, columnspan=2)
        Label(self, text="Start:").grid(row=1)
        self.start_input = Entry(self)
        self.start_input.grid(row=1, column=1)

        Label(self, text="Ziel:").grid(row=2)
        self.destination_input = Entry(self)
        self.destination_input.grid(row=2, column=1)

        self.distance = Label(self, text="foo")
        self.distance.grid(row=3, columnspan=2)

        self.route = Label(self, text="bar")
        self.route.grid(row=4, columnspan=2)

        self.button = Button(self, text="run!")
        self.button.grid(row=5, columnspan=2)
