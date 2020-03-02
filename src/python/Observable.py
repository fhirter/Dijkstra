class Event:
    def __init__(self, source, event_type):
        self.type = event_type
        self.source = source


class Observable:
    def __init__(self):
        self.__callbacks = []

    def subscribe(self, callback):
        self.__callbacks.append(callback)

    def _fire(self, source, message):
        event = Event(source, message)

        for callback in self.__callbacks:
            callback(event)
