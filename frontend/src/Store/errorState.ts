import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { ERRORS_INFO, type ErrorsInformation } from "./utils";

const initialState: ErrorsInformation  = {
    errors: [],
}

const errorSlice = createSlice({
    name: ERRORS_INFO,
    initialState,
    reducers: {
        addError: (state: ErrorsInformation, action: PayloadAction<string>) => {
            state.errors = [...state.errors, action.payload];
        },
        clearErrors: (state: ErrorsInformation) => {
            state.errors.length = 0;
        }
    }
})

export const { addError, clearErrors} = errorSlice.actions;
export default errorSlice.reducer;
 