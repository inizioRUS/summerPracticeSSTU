import yfinance as yf


class Parser:
    def __init__(self):
        pass

    def get(self, name, period):
        msft = yf.Ticker(name)
        data = msft.history(period=period)
        prices = []
        a = [str(i).split(' ')[0] for i in data.index]
        last_quotes = list(data['Close'])
        l = 0
        for i in a:
            prices.append([i, last_quotes[l]])
            l += 1
        return prices

