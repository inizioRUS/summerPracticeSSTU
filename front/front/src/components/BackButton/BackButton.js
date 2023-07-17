import React from 'react';
import styles from "./BackButton.module.css"

const BackButton = ({ onclick }) => {
    return (
        <button className={styles.back_button}
                onClick={onclick}>
            <svg xmlns="http://www.w3.org/2000/svg" width="44" height="38" viewBox="0 0 44 38" fill="none">
                <path d="M20.8281 37.0108C20.3213 37.5175 19.634 37.8021 18.9174 37.8021C18.2008 37.8021 17.5135 37.5175 17.0067 37.0108L0.791317 20.7954C0.284668 20.2886 4.57764e-05 19.6014 4.57764e-05 18.8847C4.57764e-05 18.1681 0.284668 17.4808 0.791317 16.974L17.0067 0.758636C17.5164 0.266342 18.1991 -0.00605774 18.9077 9.91821e-05C19.6163 0.0062561 20.2941 0.290482 20.7952 0.791561C21.2963 1.29264 21.5805 1.97047 21.5867 2.67908C21.5928 3.38768 21.3204 4.07035 20.8281 4.58007L9.22602 16.1822L40.5379 16.1822C41.2547 16.1822 41.9421 16.4669 42.4489 16.9737C42.9557 17.4806 43.2405 18.168 43.2405 18.8847C43.2405 19.6015 42.9557 20.2889 42.4489 20.7957C41.9421 21.3026 41.2547 21.5873 40.5379 21.5873L9.22602 21.5873L20.8281 33.1894C21.3348 33.6962 21.6194 34.3835 21.6194 35.1001C21.6194 35.8167 21.3348 36.504 20.8281 37.0108Z" fill="url(#paint0_linear_5_1137)"/>
                <defs>
                    <linearGradient id="paint0_linear_5_1137" x1="-14.5" y1="9.5" x2="100.5" y2="52" gradientUnits="userSpaceOnUse">
                        <stop stop-color="#0084FF"/>
                        <stop offset="1" stop-color="white" stop-opacity="0"/>
                    </linearGradient>
                </defs>
            </svg>
        </button>
    );
};

export default BackButton;