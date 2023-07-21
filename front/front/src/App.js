import styles from "./App.module.css"
import MainPage from "./pages/MainPage/MainPage";
import LoginPage from "./pages/LoginPage/LoginPage";
import {Route, Routes} from "react-router";
import StocksPage from "./pages/StocksPage/StocksPage";
import StockPage from "./pages/StockPage/StockPage";
import UserPage from "./pages/UserPage/UserPage";
import BackButton from "./components/BackButton/BackButton";
import IncreaseBalancePage from "./pages/IncreaseBalancePage/IncreaseBalancePage";
import OperationsPage from "./pages/OperationsPage/OperationsPage";
import FavouritePage from "./pages/FavouritePage/FavouritePage";
import AdvancedStocksPage from "./pages/AdvancedStocksPage/AdvancedStocksPage";

function App() {
  return (
    <div className={styles.app}>
        <Routes>
            <Route path={"/"} element={<LoginPage />} />
            <Route path={"/main"} element={<MainPage />} />
            <Route path={"/user"} element={<UserPage />} />
            <Route path={"/stocks"} element={<StocksPage />} />
            <Route path={"/stock/:id"}  element={<StockPage />} />
            <Route path={"/increase"} element={<IncreaseBalancePage />} />
            <Route path={"/operations"} element={<OperationsPage />} />
            <Route path={"/favourite"} element={<FavouritePage />} />
            <Route path={"/advanced"} element={<AdvancedStocksPage />} />
        </Routes>
    </div>
  );
}

export default App;
