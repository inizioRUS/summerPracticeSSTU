from fastapi import FastAPI
import ParserClass

app = FastAPI()

par = ParserClass.Parser()


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
