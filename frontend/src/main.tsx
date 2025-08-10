import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'

import { GOOGLE_CLIENT_ID } from './Login/const'
import { GoogleOAuthProvider } from '@react-oauth/google'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <GoogleOAuthProvider clientId={GOOGLE_CLIENT_ID}>
      <div></div>
    </GoogleOAuthProvider>
  </StrictMode>,
)
