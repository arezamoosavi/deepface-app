import logging

from PIL import Image
from io import BytesIO

from mongoengine import Document, fields
from database.connection import Mongo

mongo = Mongo()

if __name__ == "__main__":
    try:

        mongo.connect()

        class Test(Document):
            file = fields.FileField()

        test = Test()
        test.file.put(
            open("test_image.png", "rb"),
            content_type="image/png",
            filename="test_img.png",
        )
        test.save()

        Test.objects.as_pymongo()
        t = Test.objects.first()
        content = t.file.read()
        im = Image.open(BytesIO(content))
        print("saved image ", type(im))

        numberdata = Test.objects.count()
        print("\nnumber of items: ", numberdata)

        exit(1)
    except Exception as e:

        logging.error("Error! {}".format(e))
        exit(0)
