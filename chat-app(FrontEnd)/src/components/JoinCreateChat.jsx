import React, { useState } from 'react'
import chatIcon from '../assets/speak.png'
import toast from 'react-hot-toast';
import { createRoom as createRoomApi, joinRoomApi } from '../service/RoomService';
import useChatContext from '../context/chatContext';
import { useNavigate } from 'react-router';
const JoinCreateChat = () => {
    const [detail, setDetail] = useState(
        {
            roomId: "",
            userName: "",
        }
    );
    const { setRoomId, setCurrentUser, setConnected } = useChatContext();
    const navigate = useNavigate()
    function handleFormInputChange(event) {
        setDetail({
            ...detail,
            [event.target.name]: event.target.value,
        });
    }
    function validateForm() {
        if (detail.userName === "" || detail.roomId === "") {
            toast.error("Invalid Input !!")
            return false
        }
        else {
            return true
        }
    }
    async function joinRoom() {
        
        if (validateForm()) {
            // join Room
            try {
                const response = await joinRoomApi(detail.roomId)
                toast.success("joined...")
                console.log(response.data);
                setCurrentUser(detail.userName);
                setRoomId(response.data.roomId);
                setConnected(true)
                navigate("/chat")
            }
            catch (error) {
                console.log(error);
                if (error.status === 404) {
                    toast.error("Room not found!")
                }
                else {
                    toast.error("Error in joining room")
                }
            }
        }

    }
    async function createRoom() {
        if (validateForm()) {
            // create Room
            console.log(detail)
            try {
                const response = await createRoomApi(detail.roomId)
                toast.success("Room Created SuccessFully !!")
                setCurrentUser(detail.userName);
                setRoomId(response.data.roomId);
                setConnected(true)
                navigate("/chat")
                // forward to chat page
            }
            catch (error) {
                console.log(error);
                if (error.status === 409) {
                    toast.error("Room  already exists !!")
                }
                else {
                    toast.error("Error in creating room");
                }
            }
        }

    }
    return (
        <div className="min-h-screen flex  items-center justify-center ">

            <div className="p-10 dark:border-gray-700 border w-full flex flex-col gap-5 max-w-md rounded dark:bg-gray-900 shadow">
                <div>
                    <img src={chatIcon} className="w-24 mx-auto" />
                </div>
                <h1 className="text-2xl font-semibold text-center ">Join Room/ Create Room ...</h1>
                {/*name div*/}
                <div className="">
                    <label htmlFor="name" className="block font-medium mb-2">Your Name</label>
                    <input onChange={handleFormInputChange} value={detail.userName} name="userName" placeholder="Enter the name" type="text" id="name" className="w-full dark:bg-gray-600 px-4 py-2 border dark:border-gray-600 rounded-full  focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </div>
                {/*room id div*/}
                <div className="">
                    <label htmlFor="roomId" className="block font-medium mb-2">Room ID/New Room ID</label>
                    <input onChange={handleFormInputChange} value={detail.roomId} name="roomId" placeholder="Enter the room id" type="text" id="roomId" className="w-full dark:bg-gray-600 px-4 py-2 border dark:border-gray-600 rounded-full  focus:outline-none focus:ring-2 focus:ring-blue-500" />
                </div>
                {/*buttons*/}
                <div className="flex justify-center gap-2 mt-4">
                    <button onClick={joinRoom} className="px-3 py-2 dark:bg-blue-500 hover:dark:bg-blue-800 rounded-full">Join Room</button>
                    <button onClick={createRoom} className="px-3 py-2 dark:bg-green-500 hover:dark:bg-green-800 rounded-full">Create Room</button>
                </div>
            </div>


        </div>
    )
}

export default JoinCreateChat