import { configureStore } from "@reduxjs/toolkit";

import userReducer from './userState';
import errorsReducer from './errorState';

const store = configureStore({
    reducer:{
        USER_INFO: userReducer,
        ERRORS_INFO: errorsReducer,
    }
});

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;