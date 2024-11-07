import { create } from "zustand";
import { persist } from "zustand/middleware";
import { UserInterface } from "../interface/UserInterface.tsx";

interface UserStore extends UserInterface {
  setUser: (userInfo: UserInterface) => void;
}

const useUserStore = create(
  persist<UserStore>(
    (set) => ({
      userName: null,
      current: null,

      setUser: (userInfo) => {
        set({
          userName: userInfo.userName,
          current: userInfo.current,
        });
      },
    }),

    {
      name: "userStatus",
    }
  )
);

export default useUserStore;
