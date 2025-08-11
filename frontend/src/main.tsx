import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import { GOOGLE_CLIENT_ID } from './Login/const'
import { GoogleOAuthProvider } from '@react-oauth/google'

import GoogleOAuth from './Login/GoogleOAuth'

// eslint-disable-next-line react-refresh/only-export-components
function App(){

    return (
      <GoogleOAuth />
    )
}

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
      <App />
    </GoogleOAuthProvider>
  </StrictMode>,
)
