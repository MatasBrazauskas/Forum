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
        addUserInfo: (state: UserInformation, action: PayloadAction<UserInformation>) => {
            Object.assign(state, action.payload);
        },
        incrementPostCount: (state: UserInformation) => {
            if(typeof state?.postCount === 'number')
                state.postCount++;
        },
        incrementReputation: (state: UserInformation) => {
            if(typeof state?.reputation === 'number')
                state.reputation++;
        },
    }
})

export const { clearUserInfo, addUserInfo, incrementPostCount, incrementReputation } = userSlice.actions;
export default userSlice.reducer;
