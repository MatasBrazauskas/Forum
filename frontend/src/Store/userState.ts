import { createSlice, type PayloadAction } from "@reduxjs/toolkit";
import { USER_INFO, type UserInformation } from "./utils";

const initialState : UserInformation = {
    username: 'GUEST',
    role: 'GUEST',
}

const userSlice = createSlice({
    name: USER_INFO,
    initialState,
    reducers: {
        clearUserInfo: () => {
            return initialState;
        },
        adduserInfo: (state: UserInformation, action: PayloadAction<UserInformation>) => {
            Object.assign(state, action.payload);
        }
    }
})

export const { clearUserInfo, adduserInfo } = userSlice.actions;
export default userSlice.reducer;
