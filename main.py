from fastapi import FastAPI
from Predicter import Predicter

app = FastAPI()

predicter = Predicter()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/predict/{name}")
async def say_hello(name: str):
    return predicter.predict(name)
