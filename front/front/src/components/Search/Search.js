import React, {useState} from "react";
import styles from './Search.module.css'
import search from './search.svg'

const Search = () => {
    const [searchText, setSearchText] = useState("Бумага, инвестор, пост");

    return (
        <form className={styles.form}>
            <a href={"#"} className={styles.a}>
                <img src={search} className={styles.img}/>
            </a>
            <input type={"search"}
                   className={styles.input}
                   placeholder={searchText !== '' ? searchText : "Бумага, инвестор, пост"}
                   onChange={(event) => {
                       setSearchText(event.target.value)
                   }}/>
        </form>
    )
}

export default Search