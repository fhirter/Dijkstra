import tkinter as tk
from tkinter import Label, Button, Entry


class View(tk.Frame):
    button: Button
    text_field: Entry

    def __init__(self, **kw):
        super().__init__(**kw)
        self.pack()

        self.master.title("MVC Beispiel")
        self.master.maxsize(1000, 400)

        text = Label(self, text="Dijkstra Algorithmus")
        text.pack()

        self.start_text = Entry(self)
        self.start_text.pack()

        self.destination_text = Entry(self)
        self.destination_text.pack()

        self.button = Button(self, text="run!")
        self.button.pack()
