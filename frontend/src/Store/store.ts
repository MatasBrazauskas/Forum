import { configureStore } from "@reduxjs/toolkit";

import userReducer from './userState';

const store = configureStore({
    reducer:{
        USER_INFO: userReducer,
    }
});

export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;