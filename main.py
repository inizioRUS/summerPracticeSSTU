from fastapi import FastAPI
from Predicter import Predicter
import uvicorn

app = FastAPI()

predicter = Predicter()


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/predict/{name}")
async def say_hello(name: str):
    return predicter.predict(name)

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)