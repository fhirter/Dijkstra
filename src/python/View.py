from tkinter import Frame, Label, Button, Entry


class View:
    __frame: Frame
    button: Button
    text_field: Entry

    def __init__(self, frame: Frame):
        self.__frame = frame

        self.__frame.master.title("MVC Beispiel")
        self.__frame.master.maxsize(1000, 400)

        text = Label(frame, text="Dijkstra Algorithmus")
        text.pack()

        self.start_text = Entry(frame)
        self.start_text.pack()

        self.destination_text = Entry(frame)
        self.destination_text.pack()

        self.button = Button(frame, text="run!")
        self.button.pack()
