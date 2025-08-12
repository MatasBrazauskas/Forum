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
        clearUserInfo: (state: UserInformation) => {
            state.username = 'GUEST';
            state.role = 'GUEST';
            state.dateOfCreation = undefined;
            state.lastOnline = undefined;
            state.postCount = undefined;
            state.reputation = undefined;
        },
        adduserInfo: (state: UserInformation, actions: PayloadAction<UserInformation>) => {
            state = structuredClone(actions.payload);
        }
    }
})

export const { clearUserInfo, adduserInfo } = userSlice.actions;
export default userSlice.reducer;
