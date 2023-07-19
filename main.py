from fastapi import FastAPI
import ParserClass
from fastapi.middleware.cors import CORSMiddleware

app = FastAPI()

par = ParserClass.Parser()

origins = ["*"]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.get("/")
async def root():
    return {"message": "Hello World"}


@app.get("/year/{name}")
async def year(name: str):
    return {"prices": par.get(name, "1y")}


@app.get("/mounth/{name}")
async def say_hello(name: str):
    return {"prices": par.get(name, "1mo")}


@app.get("/week/{name}")
async def say_hello(name: str):
    return {"prices": par.get(name, "5d")}


@app.get("/today/{name}")
async def say_hello(name: str):
    return {"prices": par.get(name, "1d")}
