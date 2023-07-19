import numpy as np
import keras
import pandas as pd
import datetime
from sklearn.preprocessing import MinMaxScaler
import yfinance as yf
from pandas_datareader import data as pdr

yf.pdr_override()


class Predicter:
    def __init__(self):
        self.model = keras.models.load_model('../summerPracticeSSTU/my_model.keras')

    def predict(self, name):
        df = pdr.get_data_yahoo(name, start='2023-01-01', end=datetime.datetime.now())
        data = df.filter(['Close'])
        # Convert the dataframe to a numpy array
        dataset = data.values
        scaler = MinMaxScaler(feature_range=(0, 1))
        scaled_data = scaler.fit_transform(dataset)

        now = scaled_data[len(scaled_data.shape) - 62:, :]

        x_train = []

        n = 7
        x_train.append(now[:, 0])

        x_train = np.array(x_train)

        # Reshape the data
        x_train = np.reshape(x_train, (x_train.shape[0], x_train.shape[1], 1))

        for i in range(n):
            predictions = self.model.predict(x_train) * 1.15
            x_train = np.append(np.delete(x_train, (0, 0, 0)), predictions)
            x_train = np.reshape(x_train, (1, x_train.shape[0], 1))
        now = x_train[:, -n - 1:]
        predictions = scaler.inverse_transform(now.reshape(1, -1))
        dfP = pd.DataFrame({'Predictions': [i for i in predictions[0]]},
                           index=[datetime.date.today() + datetime.timedelta(days=i) for i in range(-1, n)])
        prices = []
        a = [i for i in dfP.index]
        last_quotes = list(dfP['Predictions'])
        l = 0
        for i in a:
            prices.append([i, last_quotes[l]])
            l += 1
        return prices
