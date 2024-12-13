
import { createRoot } from 'react-dom/client'
import './index.css'
import { BrowserRouter } from "react-router"
import AppRoutes from './config/routes'
import { Toaster } from "react-hot-toast"
import { ChatProvider } from './context/chatContext'

createRoot(document.getElementById('root')).render(
    <BrowserRouter>
      <Toaster position="top-center" />
      <ChatProvider>
        <AppRoutes />
      </ChatProvider>
    </BrowserRouter>
)
