class Event:
    def __init__(self, source, message: str):
        self.message = message
        self.source = source


class Observable:
    def __init__(self):
        self.__callbacks = []

    def attach(self, callback):
        self.__callbacks.append(callback)

    def _notify(self, source: object, message: str):
        event = Event(source, message)

        for callback in self.__callbacks:
            callback(event)
