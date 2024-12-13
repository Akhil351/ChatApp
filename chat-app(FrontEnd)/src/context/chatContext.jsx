import { createContext, useContext, useState } from "react";

const ChatContex = createContext()

export const ChatProvider = ({ children }) => {
    const [roomId, setRoomId] = useState("")
    const [currentUser, setCurrentUser] = useState("")
    const [connected, setConnected] = useState(false)
    return (
        <ChatContex.Provider value={{ roomId, setRoomId, currentUser, setCurrentUser, connected, setConnected }}>
            {children}
        </ChatContex.Provider>
    )
}
const useChatContext = () => useContext(ChatContex);
export default useChatContext;