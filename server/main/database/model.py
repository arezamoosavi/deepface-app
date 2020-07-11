from datetime import datetime
from mongoengine import Document, fields
from database.connection import Mongo

mongo = Mongo()

mongo.connect()


class analyze(Document):

    time = fields.DateTimeField(default=datetime.utcnow)
    photo = fields.FileField()


class compare(Document):

    time = fields.DateTimeField(default=datetime.utcnow)
    first = fields.FileField()
    second = fields.FileField()
