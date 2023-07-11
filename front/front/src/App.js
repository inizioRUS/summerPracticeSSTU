import Search from "./components/Search/Search";
import IndexAccount from "./components/IndexAccount/IndexAccount";
import AccountStock from "./components/AccountStock/AccountStock";
import Header from "./components/Header/Header";
import styles from "./App.module.css"
import MainPage from "./pages/MainPage/MainPage";
import LoginForm from "./components/LoginForm/LoginForm";
import LoginPage from "./pages/LoginPage/LoginPage";
import {Route, Routes} from "react-router-dom";
import Stock from "./components/Stock/Stock";


function App() {
  return (
    <div className={styles.app}>
        {/*<Routes>*/}
        {/*    <Route path={"/"} element={<MainPage />}/>*/}
        {/*</Routes>*/}
        <Stock />
    </div>
  );
}

export default App;
