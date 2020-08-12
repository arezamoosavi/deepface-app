from mongoengine import connect, disconnect
import os


class Mongo:
    def __init__(self):

        self.host = os.getenv("MONGODB_HOST")
        self.port = int(os.getenv("MONGODB_PORT"))
        self.dbname = os.getenv("MONGODB_DB")
        self.username = os.getenv("MONGODB_USERNAME")
        self.password = os.getenv("MONGODB_PASSWORD")

    def connect(self):

        return connect(
            db=self.dbname,
            username=self.username,
            password=self.password,
            host=self.host,
            port=self.port,
        )

    def disconnect(self) -> None:
        disconnect()
