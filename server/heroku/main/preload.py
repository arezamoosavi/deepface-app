import tensorflow as tf
from PIL import Image
from io import BytesIO
from deepface import DeepFace

with open("image.jpeg", "rb") as image:
    f = image.read()
    b = bytearray(f)
    img = Image.open(BytesIO(b))
image_array = tf.keras.preprocessing.image.img_to_array(img)

demography = DeepFace.analyze(image_array, actions=["emotion"])
# print("Age: ", demography["age"])
# print("Gender: ", demography["gender"])
# print("Emotion: ", demography["dominant_emotion"])
# print("Race: ", demography["dominant_race"])


result = DeepFace.verify(image_array, image_array, model_name="Facenet")
print("Is verified: ", result["verified"])
