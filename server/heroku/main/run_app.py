import io
from PIL import Image
from typing import List
from fastapi import FastAPI, File, UploadFile
from deepface import DeepFace
from tensorflow.python.framework import ops
import tensorflow as tf

app = FastAPI()


@app.on_event("startup")
async def startup_event():
    print("app started")
    global graph
    graph = ops.get_default_graph()
    # do start connection mongodb


@app.on_event("shutdown")
def shutdown_event():
    print("app stoped")
    # do stop connection mongodb


@app.get("/")
def read_root():
    return {"Hello": "World"}


@app.post("/analyze/")
async def analyzer(file: bytes = File(...)):

    image = Image.open(io.BytesIO(file))
    img = tf.keras.preprocessing.image.img_to_array(image)

    with graph.as_default():

        demography = DeepFace.analyze(img, actions=["emotion"])
    return {"file_size": len(file), "prediction": demography}


@app.post("/verification/")
async def verification_route(file: List[bytes] = File(...)):

    image1 = Image.open(io.BytesIO(file[0]))
    img1 = tf.keras.preprocessing.image.img_to_array(image1)

    image2 = Image.open(io.BytesIO(file[1]))
    img2 = tf.keras.preprocessing.image.img_to_array(image2)

    with graph.as_default():

        result = DeepFace.verify(img1, img2, model_name="Facenet")
    return {"result": result}
