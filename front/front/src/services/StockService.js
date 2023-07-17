import img from "../components/RecommendationsStock/dag_test.png";

const API_URl = "http://localhost:8080/stocks"

export const getStockById = async (id) => {
    return {
        id: id,
        img: img,
        name: "Дагестанские авиалинии",
        cost: 1400,
        change: 90
    }
}

export const getStocks = async () => {
    return [
        {
            id: 1,
            img: img,
            name: "Паша",
            cost: 16,
            change: 5
        },
        {
            id: 2,
            img: img,
            name: "Саратовские авиалинии",
            cost: 1200,
            change: 5
        },
        {
            id: 3,
            img: img,
            name: "Дагестанские авиалинии",
            cost: 1400,
            change: 90
        },
        {
            id: 4,
            img: img,
            name: "Армянские авиалинии",
            cost: 900,
            change: 5
        },
        {
            id: 5,
            img: img,
            name: "Саратовские авиалинии",
            cost: 1200,
            change: 5
        },
        {
            id: 6,
            img: img,
            name: "Дагестанские авиалинии",
            cost: 1400,
            change: 90
        },
        {
            id: 7,
            img: img,
            name: "Саратовские авиалинии",
            cost: 1200,
            change: 5
        },
        {
            id: 8,
            img: img,
            name: "Дагестанские авиалинии",
            cost: 1400,
            change: 90
        }]
}
